
public class Map {
	
	public Cell[][] map = new Cell[10][10];
	private Cell hunter;
	
	
	public String toString(){
		String toString = "";
		for(int i =  0; i < 10; i ++){
			for(int j = 0; j < 10; j ++){
				if(map[i][j].equals(hunter)){
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
			if(i != 9){
				toString += "\n";
			}
		}
	return toString;
	}
	
	
}
