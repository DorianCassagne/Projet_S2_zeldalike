package model.PathFinder;

import java.util.ArrayList;

import model.PathFinder.resources.CellBfs;
import model.gameMap.GameMap;
import model.gameMap.additional.MapReader;

public final class BFS1 {
	public final static int simpleMove(GameMap map, int cellStart, int cellEnd) {
		boolean[] seenMap= new boolean[MapReader.MAPLENGTH*MapReader.MAPLENGTH];
		
		ArrayList<CellBfs> all=new ArrayList<CellBfs>();
		ArrayList<CellBfs> last=new ArrayList<CellBfs>();
		last.add(new CellBfs(null, cellStart));
		//map.isWalkableAt(cellStart);
		CellBfs find = null;
		
		do{
			ArrayList<CellBfs> empty=new ArrayList<CellBfs>();
			find= checkAroud(last, empty, seenMap, map, cellEnd);
			all.addAll(empty);
			last=empty;
			if (last.isEmpty())
				break;
		}while (find==null && !last.isEmpty());
		if (last.isEmpty())
			return cellStart;
		if (find.getLast()==null)
			return find.getIdCell();
		while(find.getLast().getLast()==null){
			find=find.getLast();
		}
		return find.getIdCell();
			
		
		
//		last.add(new CellBfs(null, xStart, yStart));
//		//seenMap[xStart][yStart]=true;
//		CaseBfs victory=null;
//		
//		do{
//			ArrayList<CaseBfs> empty=new ArrayList<CaseBfs>();
//			victory= check(last, empty, seenMap, map,maxX,maxY  );
//			all.addAll(empty);
//			last=empty;
//			if (last.isEmpty())
//				break;
//		}while (victory==null);
//		if(victory==null)
//			return "x";
//		if (victory.last!=null)
//			while(victory.last.last!=null) {
//				victory=victory.last;
//			}
//		if (victory.x == xStart) {
//			if(victory.y<yStart) {
//				return "O";
//			}
//			if(victory.y>yStart) {
//				return "E";
//			}
//		}
//		else {
//			if(victory.x<xStart) {
//				return "N";
//			}
//			if(victory.x>xStart) {
//				return "S";
//			}
//		}
//		System.out.println("erreur bfs");
//		return "x";
//		
//		
//	}
//	
//	private final static CaseBfs check(ArrayList<CaseBfs> last,ArrayList<CaseBfs> empty , boolean[][] seenMap,Map map,int maxX,int maxY) {
//
//		CaseBfs victory=null;
//		for (CaseBfs sommet: last) {
//			empty.add(checkCase(sommet, sommet.x-1, sommet.y, map, seenMap, maxX, maxY));
//			if (map.getCase(sommet.x-1, sommet.y).haveFruit()) {
//				victory=new CaseBfs(sommet,sommet.x-1, sommet.y);
//			}
//			empty.add(checkCase(sommet, sommet.x+1, sommet.y, map, seenMap, maxX, maxY));
//			if (map.getCase(sommet.x-1, sommet.y).haveFruit()) {
//				victory=new CaseBfs(sommet,sommet.x-1, sommet.y);
//			}
//			empty.add(checkCase(sommet, sommet.x, sommet.y-1, map, seenMap, maxX, maxY));
//			if (map.getCase(sommet.x, sommet.y-1).haveFruit()) {
//				victory=new CaseBfs(sommet,sommet.x, sommet.y-1);
//			}
//			empty.add(checkCase(sommet, sommet.x, sommet.y+1, map, seenMap, maxX, maxY));
//			if (map.getCase(sommet.x, sommet.y+1).haveFruit()) {
//				victory=new CaseBfs(sommet, sommet.x, sommet.y+1);
//			}
//		}
//		
//		return victory;
//	}
//	private final static CaseBfs checkCase(CaseBfs lastCase,int x, int y, Map map, boolean[][] seenMap,int maxX,int maxY) {
//		if (x<0 || y<0||x>=maxX || y>=maxY)
//			return null;
//		if(!seenMap[x][y]) {
//			seenMap[x][y]=true;
//			if (map.getCase(maxX, maxY).isWalkable()){
//				return new CaseBfs(lastCase, x, y);
//			}
//				
//		}
//		return null;
//	}
//	
		
	}
	
	
	
	private final static CellBfs checkAroud(ArrayList<CellBfs> last,ArrayList<CellBfs> empty , boolean[] seenMap,GameMap map, int cellEnd) {

		for (CellBfs sommet: last) {
			CellBfs check;
			if (sommet.getIdCell()+1==cellEnd)
				return sommet;
			check= checkCase(sommet, map, seenMap, sommet.getIdCell()+1);
			if (check!=null)
				empty.add(check);
			if (sommet.getIdCell()-1==cellEnd)
				return sommet;
			check= checkCase(sommet, map, seenMap, sommet.getIdCell()-1);
			if (check!=null)
				empty.add(check);
			if (sommet.getIdCell()+MapReader.MAPLENGTH==cellEnd)
				return sommet;
			check= checkCase(sommet, map, seenMap, sommet.getIdCell()+MapReader.MAPLENGTH);
			if (check!=null)
				empty.add(check);
			if (sommet.getIdCell()-MapReader.MAPLENGTH==cellEnd)
				return sommet;
			check= checkCase(sommet, map, seenMap, sommet.getIdCell()-MapReader.MAPLENGTH);
			if (check!=null)
				empty.add(check);
		}
		
		return null;
	}
	
	private final static CellBfs checkCase(CellBfs lastCase, GameMap map, boolean[] seenMap, int id) {
	if (id<0 || id>=MapReader.MAPLENGTH*MapReader.MAPLENGTH )
		return null;
	if(!seenMap[id]) {
		seenMap[id]=true;
		if (map.isWalkableAt(id)){
			return new CellBfs(lastCase, id);
		}
			
	}
	return null;
}

}

