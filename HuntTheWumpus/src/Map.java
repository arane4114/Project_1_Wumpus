import java.awt.Point;
import java.util.ArrayList;

public class Map {

	private Cell[][] map;
	private Cell hunter;
	private Point wumpusLocation;
	private ArrayList<Point> invalidPoints;

	public Map() {
		map = new Cell[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				map[i][j] = new Cell();
			}
		}
		this.invalidPoints = new ArrayList<Point>();

	}

	/*
	 * Returns a cell of the map given a point.
	 */
	public Cell getCell(Point point) {
		return map[point.x][point.y];
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
		map[wumpusLocation.x][wumpusLocation.y].setWumpus(true);

		map[wumpusLocation.x][wrapAround(wumpusLocation.y - 1)].setBlood(true);
		map[wumpusLocation.x][wrapAround(wumpusLocation.y - 2)].setBlood(true);
		map[wrapAround(wumpusLocation.x - 1)][wrapAround(wumpusLocation.y - 1)]
				.setBlood(true);
		map[wrapAround(wumpusLocation.x - 1)][wumpusLocation.y].setBlood(true);
		map[wrapAround(wumpusLocation.x - 2)][wumpusLocation.y].setBlood(true);
		map[wrapAround(wumpusLocation.x - 1)][wrapAround(wumpusLocation.y + 1)]
				.setBlood(true);
		map[wumpusLocation.x][wrapAround(wumpusLocation.y + 1)].setBlood(true);
		map[wumpusLocation.x][wrapAround(wumpusLocation.y + 2)].setBlood(true);
		map[wrapAround(wumpusLocation.x + 1)][wrapAround(wumpusLocation.y + 1)]
				.setBlood(true);
		map[wrapAround(wumpusLocation.x + 1)][wumpusLocation.y].setBlood(true);
		map[wrapAround(wumpusLocation.x + 2)][wumpusLocation.y].setBlood(true);
		map[wrapAround(wumpusLocation.x + 1)][wrapAround(wumpusLocation.y - 1)]
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

	public int wrapAround(int i) {
		if (i < map.length && i > -1) {
			return i;
		} else if (i > map.length - 1) {
			return i - map.length;
		} else {
			return i + map.length;
		}

	}

	public String toString() {
		String toString = "";
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (map[i][j].equals(hunter)) {
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
				if (map[i][j].equals(hunter)) {
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