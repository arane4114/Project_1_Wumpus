/*
 * @ - Author: Abhishek Rane
 * @ - Author: Bryce Hammond
 */
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Map {

	private Cell[][] map;
	private Point wumpusLocation;
	private Point hunterLocation;
	private ArrayList<Point> invalidPoints;
	private boolean alive;

	public Map() {
		map = new Cell[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				map[i][j] = new Cell();
			}
		}
		this.invalidPoints = new ArrayList<Point>();
		this.alive = true;
		this.hunterLocation = null;
	}

	/*
	 * Returns a cell of the map given a point.
	 */
	public Cell getCell(Point point) {
		return map[point.y][point.x];
	}

	/*
	 * This method generates a set of pits that do not lie on any invalid point.
	 */
	public void generatePits() {
		int numberOfPits = 3 + ((int) (Math.random() * ((5 - 3) + 1)));
		ArrayList<Point> pitPoints = new ArrayList<Point>();
		int i = 1;
		while (i <= numberOfPits) {
			int x = (int) (Math.random() * ((9) + 1));
			int y = (int) (Math.random() * ((9) + 1));
			Point possibleNewPoint = new Point(x, y);
			Boolean occupied = false;
			for (Point point : this.invalidPoints) {
				if (point.equals(possibleNewPoint)) {
					occupied = true;
					break;
				}
			}
			if (!occupied) {
				pitPoints.add(possibleNewPoint);
				invalidPoints.add(possibleNewPoint);
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
			map[point.y][point.x].setPit(true);
			if (point.y == 0) {
				map[9][point.x].setSlime(true);
			} else {
				map[point.y - 1][point.x].setSlime(true);
			}

			if (point.y == 9) {
				map[0][point.x].setSlime(true);
			} else {
				map[point.y + 1][point.x].setSlime(true);
			}

			if (point.x == 0) {
				map[point.y][9].setSlime(true);
			} else {
				map[point.y][point.x - 1].setSlime(true);
				;
			}

			if (point.x == 9) {
				map[point.y][0].setSlime(true);
			} else {
				map[point.y][point.x + 1].setSlime(true);
			}
		}
	}

	/*
	 * Generates a wumpus on any random point in the map. The map is empty at
	 * this point so any point is valid.
	 */
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
		map[wumpusLocation.y][wumpusLocation.x].setWumpus(true);

		map[wumpusLocation.y][wrapAround(wumpusLocation.x - 1)].setBlood(true);
		map[wumpusLocation.y][wrapAround(wumpusLocation.x - 2)].setBlood(true);
		map[wrapAround(wumpusLocation.y - 1)][wrapAround(wumpusLocation.x - 1)]
				.setBlood(true);
		map[wrapAround(wumpusLocation.y - 1)][wumpusLocation.x].setBlood(true);
		map[wrapAround(wumpusLocation.y - 2)][wumpusLocation.x].setBlood(true);
		map[wrapAround(wumpusLocation.y - 1)][wrapAround(wumpusLocation.x + 1)]
				.setBlood(true);
		map[wumpusLocation.y][wrapAround(wumpusLocation.x + 1)].setBlood(true);
		map[wumpusLocation.y][wrapAround(wumpusLocation.x + 2)].setBlood(true);
		map[wrapAround(wumpusLocation.y + 1)][wrapAround(wumpusLocation.x + 1)]
				.setBlood(true);
		map[wrapAround(wumpusLocation.y + 1)][wumpusLocation.x].setBlood(true);
		map[wrapAround(wumpusLocation.y + 2)][wumpusLocation.x].setBlood(true);
		map[wrapAround(wumpusLocation.y + 1)][wrapAround(wumpusLocation.x - 1)]
				.setBlood(true);

		invalidPoints.add(new Point(wumpusLocation.x,
				wrapAround(wumpusLocation.y - 1)));
		invalidPoints.add(new Point(wumpusLocation.x,
				wrapAround(wumpusLocation.y - 2)));
		invalidPoints.add(new Point(wrapAround(wumpusLocation.x - 1),
				wrapAround(wumpusLocation.y - 1)));
		invalidPoints.add(new Point(wrapAround(wumpusLocation.x - 1),
				wumpusLocation.y));
		invalidPoints.add(new Point(wrapAround(wumpusLocation.x - 2),
				wumpusLocation.y));
		invalidPoints.add(new Point(wrapAround(wumpusLocation.x - 1),
				wrapAround(wumpusLocation.y + 1)));
		invalidPoints.add(new Point(wumpusLocation.x,
				wrapAround(wumpusLocation.y + 1)));
		invalidPoints.add(new Point(wumpusLocation.x,
				wrapAround(wumpusLocation.y + 2)));
		invalidPoints.add(new Point(wrapAround(wumpusLocation.x + 1),
				wrapAround(wumpusLocation.y + 1)));
		invalidPoints.add(new Point(wrapAround(wumpusLocation.x + 1),
				wumpusLocation.y));
		invalidPoints.add(new Point(wrapAround(wumpusLocation.x + 2),
				wumpusLocation.y));
		invalidPoints.add(new Point(wrapAround(wumpusLocation.x + 1),
				wrapAround(wumpusLocation.y - 1)));
		invalidPoints.add(wumpusLocation);
	}

	/*
	 * This encapsulates the wraparound logic needed by the set wumpus method.
	 */
	private int wrapAround(int i) {
		if (i < map.length && i > -1) {
			return i;
		} else if (i > map.length - 1) {
			return i - map.length;
		} else {
			return i + map.length;
		}

	}

	/*
	 * This Generates a random hunter location that is not a Wumpus, Blood, Pit,
	 * Slime. Uses a simple While loop to try and place Hunter in a random
	 * location until successful.
	 */
	public void generateHunter() {
		Random rand = new Random();
		boolean hunterPlaced = false;

		while (!hunterPlaced) {
			int i = rand.nextInt(map.length);
			int j = rand.nextInt(map.length);
			if (!map[i][j].getWumpus() && !map[i][j].getPit()
					&& !map[i][j].getSlime() && !map[i][j].getBlood()) {
				this.hunterLocation = (new Point(j, i));
				map[i][j].setHiddenRoom(false);
				map[i][j].setHunter(true);
				hunterPlaced = true;
			}
		}
	}

	/*
	 * This is a method for testing, you can place a hunter in any location.
	 * Does not check if your point is valid.
	 */
	public void setHunter(Point hunt) {
		int x = (int) hunt.x;
		int y = (int) hunt.y;
		if (this.hunterLocation != null) {
			map[hunterLocation.y][hunterLocation.x].setHunter(false);
		}
		this.hunterLocation = (new Point(x, y));
		map[y][x].setHunter(true);
	}

	/*
	 * This method shoots the arrow in either the horizontal or vertical
	 * direction. Returns false if you miss and sets alive to false, returns
	 * true if wumpus is hit. Valid inputs are capital 'W' , 'A' , 'S' , 'D'.
	 */
	public boolean shootArrow(char x) {
		alive = false;
		if (x == 'A' || x == 'D') {
			if (hunterLocation.y == wumpusLocation.y) {
				return true;
			}
		} else if (x == 'W' || x == 'S') {
			if (hunterLocation.x == wumpusLocation.x) {
				return true;
			}
		}
		return false;
	}

	/*
	 * This method moves the hunter with wrap around, the valid input is a lower
	 * case 'w' , 'a' , 's' , 'd'.
	 */
	public void hunterMove(char x) {
		if (x == 'w') {
			map[hunterLocation.y][hunterLocation.x].setHunter(false);
			map[hunterLocation.y][hunterLocation.x].setHiddenRoom(false);
			hunterLocation.move(hunterLocation.x,
					wrapAround(hunterLocation.y - 1));
			map[hunterLocation.y][hunterLocation.x].setHunter(true);
		} else if (x == 's') {
			map[hunterLocation.y][hunterLocation.y].setHunter(false);
			map[hunterLocation.y][hunterLocation.x].setHiddenRoom(false);
			hunterLocation.move(hunterLocation.x,
					wrapAround(hunterLocation.y + 1));
			map[hunterLocation.y][hunterLocation.x].setHunter(true);
		} else if (x == 'd') {
			map[hunterLocation.y][hunterLocation.x].setHunter(false);
			map[hunterLocation.y][hunterLocation.x].setHiddenRoom(false);
			hunterLocation.move(wrapAround(hunterLocation.x + 1),
					hunterLocation.y);
			map[hunterLocation.y][hunterLocation.x].setHunter(true);
		} else if (x == 'a') {
			map[hunterLocation.y][hunterLocation.x].setHunter(false);
			map[hunterLocation.y][hunterLocation.x].setHiddenRoom(false);
			hunterLocation.move(wrapAround(hunterLocation.x - 1),
					hunterLocation.y);
			map[hunterLocation.y][hunterLocation.x].setHunter(true);
		}
	}

	/*
	 * Acts as a getter for alive. This allows the run loop to know when to
	 * exit.
	 */
	public boolean isAlive() {
		return alive;
	}

	/*
	 * Gets a textual representation for every room that the hunter is in. If
	 * the room the hunter is currently in is lethal, the hunter can be killed.
	 */
	public String getCurrentState() {
		if (getCell(hunterLocation).getGoop()) {
			return "Eww. You walked onto a reddish green mix of blood and slime. It looks like goop.";
		}
		if (getCell(hunterLocation).getBlood()) {
			return "Your feet slip a bit. You look down and see the floor covered in blood.";
		}
		if (getCell(hunterLocation).getSlime()) {
			return "Your shoes are now covered in some sort of slime";
		}
		if (getCell(hunterLocation).getPit()) {
			alive = false;
			return "You loose you footing and fall into a bottemless pit\n"
					+ "GAME OVER";
		}
		if (getCell(hunterLocation).getWumpus()) {
			alive = false;
			return "You walk into the wumpus. Dinner is serverd.\n"
					+ "For the wumpus.\n" + "GAME OVER";
		}
		if (getCell(hunterLocation).getIsEmpty()) {
			return "You look around all you see is nothing. The silence is deafaning";
		}
		return "";
	}

	/*
	 * Simple to string that prints out a console representation of the current
	 * state of the map.
	 */
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

	/*
	 * Simple to string that prints out a console representation of the current
	 * state of the map but print all rooms as not hidden.
	 */
	public String toStringDebug() {
		String toString = "";
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (i == hunterLocation.y && j == hunterLocation.x) {
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

	/*
	 * Returns the hunter's location. This is used in JUnit testing.
	 */
	public Point getHunter() {
		return this.hunterLocation;
	}
}