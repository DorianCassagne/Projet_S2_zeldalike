package model.gameMap.move;

import java.util.ArrayList;

import java.util.HashMap;
import model.character.Movable;
import model.character.PendingMovable;
import model.character.attack.Attack;
import model.gameMap.additional.NewMovable;
import model.gameMap.additional.Statics;

public class Action {
	private HashMap<Movable,Integer> movableList;
	private ArrayList<NewMovable> addedCharacter;//Caract�res qui sont ajout�s r�cement mais pas encore r�cup�r�s
	private ArrayList<Integer> removedMovable;//Caract�re qui seront retir� au prochain tour
	private ArrayList<PendingMovable> pendingMovable;//Caract�res qui vont �tre ajout� au prochain tour
	private ArrayList<Move> pendingMoves;
	private int delay;
	private int movableId;
	
	public Action() {
		this.delay = 0;
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
		
	//Renvoie la liste des movements effectu�s pendant un tour
	synchronized public Move[] turn() {
		this.updateDelay();
		Move[] movesArray = new Move[0];
		
		if(this.delay > 0) {
			
			updateMovableList();
			ArrayList<Move> moves = executeTurn();
			moves.addAll(this.pendingMoves);
			this.pendingMoves.clear();
			movesArray = new Move[moves.size()];
			
			movesArray = moves.toArray(movesArray);
		}
		return movesArray;
	}
	
	private void updateDelay() {
		if(delay > 0)
			delay--;
	}
	
	//Renvoie la liste des nouveaux caract�res introduit dans le jeu
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
	 * Cette méthode ajoute un déplaçable � la liste des d�pla�able en lui attribuant un identifiant unique
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
	
	public void setNewDelay(int newDelay) {
		this.delay = newDelay;
	}
	
	
	
	/*
	 * Identifie un movableEnAttente et applique les actions suivant les conditions suivants : 
	 * -Si Le déplaçable est d�j� sur la map, on l'enl�ve
	 * -Si le déplaçable n'est pas d�j� dans la map, on l'ajoute
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

	
	//Met � jour la liste des d�pla�able en les ajoutants � la hashmap des d�pla�able courrants
	private void updateMovableList() {
		for(PendingMovable movable : this.pendingMovable) {
			identifieMovable(movable);
		}
		this.pendingMovable.clear();
	}
	
	/*
	 * Fait jouer un tour � tous les d�pla�able sur la map et renvoie la liste de leurs actions
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
