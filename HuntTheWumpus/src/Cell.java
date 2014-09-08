// test push
// test 2
public class Cell {

	private boolean hiddenRoom;
	private boolean slime;
	private boolean blood;
	private boolean pit;
	private boolean wumpus; 
	private boolean hunter;
	
	
//	public Cell(Boolean hiddenRoom, Boolean slime, Boolean blood, Boolean pit, Boolean wumpus) {
//		this.hiddenRoom = hiddenRoom;
//		this.slime = slime;
//		this.blood = blood;
//		this.pit = pit;
//		this.wumpus = wumpus;
//		
//	}
	
	public Cell(){
		this.hiddenRoom = true;
	}
	
	public boolean getHiddenRoom(){
		return hiddenRoom;
	}
	
	public boolean getSlime(){
		return slime;
	}
	
	public boolean getBlood(){
		return blood;
	}
	
	public boolean getPit(){
		return pit;
	}
	
	public boolean getNothing(){
		return !slime && !blood && !pit;
	}
	
	public boolean getWumpus(){
		return wumpus;
	}
	
	public boolean getGoop(){
		return slime && blood;
	}
	
	public boolean getHunter(){
		return hunter;
	}
	
	public void setHiddenRoom(Boolean hiddenRoom){
		this.hiddenRoom = hiddenRoom;
	}
	
	public void setSlime(Boolean slime){
		this.slime = slime;
	}
	
	public void setBlood(Boolean blood){
		this.blood = blood;
	}
	
	public void setPit(Boolean pit){
		this.pit = pit;
	}
	
	public void setWumpus(Boolean wumpus){
		this.wumpus = wumpus;
	}

	
	public void setHunter(boolean hunter){
		this.hunter = hunter;
	}
	
	
	
	
	
	// Change to what you want it to say
	public String whatsThere(){
		String whatsThere;
		if(getGoop()){
			whatsThere = "Goop";
		}else if(getBlood()){
			whatsThere = "Blood";
		}else if(getSlime()){
			whatsThere = "Slime";
		}else{
			whatsThere = "Nothing";
		}
	return whatsThere;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
