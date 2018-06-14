package model;

import java.io.FileWriter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.character.hero.CopyOfHeroStats;

public class GameStatus {

	static final String SEPARATOR = "-";
    private final static SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
    private final static int STATUSLENGTH = 10;
   
	private IntegerProperty hp;
	private IntegerProperty maxHP;
	private IntegerProperty mp;
	private IntegerProperty maxMp;
	private IntegerProperty attackItem;
	private IntegerProperty defenseItem;
	private IntegerProperty row;
	private IntegerProperty column;
	private IntegerProperty scenarioLine;
	private StringProperty date;
	
	public GameStatus(CopyOfHeroStats heroStats,int row, int column, int scenarioLine) {
		this.hp = new SimpleIntegerProperty(heroStats.getHPBinding().get());
		this.maxHP = new SimpleIntegerProperty(heroStats.getMaxHP().get());
		this.mp = new SimpleIntegerProperty(heroStats.getMPBinding().get());
		this.maxMp = new SimpleIntegerProperty(heroStats.getMaxMP().get());
		this.attackItem = new SimpleIntegerProperty(heroStats.getAtkImage().get());
		this.defenseItem = new SimpleIntegerProperty(heroStats.getDefImage().get());
		this.row = new SimpleIntegerProperty(row);
		this.column = new SimpleIntegerProperty(column);
		this.scenarioLine = new SimpleIntegerProperty(scenarioLine);
		this.initDate();
	}
	
	public GameStatus(String[] status) {
		System.out.println(status.length);
		if(status.length == STATUSLENGTH) {
			try {
				this.hp = new SimpleIntegerProperty(Integer.parseInt(status[0]));
				this.maxHP = new SimpleIntegerProperty(Integer.parseInt(status[1]));
				this.mp = new SimpleIntegerProperty(Integer.parseInt(status[2]));
				this.maxMp = new SimpleIntegerProperty(Integer.parseInt(status[3]));
				this.attackItem = new SimpleIntegerProperty(Integer.parseInt(status[4]));
				this.defenseItem = new SimpleIntegerProperty(Integer.parseInt(status[5]));
				this.row = new SimpleIntegerProperty(Integer.parseInt(status[6]));
				this.column = new SimpleIntegerProperty(Integer.parseInt(status[7]));
				this.scenarioLine = new SimpleIntegerProperty(Integer.parseInt(status[8]));
				this.date = new SimpleStringProperty(status[9]);
			}catch(NumberFormatException e) {
				e.printStackTrace();
				throw new IllegalArgumentException("INVALID STATE");
			}
		}
	}
	
	@Override
	public String toString() {
		
		String encode = this.hp.get() + SEPARATOR;
		encode += this.maxHP.get() + SEPARATOR;
		encode += this.mp.get() + SEPARATOR;
		encode += this.maxMp.get() + SEPARATOR;
		encode += this.attackItem.get() + SEPARATOR;
		encode += this.defenseItem.get() + SEPARATOR;
		encode += this.row.get() + SEPARATOR;
		encode += this.column.get() + SEPARATOR;
		encode += this.scenarioLine.get() + SEPARATOR ;
		encode += this.date.get();
		return encode;
		
	}
	
	private void initDate() {
		this.date = new SimpleStringProperty(DATE_FORMATTER.format(new Date()));
	}
	
	public void saveState() {
		try {
			FileWriter writer = new FileWriter(StatsLoader.FILEPATH, false);
			writer.write(this.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public final IntegerProperty getHp() {
		return hp;
	}

	public final IntegerProperty getMaxHP() {
		return maxHP;
	}

	public final IntegerProperty getMp() {
		return mp;
	}

	public final IntegerProperty getMaxMp() {
		return maxMp;
	}

	public final IntegerProperty getAttackItem() {
		return attackItem;
	}

	public final IntegerProperty getDefenseItem() {
		return defenseItem;
	}

	public final IntegerProperty getRow() {
		return row;
	}

	public final IntegerProperty getColumn() {
		return column;
	}

	public final IntegerProperty getScenarioLine() {
		return scenarioLine;
	}

	public final StringProperty getDate() {
		return date;
	}
}
