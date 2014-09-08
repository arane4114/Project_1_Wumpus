// test push
// test 2
public class Cell {

	private boolean hiddenRoom;
	private boolean slime;
	private boolean blood;
	private boolean pit;
	private boolean wumpus;
	private boolean hasHunter;
	
	public Cell(boolean hiddenRoom, boolean slime, boolean blood, boolean pit, boolean wumpus) {
		this.hiddenRoom = hiddenRoom;
		this.slime = slime;
		this.blood = blood;
		this.pit = pit;
		this.wumpus = wumpus;
		this.hasHunter = false;
		
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
	
	public void setHiddenRoom(boolean hiddenRoom){
		this.hiddenRoom = hiddenRoom;
	}
	
	public void setSlime(boolean slime){
		this.slime = slime;
	}
	
	public void setBlood(boolean blood){
		this.blood = blood;
	}
	
	public void setPit(boolean pit){
		this.pit = pit;
	}
	
	public void setWumpus(boolean wumpus){
		this.wumpus = wumpus;
	}
	
	public void setHasHunter(boolean hasHunter){
		this.hasHunter = hasHunter;
	}
	
	public boolean getHasHunter(){
		return this.hasHunter;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
