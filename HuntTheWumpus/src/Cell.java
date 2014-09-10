/*
 * @ - Author: Abhishek Rane
 * @ - Author: Bryce Hammond
 */
public class Cell {

	private boolean hiddenRoom;
	private boolean slime;
	private boolean blood;
	private boolean pit;
	private boolean wumpus;
	private boolean hunter;

	public Cell() {
		this.hiddenRoom = true;
	}

	public boolean getHiddenRoom() {
		return hiddenRoom;
	}

	public boolean getSlime() {
		return slime;
	}

	public boolean getBlood() {
		return blood;
	}

	public boolean getPit() {
		return pit;
	}

	public boolean getIsEmpty() {
		return !slime && !blood && !pit && !wumpus;
	}

	public boolean getWumpus() {
		return wumpus;
	}

	public boolean getGoop() {
		return slime && blood;
	}

	public boolean getHunter() {
		return hunter;
	}

	public void setHiddenRoom(Boolean hiddenRoom) {
		this.hiddenRoom = hiddenRoom;
	}

	public void setSlime(Boolean slime) {
		this.slime = slime;
	}

	public void setBlood(Boolean blood) {
		this.blood = blood;
	}

	public void setPit(Boolean pit) {
		this.pit = pit;
		this.blood = false;
	}

	public void setWumpus(Boolean wumpus) {
		this.wumpus = wumpus;
	}

	public void setHunter(boolean hunter) {
		this.hunter = hunter;
	}

}
