import java.util.Random;


public class Map {
	
	public Cell[][] map = new Cell[10][10];
	//public Hunter hunter;
	
	public Map(){
		createMap();
		//Hunter hunter = new Hunter();			
	}
	
	public String toString(){
		String toString = "";
		for(int i =  0; i < map.length; i ++){
			for(int j = 0; j < map[i].length; j ++){
				if(map[i][j].getHunter()){				//// -------
					toString += " [O]";
				}else if(map[i][j].getHiddenRoom()){
					toString += " [X]";
				}else if(map[i][j].getGoop()){
					toString += " [G]";
				}else if(map[i][j].getBlood()){
					toString += " [B]";
				}else if(map[i][j].getSlime()){
					toString += " [S]";
				}else if(map[i][j].getPit()){
					toString += " [P]";
				}else if(map[i][j].getWumpus()){
					toString += " [W]";
				}else{
					toString += " [ ]";
				}
			}
			if(i != map.length - 1){
				toString += "\n";
			}
		}
	return toString;
	}
	
	// Need to place Hunter
	public void createMap(){
		
		for(int i = 0; i < map.length; i ++){
			for(int j = 0; j < map[i].length; j ++){
				map[i][j] = new Cell();
			}
		}													
		
		Random rand = new Random();
		int pits = rand.nextInt(3) + 3;
		int pitsPlaced = 0;
		boolean hunterPlaced = false;
		
		setWumpus(rand.nextInt(map.length), rand.nextInt(map.length));
		
		while(pits != pitsPlaced){
			int i = rand.nextInt(map.length);
			int j = rand.nextInt(map.length);
			if(!map[i][j].getWumpus() && !map[i][j].getPit()){
				setPit(i, j);
			}
		}
		
		while(hunterPlaced){
			int i = rand.nextInt(map.length);
			int j = rand.nextInt(map.length);
			if(!map[i][j].getWumpus() && !map[i][j].getPit() && !map[i][j].getSlime() && !map[i][j].getBlood()){
				// Place Hunter With Meathod
				//hunter.setLocation(j, i);
				map[i][j].setHiddenRoom(false);
				hunterPlaced = true;
			}
			
		}
		
		
		
	}
	
	public void setPit(int i, int j){
		map[i][j].setPit(true);
		map[i][wrapAround(j - 1)].setSlime(true);
		map[wrapAround(i - 1)][j].setSlime(true);
		map[i][wrapAround(j + 1)].setSlime(true);
		map[wrapAround(i + 1)][j].setSlime(true);
	}
	
	public void setWumpus(int i, int j){
		map[i][j].setWumpus(true);
		
		map[i][wrapAround(j - 1)].setBlood(true);
		map[i][wrapAround(j - 2)].setBlood(true);
		
		map[wrapAround(i - 1)][wrapAround(j - 1)].setBlood(true);
		map[wrapAround(i - 2)][wrapAround(j - 2)].setBlood(true);
		
		map[wrapAround(i - 1)][j].setBlood(true);
		map[wrapAround(i - 2)][j].setBlood(true);
		
		map[wrapAround(i - 1)][wrapAround(j + 1)].setBlood(true);
		map[wrapAround(i - 2)][wrapAround(j + 2)].setBlood(true);
		
		map[i][wrapAround(j + 1)].setBlood(true);
		map[i][wrapAround(j + 2)].setBlood(true);
		
		map[wrapAround(i + 1)][wrapAround(j + 1)].setBlood(true );
		map[wrapAround(i + 2)][wrapAround(j + 2)].setBlood(true);
		
		map[wrapAround(i + 1)][j].setBlood(true);
		map[wrapAround(i + 2)][j].setBlood(true);
		
		map[wrapAround(i + 1)][wrapAround(j - 1)].setBlood(true);
		map[wrapAround(i + 2)][wrapAround(j - 2)].setBlood(true);
	}
	
	public int wrapAround(int i){
		if(i < map.length && i > -1){
			return i;
		}else if(i > map.length - 1){
			return i - (map.length - 1);
		}else{
			return i + map.length;
		}
	}
	
	
//	public boolean shotWumpus(boolean horizontal){
//		int x = hunter.getX();
//		int y = hunter.getY();
//		if(horizontal){
//			for(int i = 0; i < map[y].length; i ++){
//				if(map[y][i].getWumpus()){
//					return true;
//				}
//			}
//			
//		}else{
//			for(int i = 0; i < map.length; i ++){
//				if(map[i][x].getWumpus()){
//					return true;
//				}
//			}
//		}
//	return false;
//	}
	
	
}
