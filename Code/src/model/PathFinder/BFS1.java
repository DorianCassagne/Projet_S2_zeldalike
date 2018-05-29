package model.PathFinder;

import java.util.ArrayList;
import java.util.Random;
import model.PathFinder.resources.CellBfs;
import model.gameMap.GameMap;
import model.gameMap.additional.MapReader;
public final class BFS1 {
	
	private static boolean random;
	private final static Random ran=  new Random();

	public final static int simpleMove(GameMap map, int cellStart, int cellEnd) {
		boolean[] seenMap= new boolean[MapReader.MAPLENGTH*MapReader.MAPLENGTH];
		random=ran.nextBoolean();
		ArrayList<CellBfs> all=new ArrayList<CellBfs>();
		ArrayList<CellBfs> last=new ArrayList<CellBfs>();
		last.add(new CellBfs(null, cellStart));
		CellBfs find = null;
		
		do{
			ArrayList<CellBfs> empty=new ArrayList<CellBfs>();
			find= checkAroud(last, empty, seenMap, map, cellEnd);
			all.addAll(empty);
			last=empty;
			if (last.isEmpty())
				break;
		}while (find==null && !last.isEmpty());
		if (find==null) {
			System.out.println("no way to join him");
			return cellStart;
		}
		if (find.getLast()==null)
			return find.getIdCell();
		while(find.getLast().getLast()!=null){
			find=find.getLast();
		}
		return find.getIdCell();
					
	}
	
	
	
	private final static CellBfs checkAroud(ArrayList<CellBfs> last,ArrayList<CellBfs> empty , boolean[] seenMap,GameMap map, int cellEnd) {

		for (CellBfs sommet: last) {
			CellBfs check;
			for (int i = 0; i<2 ;i++) {//metre i<1 pour un deplacement bizzare mais se raprochant de la case
			if(random) {

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
				}
					
				if(!random) {
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
				random=!random;
				//System.out.println("test"+random);
			}
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

