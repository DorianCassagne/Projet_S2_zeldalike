package model.scenario.action;


import java.util.function.Supplier;


import model.character.GameCharacter;
import model.character.attack.statics.DynamicLauncher;
import model.character.enemy.Enemy;
import model.character.enemy.EnemyFactory;
import model.character.hero.GameHero;
import model.character.item.Item;
import model.character.item.factory.ItemFactory;
import model.character.item.mapChange.MapChangerEnum;
import model.character.npc.TalkingNPC;
import model.gameMap.additional.Statics;

public class Action {
	
	/*
	 * Cette classe représente une action produite par une condition du scénario.
	 * 
	 * Les actions (résponsabilités) peuvent être :  
	 * -Une création : crée un personnage (Ennemi ou NPC) ou ajouter un item à la case
	 * -Suppression : retirer un monstre de la map ou rendre une case déplaçable : en retirant les fonds non traversable et les NPC
	 * -Affichage : afficher un message à l'utilisateur
	 * -Ajout : ajouter une attaque au héro
	 * 
	 * Pour mieux comprendre les définitions des symboles, il faudra se référrer à ActionEncode situé au même package
	 */
	
	//Constantes contenant les symboles utilisé lors de l'écriture de l'attaque (Symboles autorisées


	public final static char CREATION = 'C';
	public final static char ITEM = 'I';
	public final static char WALKABLE = 'W';
	public final static char MONSTER=  'M';
	public final static char DROP = 'D';
	public final static char SHOW = 'S';
	public final static char ADD = 'A';
	public final static char ATTACK = 'A';
	public final static char NOTHING = 'N';
	public final static char NPC = 'N';
	public final static char HERO = 'H';
	public final static char DELAY = 'd';
	public final static char MAP = 'M';
	
	//Cette variable statique représente les données commune à l'action, vérifiez la classe actionData
	private static ActionData actionData;
	
	
	private char generalType;//Le type générée par l'action (par exemple Monstre)
	private String specificType;//Le sous-type générée par l'action (par exemple BADMONKEY)
	private String info;//Les détails de l'action, (par exemple l'identifiant du monstre)
	private int cellId;//L'identifiant de la case sur laquelle se produira l'action (Si nécessaire)
	private boolean isActive;//Boolean utilisée pour l'encapsulation et vérifiera que cette classe est appelée en interne par le constructeur privée
	
	
	/*
	 * Constructeur d'action, initialise les variables d'instances et met isActive à true (par défaut initialisé à false)
	 * @param char generalType : Type générale
	 * @param String specificType : Sous-type
	 * @param String info : détails
	 * @param int cellId : identifiant de la case
	 * 
	 * Ce constructeur ne déclenche pas d'érreur car la validité des paramètres dépends de l'action exécuté
	 * Donc parfois ses paramètres sont optionnelles et peuvent alors être nulles
	 * 
	 */
	

	private Action(char generalType,String specificType,String info,int cellId) {
		this.generalType = generalType;
		this.specificType = specificType;
		this.info = info;
		this.cellId = cellId;
		this.isActive = true;
	}

	
	
	/*
	 * Renvoie vrai si une action à été crée par l'intermédiaire de la méthode statique makeAction
	 * @param void 
	 * @return boolean : l'attribut isActive
	 */

	public boolean isActive() {
		return this.isActive;
	}

	
	/*
	 * Crée une fonction qui affiche un message à l'écran.
	 * L'affichage du message se fait en changeant la valeur de messageProperty(à vérifier dans la classe Game).
	 * 
	 * @param void
	 * @return Supplier<Boolean> : Une fonction qui effectue l'action et renvoie vrai si le message a été diffusée au joueur
	 */
	
	private Supplier<Boolean> createShowConsumer(){
		Supplier<Boolean> supplier = (()->{
			actionData.messageProperty().set(this.info);
			return true;
		});
		return supplier;
	}
	
	
	
	/*
	 * La fonction consultée lors de la creation d'un élément. 
	 * 
	 * @param void
	 * @return Supplier<Boolean> : retourne une fonction permettant de créer l'objet voulu
	 * @throws IllegalArgumentException : Si le sous-type d'action n'est pas identifié.
	 * 
	 */
	private Supplier<Boolean> createCreation(){
		Supplier<Boolean> supplier ;

		switch(this.generalType) {
		case ITEM :
			supplier = ()->createItem();
			break;
		case MONSTER:
			supplier = ()->createMonster();
			break;
		case NPC : 
			supplier = ()->createNPC();
			break;
		default:
			throw new IllegalArgumentException("ERROR IN CREATION WHILE PROCESSING GENERAL TYPE" + this.generalType);
		}
		
		return supplier;
	}
	
