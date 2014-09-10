/*
 * @ - Author: Abhishek Rane
 * @ - Author: Bryce Hammond
 */

/*
 * This object hold the state of each cell or room in the map. 
 */
public class Cell {

	private boolean hiddenRoom;
	private boolean slime;
	private boolean blood;
	private boolean pit;
	private boolean wumpus;
	private boolean hunter;

	/*
	 * Default constructor. Everything should be false except hiddenRoom.
	 */
	public Cell() {
		this.hiddenRoom = true;
	}
	
	/*
	 * Getter for hidden room.
	 */
	public boolean getHiddenRoom() {
		return hiddenRoom;
	}
	
	/*
	 * Getter for slime.
	 */
	public boolean getSlime() {
		return slime;
	}
	
	/*
	 * Getter for blood.
	 */
	public boolean getBlood() {
		return blood;
	}
	
	/*
	 * Getter for pit.
	 */
	public boolean getPit() {
		return pit;
	}
	
	/*
	 * Checks if the room is empty.
	 */
	public boolean getIsEmpty() {
		return !slime && !blood && !pit && !wumpus;
	}
	
	/*
	 * Checks if the room has a wumpus.
	 */
	public boolean getWumpus() {
		return wumpus;
	}
	
	/*
	 * Checks if the room has goop.
	 */
	public boolean getGoop() {
		return slime && blood;
	}

	/*
	 * Checks if the room has a hunter
	 */
	public boolean getHunter() {
		return hunter;
	}
	
	/*
	 * Setter for hiddenRoom.
	 */
	public void setHiddenRoom(Boolean hiddenRoom) {
		this.hiddenRoom = hiddenRoom;
	}

	/*
	 * Setter for slime.
	 */
	public void setSlime(Boolean slime) {
		this.slime = slime;
	}

	/*
	 * Setter for blood.
	 */
	public void setBlood(Boolean blood) {
		this.blood = blood;
	}

	/*
	 * Setter for pit.
	 */
	public void setPit(Boolean pit) {
		this.pit = pit;
		this.blood = false;
	}

	/*
	 * Setter for wumpus.
	 */
	public void setWumpus(Boolean wumpus) {
		this.wumpus = wumpus;
	}

	/*
	 * Setter for hunter.
	 */
	public void setHunter(boolean hunter) {
		this.hunter = hunter;
	}

}
