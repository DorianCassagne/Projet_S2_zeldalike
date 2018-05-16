package model.map.cell.background;

public abstract class BackgroundFactory {
	
	final static int[] WALKABLEVALUES= {3,5,8};
	public static boolean getWalkableImgVal(int imgVal) {
		boolean walkable=false;
		for (int i : WALKABLEVALUES) {
			if (imgVal==i)
				walkable=true;
		}
		return walkable;
	}
}