	/*
	 * Instancie un monstre en fonction de son sous-type
	 * et l'ajoute à la liste des monstres crées par le scénario
	 * 
	 * 
	 * @param void
	 * @return boolean added :-renvoie vrai si l'ennemie a été correctement ajouté à la map
	 * 						   L'ajout de l'ennemie pourra être interrompu si la case n'est pas traversable
	 * 
	 * Attention : 
	 * En cas de deux monstres ayant le même identifiant, aucune exception ne sera déclenché.
	 * Cela est dû à la raison suivante : ne pas interrompre le déroulement du jeu par faute d'un élément du scénario
	 * Si vous voulez être sûr de ne pas créer deux monstres ayant le même identifiant, utilisez l'outil graphique
	 *
	 */


	private boolean createMonster() {
		boolean added = true;
		
		try {	
			Enemy enemy = EnemyFactory.MonsterFactory(this.specificType, actionData.getMap(), Statics.convertToRow(this.cellId),Statics.convertToColomn(this.cellId));
			actionData.getAttackList().put(this.info,enemy);
		}catch(Exception e) {
			added = false;
			System.err.println("ENEMY FACTORY FAILED BECAUSE OF " + this.specificType + e.getMessage());
		}
		
		return added;
	}
	
	
	/*
	 * Instancie un NPC en fonction de son id et l'ajoute à la map
	 * 
	 * 
	 * @param void
	 * @return boolean added : renvoie toujours vrai
	 * 
	 * Attention : 
	 * Avant de créer un NPC, merci de bien lire la class TalkingNPC (si on le place sur une case non traversable).
	 *
	 */
	
	private boolean createNPC() {
		
		try {
		
			new TalkingNPC(actionData.messageProperty(), this.info,this.specificType,actionData.getMap(),Statics.convertToRow(this.cellId),Statics.convertToColomn(this.cellId));
			 actionData.getNPCList().put(this.cellId, this.specificType);
		}catch(Exception e) {
			System.err.println("ERROR ON TALKING NPC, MAYBE THE ID WAS NOT A NUMBER : " + this.specificType);
		}
		
		return true;
	}
	
	/*
	 * Crée un item en fonction de son nom
	 * 
	 * @param void
	 * @return boolean added : -Vrai : si l'item a été bien placé sur la map
	 * 						   -False : si l'ajout de l'item sur la map a échoué
	 * 
	 * Attention : Pas d'excéption en cas d'échéc 
	 */
	
	private boolean createItem() {
		Item item = ItemFactory.getItem(this.specificType.toUpperCase());

		boolean created = actionData.getMap().addItem(item,this.cellId);

		if(created) {
			actionData.getItemList().put(this.cellId,this.specificType.toUpperCase());
		}
		
		return created;
	}
	
	
	/*
	 * La fonction consultée lors de la suppression d'un élément. 
	 * 
	 * @param void
	 * @return Supplier<Boolean> : retourne une fonction permettant de supprimer/retirer un monstre ou une propriété.
	 * @throws IllegalArgumentException : Si le sous-type d'action n'est pas identifié.
	 */
	private Supplier<Boolean> establishDrop() {
		Supplier<Boolean> supplier;
		switch(this.generalType) {
		case MONSTER : 
			supplier = ()->{
				boolean isFound = actionData.getAttackList().get(this.info) != null;
				if(isFound) {
					Enemy enemy = actionData.getAttackList().get(this.info);
					enemy.removeCharacter(this);
					actionData.getAttackList().remove(enemy);
				}
				return isFound;
			
			};
			break;
			
		case WALKABLE : 
			supplier = () -> {
				try {
					actionData.getMap().clearBackgroundConstraint(this.cellId,this,Integer.parseInt(this.info));
					actionData.getNPCList().remove(this.cellId);
				}catch(NumberFormatException e) {
					System.err.println("ERROR ON ESTABLISH DROP() OF WALKABLE"); 
				}
				return true;
			};
			break;
		
		case ITEM : 
			supplier = ()-> {
				actionData.getMap().removeItemAt(this.cellId);
				return true;
		};
		
		case HERO : 
			supplier = ()-> {
				GameHero.getHero().removeCharacter(this);
				return true;
			};
		break;
		
		default : 
				throw new IllegalArgumentException("DROP ACTION FAILED BECAUSE OF " + this.generalType);
		}
		return supplier;
	}
	
	

