package model;

import java.util.HashMap;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;

public class Map {

	public final static int ROWINDEX = 0;
	public final static int COLUMNINDEX = 1;
	
	public static enum Deplacement{
		TOP(-PAS,0),
		BOTTOM(PAS,0),
		LEFT(0,-PAS),
		RIGHT(0,PAS);
		
		private int verticaly;
		private int horizontaly;
		Deplacement(int verticaly,int horizontaly){
			this.verticaly = verticaly;
			this.horizontaly = horizontaly;
		}
		
	}
	
	private static final int PAS = 1 ;
	public final static int TRASHINDEX = Integer.MAX_VALUE;
	private final static int INVALIDINDEX = -1;
	private Case[][] cases ;
	private HashMap<Integer,Personnage> listePerso ;
	private IntegerProperty changeProperty;
	private final IntegerProperty safeProperty;
	private int idPersonnage;
	private Case trash;
	
	
	public Map(String mapName)  {
		try {
			this.safeProperty = new SimpleIntegerProperty();
			MapReader map = new MapReader(mapName);
			this.changeProperty = new SimpleIntegerProperty();
			this.listePerso = new HashMap<Integer,Personnage>();
			this.idPersonnage = 0;
			this.trash = new Case(0);
			initialiseCases(map);
		}catch(Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Invalid Map");
		}
	}
	
	
	public boolean addToList(Personnage personnage,int startCaseX,int startCaseY) {
		boolean added = false;
		if(!this.listePerso.containsValue(personnage)) {
			this.listePerso.put(this.idPersonnage, personnage);
			try {
				this.trash.viderCase();
				this.cases[startCaseX][startCaseY].ajouterPersonnage(personnage);
			}catch(IllegalArgumentException e) {}
			this.idPersonnage++;
			added = true;
		}
		return added;
	}
	
	private void initialiseCases(MapReader map) {
		int mapHeight = map.getNbTilesHeight();
		int mapWidth = map.getNbTilesWidth();
		this.cases = new Case[mapHeight][mapWidth];
		this.trash.changeProperty().addListener(
				(obs,oldValue,newValue)->{
					this.changeProperty.set(TRASHINDEX);
				}
		);
		for(int i = 0 ; i < mapHeight;i++) {
			int row = i;
			for(int j = 0 ; j < mapWidth;j++) {
				int column = j;
				this.cases[i][j] = new Case(map.getRepresentation(i, j));	
				this.cases[i][j].changeProperty().addListener(
						(obs,oldValue,newValue)->{
							this.changeProperty.set(convertToCaseId(row, column));
						}
				);
			}
		}
		
		
	}
	
	public int getMapWidth() {
		if(this.cases.length > 0)
			return this.cases[0].length;
		else
			return 0;
	}
	
	public int getMapHeight() {
		return this.cases.length;
	}
	
	
	public Background getBackground(int row,int column) {
		return this.cases[row][column].getBackground();
	}
	
	public StringProperty personnageImageNameProperty(int keyPerso) {
		return this.listePerso.get(keyPerso).representationImageProperty();
	}
	
	public char getCaseChangeCategory(int caseIndex) {
		if(caseIndex == TRASHINDEX) {
			return this.trash.getChangeCategory();
		}
		int[] coordonnees = this.convertFromCaseId(caseIndex);
		int row = coordonnees[ROWINDEX];
		int column = coordonnees[COLUMNINDEX];
		return this.cases[row][column].getChangeCategory();
	}
	
	
	public IntegerProperty changeProperty() {
		this.safeProperty.unbind();
		this.safeProperty.bind(this.changeProperty);
		return this.safeProperty;
	}
	
	void addPersonnage(Personnage p) {
		boolean set = false;
		Double d;
		while(!set) {
			d = Math.random() * this.getMapHeight();
			int startX = d.intValue() ;
			d = Math.random() * this.getMapWidth() ;
			int startY = d.intValue();
			set = this.addToList(p, startX, startY);
		}
	}
	
	
	
	public void etablirDeplacement(Personnage p,Deplacement deplacement) {
		setLastPersonnage(p);
		if(this.idPersonnage != INVALIDINDEX) {	
			int[] coordonneesDepart = getCurrentPlace(p);
			System.out.println(coordonneesDepart[0] + "->" + coordonneesDepart[1]);
			int[] coordonneesArrive = {coordonneesDepart[ROWINDEX] + deplacement.verticaly,coordonneesDepart[COLUMNINDEX] + deplacement.horizontaly};
			this.deplacerPersonnage(p,coordonneesDepart,coordonneesArrive);
		}else {
			throw new IllegalArgumentException();
		}
	}
	
	
	
	private void deplacerPersonnage(Personnage p, int[] depart,int[] arrive){
		
		int xi = depart[ROWINDEX];
		int yi = depart[COLUMNINDEX];
		int xf = arrive[ROWINDEX];
		int yf = arrive[COLUMNINDEX];
		try {
			Case caseCourante = this.cases[xi][yi];
			Case caseArrivee = this.cases[xf][yf];
			if(caseArrivee.estVide()) {
				caseCourante.viderCase();
				caseArrivee.ajouterPersonnage(p);
			}
			else
				throw new IllegalArgumentException("Case non vide");
		}catch(ArrayIndexOutOfBoundsException e) {
			throw new IllegalArgumentException("Limite atteinte");
		}
	}
	
	
	private int[] getCurrentPlace(Personnage p) {
		for(int i = 0 ; i < this.getMapHeight();i++) {
			for(int j = 0 ; j < this.getMapWidth();j++) {
				if(this.cases[i][j].contientPersonnage(p)) {
					int[] coordonnees = {i,j};
					return coordonnees;
				}
			}
		}
		throw new IllegalArgumentException();
	}
	
	private void setLastPersonnage(Personnage p) {
		this.idPersonnage = this.getPersoIndex(p);
	}
	
	public int getPersoIndex(Personnage p) {
		for(Integer key : this.listePerso.keySet()) {
			if(this.listePerso.get(key) == p) {
				return key;
			}
		}
		return INVALIDINDEX;
	}
	
	public int getLastChangeId() {
		return this.idPersonnage;
	}
	
	public Integer[] getListePersoKeys() {
		return this.listePerso.keySet().toArray(new Integer[this.listePerso.keySet().size()]);
	}
	
	public StringProperty getPersonnageStringProperty(Integer key) {
		return this.listePerso.get(key).representationImageProperty();
	}
	
	public char getLastChangeType(int caseId) {
		int[] coordonnees =  this.convertFromCaseId(caseId);
		int row = coordonnees[0];
		int column = coordonnees[1];
		return this.cases[row][column].getChangeCategory();
	}
	
	public int[] convertFromCaseId(int caseId) {
		int row = caseId / this.getMapWidth();
		int column = caseId % this.getMapWidth();
		int[] converted = {row,column};
		return converted;
	}
	
	public int convertToCaseId(int row,int column) {
		return row * this.getMapWidth() + column;
	}
	
	public Personnage getPersonnageByKey(Integer key) {
		return this.listePerso.get(key);
	}
	
}
