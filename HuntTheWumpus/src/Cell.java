// test push
public class Cell {

	private Boolean hiddenRoom;
	private Boolean slime;
	private Boolean blood;
	private Boolean pit;
	private Boolean wumpus; 
	
	
	public Cell(Boolean hiddenRoom, Boolean slime, Boolean blood, Boolean pit, Boolean wumpus) {
		this.hiddenRoom = hiddenRoom;
		this.slime = slime;
		this.blood = blood;
		this.pit = pit;
		this.wumpus = wumpus;
		
	}
	
	public Boolean getHiddenRoom(){
		return hiddenRoom;
	}
	
	public Boolean getSlime(){
		return slime;
	}
	
	public Boolean getBlood(){
		return blood;
	}
	
	public Boolean getPit(){
		return pit;
	}
	
	public Boolean getNothing(){
		return !slime && !blood && !pit;
	}
	
	public Boolean getWumpus(){
		return wumpus;
	}
	
	public Boolean getGoop(){
		return slime && blood;
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

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
