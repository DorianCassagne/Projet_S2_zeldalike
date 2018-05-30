package model.PathFinder.resources;

public class CellBfs {
	private CellBfs last;
	private int idCell;
	
	public CellBfs(CellBfs last, int idCell) {
		this.setLast(last);
		this.setIdCell(idCell);
	}

	public int getIdCell() {
		return idCell;
	}

	private void setIdCell(int idCell) {
		this.idCell = idCell;
	}

	public CellBfs getLast() {
		return last;
	}

	private void setLast(CellBfs last) {
		this.last = last;
	}
}
