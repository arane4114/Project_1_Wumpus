import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Map {

	private Cell[][] map;
	private Point wumpusLocation;
	private Point hunterLocation;												// UPPPPPPPPPPDATE

	public Map() {
		map = new Cell[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				map[i][j] = new Cell();
			}
		}
	}

	public Cell getCell(Point point) {
		return map[point.x][point.y];
	}

	public void generatePits() {
		int numberOfPits = 3 +( (int) (Math.random() * ((5 - 3) + 1))); // Generates
																		// a
																		// number
																		// between
																		// 3 and
																		// 5
		ArrayList<Point> pitPoints = new ArrayList<Point>();
		int i = 1;
		while (i <= numberOfPits) {
			int x = (int) (Math.random() * ((9) + 1));
			int y = (int) (Math.random() * ((9) + 1));
			Point possibleNewPoint = new Point(x, y);
			Boolean occupied = false;
			for (Point point : pitPoints) {
				if (possibleNewPoint.equals(point)
						|| possibleNewPoint.equals(this.wumpusLocation)) {
					occupied = true;
					break;
				}
			}
			if (!occupied) {
				pitPoints.add(possibleNewPoint);
				i++;
			}
		}
		setPits(pitPoints);
	}

	/*
	 * Sets pits and goop with a set of known valid pit points locations. Allows
	 * for JUnit testing of a static map.
	 */
	public void setPits(ArrayList<Point> pitPoints) {
		for (Point point : pitPoints) {
			map[point.x][point.y].setPit(true);
			if (point.x == 0) {
				map[9][point.y].setSlime(true);
			} else {
				map[point.x - 1][point.y].setSlime(true);
			}

			if (point.x == 9) {
				map[0][point.y].setSlime(true);
			} else {
				map[point.x + 1][point.y].setSlime(true);
			}

			if (point.y == 0) {
				map[point.x][9].setSlime(true);
			} else {
				map[point.x][point.y - 1].setSlime(true);
				;
			}

			if (point.y == 9) {
				map[point.x][0].setSlime(true);
			} else {
				map[point.x][point.y + 1].setSlime(true);
			}
		}
	
		for (Point point : pitPoints) {
			System.out.println(point);
		}
	}

	public void generateWumpus() {
		setWumpus(new Point((int) (Math.random() * ((9) + 1)),
				(int) (Math.random() * ((9) + 1))));
	}

	/*
	 * Sets the location of a wumpus. Assumes that a valid wumpus location is
	 * passed.
	 */
	public void setWumpus(Point wumpusLocation) {
		this.wumpusLocation = wumpusLocation;
//		map[wumpusLocation.x][wumpusLocation.y].setWumpus(true);
//		if (wumpusLocation.x == 0) {
//			map[9][wumpusLocation.y].setBlood(true);
//		} else {
//			map[wumpusLocation.x - 1][wumpusLocation.y].setBlood(true);
//		}
//
//		if (wumpusLocation.x == 9) {
//			map[0][wumpusLocation.y].setBlood(true);
//		} else {
//			map[wumpusLocation.x + 1][wumpusLocation.y].setBlood(true);
//		}
//
//		if (wumpusLocation.y == 0) {
//			map[wumpusLocation.x][9].setBlood(true);
//		} else {
//			map[wumpusLocation.x][wumpusLocation.y - 1].setBlood(true);
//		}
//
//		if (wumpusLocation.y == 9) {
//			map[wumpusLocation.x][0].setBlood(true);
//			;
//		} else {
//			map[wumpusLocation.x][wumpusLocation.y + 1].setBlood(true);
//		}
		
		map[wumpusLocation.y][wumpusLocation.x].setWumpus(true);
		
		map[wumpusLocation.y][wrapAround(wumpusLocation.x - 1)].setBlood(true);
		map[wumpusLocation.y][wrapAround(wumpusLocation.x - 2)].setBlood(true);
		
		map[wrapAround(wumpusLocation.y - 1)][wrapAround(wumpusLocation.x - 1)].setBlood(true);
		
		map[wrapAround(wumpusLocation.y - 1)][wumpusLocation.x].setBlood(true);
		map[wrapAround(wumpusLocation.y - 2)][wumpusLocation.x].setBlood(true);
		
		map[wrapAround(wumpusLocation.y - 1)][wrapAround(wumpusLocation.x + 1)].setBlood(true);
		
		map[wumpusLocation.y][wrapAround(wumpusLocation.x + 1)].setBlood(true);
		map[wumpusLocation.y][wrapAround(wumpusLocation.x + 2)].setBlood(true);
		
		map[wrapAround(wumpusLocation.y + 1)][wrapAround(wumpusLocation.x + 1)].setBlood(true );
		
		map[wrapAround(wumpusLocation.y + 1)][wumpusLocation.x].setBlood(true);
		map[wrapAround(wumpusLocation.y + 2)][wumpusLocation.x].setBlood(true);
		
		map[wrapAround(wumpusLocation.y + 1)][wrapAround(wumpusLocation.x - 1)].setBlood(true);
	}
	
	public int wrapAround(int i){
		if(i < map.length && i > -1){
			return i;
		}else if(i > map.length - 1){
			return i - map.length;
		}else{
			return i + map.length;
		}
	}
	
	public void generateHunter(){																					// CHARGE HUNTER
		Random rand = new Random();
		boolean hunterPlaced = false;

		while(!hunterPlaced){
			int i = rand.nextInt(map.length);
			int j = rand.nextInt(map.length);
			if(!map[i][j].getWumpus() && !map[i][j].getPit() && !map[i][j].getSlime() && !map[i][j].getBlood()){	// CHECK CHECK
				this.hunterLocation = (new Point(j, i));
				map[i][j].setHiddenRoom(false);
				map[i][j].setHunter(true);
				hunterPlaced = true;
			}
		}
	}
	
	public void setHunter(Point hunt){																				// CHANGE HUNTER		
		int x = (int) hunt.getX();
		int y = (int) hunt.getY();
		map[hunterLocation.y][hunterLocation.x].setHunter(false);
		this.hunterLocation = (new Point(x, y));
		map[y][x].setHunter(true);
	}
	
	public boolean shootArrow(char x){																				// CHANGE HUNTER
		if(x == 'A' || x == 'D'){
			if(hunterLocation.y == wumpusLocation.y){
				return true;
			}
		}else if(x == 'W' || x == 'S'){
			if(hunterLocation.x == wumpusLocation.x){
				return true;
			}
		}
	return false;
	}
	
	public void hunterMove(char x){																					// CHENGE HUNTER
		if(x == 'w'){
			map[hunterLocation.y][hunterLocation.x].setHunter(false);
			hunterLocation.move(hunterLocation.x, wrapAround(hunterLocation.y - 1));
			map[hunterLocation.y][hunterLocation.x].setHunter(true);
		}else if(x == 's'){
			map[hunterLocation.y][hunterLocation.y].setHunter(false);
			hunterLocation.move(hunterLocation.x, wrapAround(hunterLocation.y + 1));
			map[hunterLocation.y][hunterLocation.x].setHunter(true);
		}else if(x == 'd'){
			map[hunterLocation.y][hunterLocation.x].setHunter(false);
			hunterLocation.move(wrapAround(hunterLocation.x + 1), hunterLocation.y);
			map[hunterLocation.y][hunterLocation.x].setHunter(true);
		}else if(x == 'a'){
			map[hunterLocation.y][hunterLocation.x].setHunter(false);
			hunterLocation.move(wrapAround(hunterLocation.x - 1), hunterLocation.y);
			map[hunterLocation.y][hunterLocation.x].setHunter(true);
		}
	}
	
	public String toString() {
		String toString = "";
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (i == hunterLocation.y && j == hunterLocation.x) {													
					toString += " [O]";
				} else if (map[i][j].getHiddenRoom()) {
					toString += " [X]";
				} else if (map[i][j].getPit()) {
					toString += " [P]";
				} else if (map[i][j].getWumpus()) {
					toString += " [W]";
				} else if (map[i][j].getGoop()) {
					toString += " [G]";
				} else if (map[i][j].getBlood()) {
					toString += " [B]";
				} else if (map[i][j].getSlime()) {
					toString += " [S]";
				} else {
					toString += " [ ]";
				}
			}
			if (i != 9) {
				toString += "\n";
			}
		}
		return toString;
	}

	public String toStringDebug() {
		String toString = "";
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (i == hunterLocation.y && j == hunterLocation.x) {													// CHANGE HUNTER
					toString += " [O]";
				} else if (map[i][j].getPit()) {
					toString += " [P]";
				} else if (map[i][j].getWumpus()) {
					toString += " [W]";
				} else if (map[i][j].getGoop()) {
					toString += " [G]";
				} else if (map[i][j].getBlood()) {
					toString += " [B]";
				} else if (map[i][j].getSlime()) {
					toString += " [S]";
				} else {
					toString += " [ ]";
				}
			}
			if (i != 9) {
				toString += "\n";
			}
		}
		return toString;
	}

}
