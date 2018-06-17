package model;
/*
 * Classe GameStatus 
 * Utile pour la sauvegarde des donnees sur le hero la map le scenario et la position de hero
 */



import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.character.hero.CopyOfHeroStats;
import resources.additionalClass.SeparatorFileWriter;

public class GameStatus {

	static final String SEPARATOR = "-";
	static final String ENDSEPARATOR = "\n";
    private final static SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
    private final static int STATUSLENGTH = 11;
   
	private int hp;
	private int maxHP;
	private int mp;
	private int maxMp;
	private int attackItem;
	private int defenseItem;
	private int row;
	private int column;
	private int scenarioLine;
	private int idMap;
	private int score;
	private StringProperty date;
	
	public GameStatus(CopyOfHeroStats heroStats,int row, int column, int scenarioLine,int idMap) {
		
		if(heroStats != null && row > 0 && column > 0 && scenarioLine >= 0) {
			this.hp = heroStats.getHPBinding().get();
			this.maxHP = heroStats.getMaxHP().get();
			this.mp = heroStats.getMPBinding().get();
			this.maxMp = heroStats.getMaxMP().get();
			this.attackItem = heroStats.getAtkImage().get();
			this.defenseItem = heroStats.getDefImage().get();
			this.row = row;
			this.column = column;
			this.scenarioLine = scenarioLine;
			this.idMap = idMap;
			
			this.initDate();
		}
		else
			throw new IllegalArgumentException("INVALID STATE " );
	}
	
	public GameStatus(String[] status) {

		if(status.length == STATUSLENGTH) {
			try {
			
				this.hp = Integer.parseInt(status[0]);
				this.maxHP = Integer.parseInt(status[1]);
				this.mp = Integer.parseInt(status[2]);
				this.maxMp = Integer.parseInt(status[3]);
				this.attackItem = Integer.parseInt(status[4]);
				this.defenseItem =Integer.parseInt(status[5]);
				this.row = Integer.parseInt(status[6]);
				this.column = Integer.parseInt(status[7]);
				this.scenarioLine = Integer.parseInt(status[8]);
				this.idMap = Integer.parseInt(status[9]);
				this.date = new SimpleStringProperty(status[10]);
		
			}catch(NumberFormatException e) {

				throw new IllegalArgumentException("INVALID STATE");
			
			}
		}
		
	}
	
	/*
	 * Permet d'encoder les donnees relatives caracteristiques, au scenario (map et position)
	 * 
	 */
	
	@Override
	public String toString() {
		
		String encode = this.hp + SEPARATOR;
		
		encode += this.maxHP + SEPARATOR;
		encode += this.mp + SEPARATOR;
		encode += this.maxMp + SEPARATOR;
		encode += this.attackItem + SEPARATOR;
		encode += this.defenseItem + SEPARATOR;
		encode += this.row + SEPARATOR;
		encode += this.column + SEPARATOR;
		encode += this.scenarioLine + SEPARATOR ;
		encode += this.idMap + SEPARATOR;
		encode += this.date.get() + ENDSEPARATOR ;

		return encode;
		
	}
	
	private void initDate() {
		
		this.date = new SimpleStringProperty(DATE_FORMATTER.format(new Date()));
	
	}
	
	public boolean saveState() {
		return SeparatorFileWriter.writeToFile(StatsLoader.FILEPATH,this.toString(), true);
	}
	
	public  int getHp() {
		return hp;
	}

	public  int getMaxHP() {
		return maxHP;
	}

	public  int getMp() {
		return mp;
	}

	public  int getMaxMp() {
		return maxMp;
	}

	public  int getAttackItem() {
		return attackItem;
	}

	public  int getDefenseItem() {
		return defenseItem;
	}

	public  int getRow() {
		return row;
	}

	public  int getColumn() {
		return column;
	}

	public  int getScenarioLine() {
		return scenarioLine;
	}

	public  StringProperty getDate() {
		return date;
	}
	
	public int getMapId() {
		return this.idMap;
	}
}