	/*
	 * La fonction consultée lors de l'ajout d'un élément au héro. 
	 * 
	 * @param void
	 * @return Supplier<Boolean> : retourne une fonction permettant de modifier le héro en lui ajoutant des items ou des attaques.
	 * @throws IllegalArgumentException : Si le sous-type d'action n'est pas identifié.
	 */
	private Supplier<Boolean> establishAdd(){
		Supplier<Boolean> supplier;
		switch(this.generalType) {
		case ITEM :
			supplier = ()->addItemToHero();
			break;
		case ATTACK:
			supplier = ()->addAttackToHero();
			break;
		case MONSTER :
			supplier = ()->addCharacter(actionData.getAttackList().get(this.info));
			break;
		case HERO :
			supplier = ()->addCharacter(GameHero.getHero());
			break;
		default :
			throw new IllegalArgumentException("ERROR GENERAL TYPE AT ADD :  "+this.generalType);
		}
		return supplier;
	}
	
	
	/*
	 * Ajoute un caractère à la map
	 * 
	 * @param GameCharacter character  : le caractère à ajouter
	 * 
	 * @return boolean
	 */
	
	private boolean addCharacter(GameCharacter character) {
		boolean added = character != null && actionData.getMap().addCharacter(character, character.getRow(), character.getColumn());
		return added;
	}
	
	
	/*
	 * Cette fonction ajoute un Item au héro
	 * L'ajout des attaques normaux doit se faire par l'intermédiaire d'item
	 * 
	 * @param void
	 * @return boolean : Vrai
	 * 
	 * 
	 */
	private boolean addItemToHero() {
		Item item = ItemFactory.getItem(this.specificType);
		return item.effectOn(GameCharacter.getHero());
	}
	
	
	/*
	 * Ajoute une attaque au héro
	 */
	
	private boolean addAttackToHero() {
		
		try {
			DynamicLauncher dynamicLaunch = new DynamicLauncher(Integer.parseInt(this.specificType));
			GameHero.getHero().addLauncher(dynamicLaunch);
		}catch(Exception e) {
			System.err.println("ERROR ON ADD ATTACK TO HERO BECAUSE OF SPECIFIC TYPE : " + this.specificType);
		}
		return true;
	}
	
	
	/*
	 * Change la Map du héro
	 */
	private boolean changeMap() {
		GameHero.getHero().setMapChange(MapChangerEnum.valueOf(this.specificType));
		return true;
	}
	
	/*
	 * Cette fonction retarde la gameLoop et le scénario de deux délais différents
	 * 
	 * @return boolean : si le delay a été bien effectué
	 */
	private boolean delay() {
		
		actionData.getMap().setActionDelay(this,this.cellId);
		try {
			actionData.setScenarioCounter(Integer.parseInt(this.info));
		}catch(NumberFormatException e) {
			System.err.println("ERROR ON DELAY BECAUSE THE INFO IS NOT AN INT " + this.info);
		}
		return true;
	}
	
	/*
	 * Cette fonction renvoie une fonction représentant l'action demandée
	 * 
	 * @param String[] action : Un tableau de symboles qui vont être utilisée en ActionEncode (vérifiez là)
	 * @param data : représente les données générales de l'action, tel que la map courante, messageProperty ...
	 
	 * @return Supplier<Boolean> : renvoie une fonction représentant l'action demandé 
	 
	 * @throws IllegalArgumentException : Si l'une des actions a reçu un faux paramètre (différent en fonction du type d'action)
	 * @throws IllegalArgumentException : Si l'action n'est pas identifié.
	 * 
	 * 
	 */

	
	public static Supplier<Boolean> TakeAction(String[] action,ActionData data) {
		ActionEncode encode = new ActionEncode(action);
		Action actionParams = new Action(encode.getGeneralType(),encode.getSpecificType(),encode.getInfo(),encode.idCase());
		actionData = data;
		Supplier<Boolean> supplier;
		switch(encode.getAction()) {
		case SHOW :
			supplier = actionParams.createShowConsumer();
			break;
		case CREATION:
			supplier = actionParams.createCreation();
			break;
		case DROP :
			supplier = actionParams.establishDrop();
			break;
		case ADD :
			supplier = actionParams.establishAdd();
			break;
		case NOTHING:
			supplier = ()->(true);
			break;
		case MAP:
			supplier = ()->actionParams.changeMap();
			break;
		case DELAY:
			supplier =()->actionParams.delay();
			break;
		default :
			throw new IllegalArgumentException("UNKNOWN ACTION NAME : " + encode.getAction());
		}
		return supplier;
		
	}
		
	
}
