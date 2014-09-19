/*
 * @ - Author: Abhishek Rane
 * @ - Author: Bryce Hammond
 */
import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

/*
 * This class is the main model for the game. It contains all the logic that the game needs to function.
 */
public class Model extends Observable {

	private Cell[][] map;
	private Point wumpusLocation;
	private Point hunterLocation;
	private ArrayList<Point> invalidPoints;
	private ArrayList<Point> visiblePoints;
	private boolean running;
	private boolean hitWumpus;
	private boolean hitSelf;
	private String currentState;

	/*
	 * Primary constructor for Map. Sets up the array of cells and other
	 * required items.
	 */
	public Model() {
		map = new Cell[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				map[i][j] = new Cell();
			}
		}
		this.invalidPoints = new ArrayList<Point>();
		this.visiblePoints = new ArrayList<Point>();
		this.running = true;
		this.hunterLocation = null;
	}
	/*
	 *  Forces the Observers to update themselves
	 */
	public void forceUpdate(){
		this.setChanged();
		this.notifyObservers();
	}

	/*
	 *  Starts a new game board.
	 */
	public void startNewGame() {
		map = new Cell[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				map[i][j] = new Cell();
			}
		}
		this.invalidPoints = new ArrayList<Point>();
		this.visiblePoints = new ArrayList<Point>();
		this.running = true;
		this.hunterLocation = null;
		this.currentState = "";
		this.hitSelf = false;
		this.hitWumpus = false;
		
		generateWumpus();
		generatePits();
		generateHunter();
	}
	
	/*
	 *  List of visible rooms. For easy GUI drawing
	 */
	public ArrayList<Point> getVisibleRooms(){
		return this.visiblePoints;
	}
	
	/*
	 * Returns a cell of the map given a point.
	 */
	public Cell getCell(Point point) {
		return map[point.y][point.x];
	}

	/*
	 * Returns the hunter's location. This is used exclusively in JUnit testing.
	 */
	public Point getHunter() {
		return this.hunterLocation;
	}

	/*
	 * This method generates a set of pits that do not lie on any invalid point.
	 */
	private void generatePits() {
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
	private void generateWumpus() {
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

		// the invalid points help during pit creation
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
	 * This Generates a random hunter location that is not a Wumpus, Blood, Pit,
	 * Slime. Uses a simple While loop to try and place Hunter in a random
	 * location until successful.
	 */
	private void generateHunter() {
		Random rand = new Random();
		boolean hunterPlaced = false;

		while (!hunterPlaced) {
			int i = rand.nextInt(map.length);
			int j = rand.nextInt(map.length);
			if (!map[i][j].isWumpus() && !map[i][j].isPit()
					&& !map[i][j].isSlime() && !map[i][j].isBlood()) {
				this.hunterLocation = (new Point(j, i));
				map[i][j].setHiddenRoom(false);
				map[i][j].setHunter(true);
				hunterPlaced = true;
			}
		}
	this.visiblePoints.add(new Point (getHunter()));
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
	 * This is a method for testing, you can place a hunter in any location.
	 * Assumes that the point is valid.
	 */
	public void setHunter(Point hunter) {
		int x = (int) hunter.x;
		int y = (int) hunter.y;
		if (this.hunterLocation != null) { // Needed for JUnit statement
			map[hunterLocation.y][hunterLocation.x].setHunter(false);
		}
		this.hunterLocation = (new Point(x, y));
		map[y][x].setHunter(true);
		this.visiblePoints.add(new Point (getHunter()));
	}

	/*
	 * This method shoots the arrow in either the horizontal or vertical
	 * direction. Returns false if you miss and sets alive to false, returns
	 * true if wumpus is hit. Valid inputs are capital 'W' , 'A' , 'S' , 'D'.
	 */
	public void shootArrow(char x) {
		if (running) {
			if (x == 'A' || x == 'D') {
				if (hunterLocation.y == wumpusLocation.y) {
					hitWumpus = true;
				}
			} else if (x == 'W' || x == 'S') {
				if (hunterLocation.x == wumpusLocation.x) {
					hitWumpus = true;
				}
			}
			hitSelf = true;
			updateCurrentState();
		}
	}

	/*
	 * This method moves the hunter with wrap around, the valid input is a lower
	 * case 'w' , 'a' , 's' , 'd'.
	 */
	public void hunterMove(char x) {
		if (running) {
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
			this.visiblePoints.add(new Point (getHunter()));
			updateCurrentState();
		}
	}

	/*
	 * Acts as a getter for alive. This allows the run loop to know when to
	 * exit.
	 */
	public boolean isRunning() {
		return running;
	}

	/*
	 * Gets a textual representation for current room that the hunter is in.
	 */
	public String getCurrentState() {
		return this.currentState;
	}
	
	/*
	 *  Sets all rooms to visible. Ends run loop.
	 */
	private void endGame(){
		running = false;
		this.visiblePoints.clear();
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				this.visiblePoints.add(new Point(i,j));
			}
		}
	}
	
	/*
	 *  Getter for hunter shooting him self.
	 */
	public boolean hasHitSelf(){
		return this.hitSelf;
	}
	
	/*
	 * Getter for the hunter shooting the wumpus.
	 */
	public boolean hasHitWumpus(){
		return this.hitWumpus;
	}
	
	/*
	 *  Main game logic.
	 *  Checks if game needs to be ended.
	 *  Produces the state of the current room the hunter is in.
	 */
	private void updateCurrentState() {
		if (hitWumpus) {
			endGame();
			this.currentState = "You aim and fire you weapon of choice. Your trusty bow.\n"
					+ "The arrow whistles through the air. Your aim is true.\n"
					+ "The beast is dead.\n" + "You return home a hero.";
		} else if (hitSelf) {
			endGame();
			this.currentState = "You aim and fire you weapon of choice. Your trusty bow.\n"
					+ "The arrow whistles through the air. Alas your target is not in that direction.\n"
					+ "As you are about to turn away a portal appears infront of the arrow. The arrow enters the portal.\n"
					+ "You hear a sound behind you. Somehow another portal has opened up behind you. The arrow is flying towards you.\n"
					+ "The arrow hits you in the ending your carrer as an explorer. Unable to walk and loosing blood you die alone.";
		} else if (getCell(hunterLocation).isGoop()) {
			this.currentState = "Eww. You walked onto a reddish green mix of blood and slime. It looks like goop.";
		} else if (getCell(hunterLocation).isBlood()) {
			this.currentState = "Your feet slip a bit. You look down and see the floor covered in blood.";
		} else if (getCell(hunterLocation).isSlime()) {
			this.currentState = "Your shoes are now covered in some sort of slime.";
		} else if (getCell(hunterLocation).isPit()) {
			endGame();
			this.currentState = "You loose you footing and fall into a bottemless pit.\n"
					+ "GAME OVER";
		} else if (getCell(hunterLocation).isWumpus()) {
			endGame();
			this.currentState = "You walk into the wumpus. Dinner is serverd.\n"
					+ "For the wumpus.\n" + "GAME OVER";
		} else if (getCell(hunterLocation).isEmpty()) {
			this.currentState = "You look around all you see is nothing. The silence is deafaning.";
		} else {
			this.currentState = "";
		}
		this.setChanged();
		this.notifyObservers();
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
				} else if (map[i][j].isHiddenRoom()) {
					toString += " [X]";
				} else if (map[i][j].isPit()) {
					toString += " [P]";
				} else if (map[i][j].isWumpus()) {
					toString += " [W]";
				} else if (map[i][j].isGoop()) {
					toString += " [G]";
				} else if (map[i][j].isBlood()) {
					toString += " [B]";
				} else if (map[i][j].isSlime()) {
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
	 * state of the map but print all rooms as not hidden. This is called during
	 * development and at the end of the game.
	 */
	public String toStringShowAllRooms() {
		String toString = "";
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (i == hunterLocation.y && j == hunterLocation.x) {
					toString += " [O]";
				} else if (map[i][j].isPit()) {
					toString += " [P]";
				} else if (map[i][j].isWumpus()) {
					toString += " [W]";
				} else if (map[i][j].isGoop()) {
					toString += " [G]";
				} else if (map[i][j].isBlood()) {
					toString += " [B]";
				} else if (map[i][j].isSlime()) {
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