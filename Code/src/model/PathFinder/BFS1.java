package model.PathFinder;

import java.util.ArrayList;
import java.util.Random;
import model.PathFinder.resources.CellBfs;
import model.gameMap.GameMap;
import model.gameMap.additional.MapReader;
public final class BFS1 {
	
	private static boolean random;
	private final static Random ran=  new Random();
	
	
	public final static int simpleMove(GameMap map, int cellStart, int cellEnd, boolean joinTheCell) {
		int[] tab ={cellEnd};
		return simpleMove(map, cellStart, tab, joinTheCell);
				
}


//	
//	
//	
//	
//	
//	private final static CellBfs checkAroud(ArrayList<CellBfs> last,ArrayList<CellBfs> empty , boolean[] seenMap,GameMap map, int cellEnd) {
//
//		for (CellBfs sommet: last) {
//			CellBfs check;
//			for (int i = 0; i<2 ;i++) {//metre i<1 pour un deplacement bizzare mais se raprochant de la case
//			if(random) {
//
//					if (sommet.getIdCell()+1==cellEnd) 
//						return getCase(sommet, map, seenMap, sommet.getIdCell()+1);
//					check= checkCase(sommet, map, seenMap, sommet.getIdCell()+1);
//					if (check!=null)
//						empty.add(check);
//						
//					
//					
//
//					if (sommet.getIdCell()-1==cellEnd)
//						return getCase(sommet, map, seenMap, sommet.getIdCell()-1);
//					check= checkCase(sommet, map, seenMap, sommet.getIdCell()-1);
//					if (check!=null)
//						empty.add(check);
//				}
//					
//				if(!random) {
//					if (sommet.getIdCell()+MapReader.MAPLENGTH==cellEnd)
//						return getCase(sommet, map, seenMap, sommet.getIdCell()+MapReader.MAPLENGTH);
//					check= checkCase(sommet, map, seenMap, sommet.getIdCell()+MapReader.MAPLENGTH);
//					if (check!=null)
//						empty.add(check);
//					
//
//					if (sommet.getIdCell()-MapReader.MAPLENGTH==cellEnd)
//						return getCase(sommet, map, seenMap, sommet.getIdCell()-MapReader.MAPLENGTH);
//					check= checkCase(sommet, map, seenMap, sommet.getIdCell()-MapReader.MAPLENGTH);
//					if (check!=null)
//						empty.add(check);
//					
//				}
//				random=!random;
//				//System.out.println("test"+random);
//			}
//		}
//		
//		return null;
//	}
	
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
	private final static CellBfs getCase(CellBfs lastCase, GameMap map, boolean[] seenMap, int id) {
		if (id<0 || id>=MapReader.MAPLENGTH*MapReader.MAPLENGTH )
			return null;
		if(!seenMap[id]) {
			seenMap[id]=true;
				return new CellBfs(lastCase, id);	
		}
		return null;
	}
	
	
	
//	public final static int simpleMove(GameMap map, int cellStart, int cellEnd) {
//	if(cellEnd==cellStart)
//		return cellStart;
//	boolean[] seenMap= new boolean[MapReader.MAPLENGTH*MapReader.MAPLENGTH];
//	random=ran.nextBoolean();
//	ArrayList<CellBfs> all=new ArrayList<CellBfs>();
//	ArrayList<CellBfs> last=new ArrayList<CellBfs>();
//	last.add(new CellBfs(null, cellStart));
//	CellBfs find = null;
//	
//	do{
//		ArrayList<CellBfs> empty=new ArrayList<CellBfs>();
//		find= checkAroud(last, empty, seenMap, map, cellEnd);
//		all.addAll(empty);
//		last=empty;
//		if (last.isEmpty())
//			break;
//	}while (find==null && !last.isEmpty());
//	if (find==null) {
//		System.out.println("no way to join him");
//		return cellStart;
//	}
//	if (find.getLast()==null)
//		return find.getIdCell();
//	while(find.getLast().getLast()!=null){
//		find=find.getLast();
//	}
//	return find.getIdCell();
//				
//}
//
	//beacoup de code duplique TODO:retirer duplication
	public final static int simpleMove(GameMap map, int cellStart, int[] cellEnd, boolean joinTheCell) {
		for (int i : cellEnd) {
			if(i==cellStart)
				return cellStart;
		}
		
		boolean[] seenMap= new boolean[MapReader.MAPLENGTH*MapReader.MAPLENGTH];
		random=ran.nextBoolean();
		ArrayList<CellBfs> all=new ArrayList<CellBfs>();
		ArrayList<CellBfs> last=new ArrayList<CellBfs>();
		last.add(new CellBfs(null, cellStart));
		CellBfs find = null;
		
		do{
			ArrayList<CellBfs> empty=new ArrayList<CellBfs>();
			find= checkAroud(last, empty, seenMap, map, cellEnd ,joinTheCell);
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


	private static CellBfs checkSingleCell( ArrayList<CellBfs> empty,CellBfs sommet, GameMap map, boolean[] seenMap, int[] cellEnd,boolean joinTheCell, int increment) {
		if(!joinTheCell) {
			for (int i2 : cellEnd) {
				if (sommet.getIdCell()+increment==i2) {
					CellBfs check= checkCase(sommet, map, seenMap, sommet.getIdCell()+ increment);
					if (check!=null) {
						return check;
					}
					//return getCase(sommet, map, seenMap,sommet.getIdCell()+increment);
					return sommet;
				}
			}
		}
		
		CellBfs check= checkCase(sommet, map, seenMap, sommet.getIdCell()+ increment);
		if (check!=null) {
			empty.add(check);
			if (joinTheCell) {
				for (int i2 : cellEnd) {
					if (sommet.getIdCell()+increment==i2) 
						return check;
				}
			}
		}
		return null;
	}

//	if (sommet.getIdCell()+MapReader.MAPLENGTH==cellEnd)
//	return getCase(sommet, map, seenMap, sommet.getIdCell()+MapReader.MAPLENGTH);
//check= checkCase(sommet, map, seenMap, sommet.getIdCell()+MapReader.MAPLENGTH);
//if (check!=null)
//	empty.add(check);
//
//


	private static CellBfs checkAroud(ArrayList<CellBfs> last, ArrayList<CellBfs> empty, boolean[] seenMap, GameMap map,int[] cellEnd, boolean joinTheCell) {
		for (CellBfs sommet: last) {
			CellBfs check=null;
			for (int i = 0; i<2 ;i++) {//metre i<1 pour un deplacement bizzare mais se raprochant de la case
				if(random) {
	
					check=checkSingleCell(empty, sommet, map, seenMap, cellEnd,joinTheCell, +1);
					if (check!=null)
						return check;
					check=checkSingleCell(empty, sommet, map, seenMap, cellEnd,joinTheCell, -1);
//					check= checkCase(sommet, map, seenMap, sommet.getIdCell()+1);
//					if (check!=null) {
//						empty.add(check);
//						for (int i2 : cellEnd) {
//							if (sommet.getIdCell()+1==i2) 
//								return check;
//						}
//					}
					
					
	
//					
//					check= checkCase(sommet, map, seenMap, sommet.getIdCell()-1);
//					if (check!=null) {
//						empty.add(check);
//						for (int i2 : cellEnd) {
//							if (sommet.getIdCell()-1==i2)
//								return check;
//						}
//					}
				}
						
				if(!random) {
					

					check=checkSingleCell(empty, sommet, map, seenMap, cellEnd,joinTheCell, +MapReader.MAPLENGTH);
					if (check!=null)
						return check;
					check=checkSingleCell(empty, sommet, map, seenMap, cellEnd,joinTheCell, -MapReader.MAPLENGTH);
				
					
//					check= checkCase(sommet, map, seenMap, sommet.getIdCell()+MapReader.MAPLENGTH);
//					if (check!=null) {
//						empty.add(check);
//						for (int i2 : cellEnd) {
//							if (sommet.getIdCell()+MapReader.MAPLENGTH==i2)
//								return check;
//						}
//					}
//					
//					
//					check= checkCase(sommet, map, seenMap, sommet.getIdCell()-MapReader.MAPLENGTH);
//					if (check!=null) {
//						empty.add(check);
//						for (int i2 : cellEnd) {
//							if (sommet.getIdCell()-MapReader.MAPLENGTH==i2)
//								return check;
//						}
//					}
				}
				random=!random;
				//System.out.println("test"+random);

				if (check!=null)
					return check;
			}
		
		}
		
		return null;

	}
}

