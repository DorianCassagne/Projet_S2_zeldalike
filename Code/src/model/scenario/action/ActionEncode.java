package model.scenario.action;

import model.character.enemy.Enemy;
import model.scenario.Scenario;
import resources.additionalClass.Conversion;

public class ActionEncode {
	/*
	 * Cette classe décode un tableau de chaîne de caractères
	 * 
	 *  Responsabilités : 
	 *  -Convertir chaque élément du tableau vers le type correcte qui pourra être interprété par l'action
	 *  -Associer à chaque variable d'instance le bon élément dans le tableau
	 *   	 * 	S/C/D/A-I/M/m/A/W-' '/{type_monstre}/{type_item}-contenuMessage/idMonstre-CELLID
	
	 */
	

	/*
	 * L'action a éxecuté : -Affichage représenté par S
	 * 						-Creation représenté par C
	 * 						-Suppression représenté par D
	 * 						-Ajout représenté par A
	 */
	private char action ;
	/*
	 * Le type général de l'élément qui sera créé : Dépends de l'action 
	 * Affichage -> Message
	 * Creation -> Monstre ou Item
	 * Suppression -> Monstre ou la propriété traversable d'une case
	 * Ajout -> Item ou Attaque
	 */
	private char generalType;
	//Le type spécifique d'un type général : Les noms dans les types énumérées
	private String specificType;
	 // Le détail de l'action : Peut être le message ou l'identifiant du monstre
	private String info;
	//L'identifiant de la case
	private int idCase;
	

	/*
	 * Crée un objet de type ActionEncode
	 * Ce constructeur assigne à chaque variable le bon éléments du tableau au bon type
	 * 
	 * @param : Tableau de chaîne de caractère représentant l'encodage écrit de l'action 
	 * 
	 * Attention : Aucune vérification de la validité des paramètre n'est faite à ce niveau
	 * Si jamais l'un des attributs est null, aucune erreur ne sera déclenché, 
	 * vu qu'ils peuvent être optionnelle selon l'action 
	 */
	
	public ActionEncode(String[] params) {
		try {
			this.action = Conversion.toChar(params[0]);
			if(action != Action.NOTHING) {
				this.generalType = Conversion.toChar(params[1]);
				this.specificType = params[2];
				this.info = params[3];
				this.idCase = Conversion.toInt(params[4]);
			}		
		}catch(Exception e) {
			
		}
	}
	
	

	
	/*
	 * Getters
	 */
	
	public char getAction() {
		return this.action;
	}
	
	public char getGeneralType() {
		return this.generalType;
	}
	
	public String getSpecificType() {
		return this.specificType;
	}
	
	public String getInfo() {
		return this.info;
	}
	
	public int idCase() {
		return this.idCase;
	}

	
	public static String encodeToMonster(String name,Enemy monster) {
		String value = Scenario.EXTERNALSEPARATOR + Action.CREATION;
		value += Scenario.INTERNALSEPARTOR + Action.MONSTER;
		value += Scenario.INTERNALSEPARTOR + monster.getName();
		value += Scenario.INTERNALSEPARTOR + name;
		value += Scenario.INTERNALSEPARTOR + monster.getCellId();
		
		return value;
	}
	
	public static String encodeToItem(int cellId,String itemName) {
		
		String value = Scenario.EXTERNALSEPARATOR + Action.CREATION;
		value += Scenario.INTERNALSEPARTOR + Action.ITEM;
		value += Scenario.INTERNALSEPARTOR + itemName;
		value += Scenario.INTERNALSEPARTOR + " ";
		value += Scenario.INTERNALSEPARTOR + cellId;
		
		return value;
		
	}
	
	public static String encodeToNPC(int cellId,String NPCType) {
		
		String value = Scenario.EXTERNALSEPARATOR + Action.CREATION;
		value += Scenario.INTERNALSEPARTOR + Action.NPC;
		value += Scenario.INTERNALSEPARTOR + NPCType;
		value += Scenario.INTERNALSEPARTOR + " ";
		value += Scenario.INTERNALSEPARTOR + cellId;
		
		return value;

		
	}
	
}
