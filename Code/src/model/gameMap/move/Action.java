package model.gameMap.move;
/*
 * Classe Action 
 * contient les methodes d'ajouts et de deletion de Movable avec leur listes
 */
import java.util.ArrayList;

import java.util.HashMap;
import model.character.Movable;
import model.character.PendingMovable;
import model.character.attack.Attack;
import model.gameMap.additional.NewMovable;
import model.gameMap.additional.Statics;

public class Action {
	private HashMap<Movable,Integer> movableList;
	private ArrayList<NewMovable> addedCharacter;//Caracteres qui sont ajoute recemment mais pas encore recupere
	private ArrayList<Integer> removedMovable;//Caractere qui seront retire au prochain tour
	private ArrayList<PendingMovable> pendingMovable;//Caractïeres qui vont etre ajoute au prochain tour
	private ArrayList<Move> pendingMoves;
	private int movableId;
	
	public Action() {
		this.movableId = 0;
		this.movableList = new HashMap<Movable,Integer>();
		this.addedCharacter = new ArrayList<NewMovable>();
		this.removedMovable = new ArrayList<Integer>();
		this.pendingMovable = new ArrayList<PendingMovable>();
		this.pendingMoves = new ArrayList<Move>();
	}
	
	
	public void addMove(int endRow,int endColumn,int speed,Movable character) {
		if(this.movableList.containsKey(character)) {
			character.setCellId(endRow, endColumn);
			Move newMove = new Move(Statics.convertToCellId(endRow, endColumn),speed);
			newMove.setMovableId(this.movableList.get(character));
		}
	}
		
	//Renvoie la liste des movements effectues pendant un tour
	synchronized public Move[] turn() {
		updateMovableList();
		ArrayList<Move> moves = executeTurn();
		moves.addAll(this.pendingMoves);
		this.pendingMoves.clear();
		Move[] movesArray = new Move[moves.size()];
		movesArray = moves.toArray(movesArray);
		return movesArray;
	}
	
	//Renvoie la liste des nouveaux caractïeres introduit dans le jeu
	public NewMovable[] getNewCharList() {
		NewMovable[] newChars = new NewMovable[this.addedCharacter.size()];
		newChars = this.addedCharacter.toArray(newChars);
		this.addedCharacter.clear();
		return newChars;
	}
	

	
	public int[] getRemovedCharacter() {
		int[] removedCharacter = new int[this.removedMovable.size()];
		for(int i = 0 ; i < removedCharacter.length;i++) {
			removedCharacter[i] = this.removedMovable.get(i);		
		}
		this.removedMovable.clear();
		return removedCharacter;
	}
	
	/*
	 * Cette methode ajoute un deplacable a la liste des deplacable en lui attribuant un identifiant unique
	 */
	public void addMovable(Movable movable,int cellId) {
		this.pendingMovable.add(new PendingMovable(movable,cellId,movable.getImageValueProperty()));
	}
	
	public void delAttack(Attack attack) {
		this.deleteMovableFromList(attack);
	}
	
	public boolean deleteMovableFromList(Movable movable) {
		boolean deleted = this.movableList.containsKey(movable);
		if(deleted) {
			this.pendingMovable.add(new PendingMovable(movable));
		}
		return deleted;
	}
	
	
	
	/*
	 * Identifie un movableEnAttente et applique les actions suivant les conditions suivants : 
	 * -Si Le deplacable est deja sur la map, on l'enleve
	 * -Si le deplacable n'est pas deja dans la map, on l'ajoute
	 */
	
	private void identifieMovable(PendingMovable pending) {
		Movable currentMovable = pending.getMovable();
		if(this.movableList.containsKey(currentMovable)){
			this.removedMovable.add(this.movableList.get(currentMovable));
			this.movableList.remove(currentMovable);
		}
		else {
			this.movableList.put(pending.getMovable(), this.movableId);
			this.addedCharacter.add(new NewMovable(movableId, pending.getCellId(),pending.getImageProperty()));
			this.movableId++;
		}
	}

	
	//Met a jour la liste des deplacable en les ajoutants a la hashmap des deplacable courrants 
	private void updateMovableList() {
		for(PendingMovable movable : this.pendingMovable) {
			identifieMovable(movable);
		}
		this.pendingMovable.clear();
	}
	
	/*
	 * Fait jouer un tour a tous les deplacable sur la map et renvoie la liste de leurs actions
	 */
	private ArrayList<Move> executeTurn() {
		ArrayList<Move> moves = new ArrayList<Move>();
		Move move;
		for(Movable movable : this.movableList.keySet()) {
			move = movable.turn();
			if(move != null) {
				move.setMovableId(this.movableList.get(movable));
				moves.add(move);
			}
		}
		return moves;
	}


}
