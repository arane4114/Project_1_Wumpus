/*
 * @ - Author: Abhishek Rane
 * @ - Author: Bryce Hammond
 */
import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;

import org.junit.Test;

/*
 * This class contains all the tests for the test driven development aspect of the program.
 * Certain user input functions can't be test in JUnit as they are handled in the run loop method.
 */
public class HuntTheWumpusTest extends Observable {

	/*
	 * This test checks the positioning of slime around a pit. There is no
	 * wraparound.
	 */
	@Test
	public void testSlimeLogic() {
		Model map = new Model();
		ArrayList<Point> pitPoints = new ArrayList<Point>();
		Point point = new Point(2, 2);
		pitPoints.add(point);
		map.setPits(pitPoints);
		assertTrue(map.getCell(point).getPit());
		assertTrue(map.getCell(new Point(1, 2)).getSlime());
		assertTrue(map.getCell(new Point(3, 2)).getSlime());
		assertTrue(map.getCell(new Point(2, 1)).getSlime());
		assertTrue(map.getCell(new Point(2, 3)).getSlime());
	}

	/*
	 * This test checks the positioning of slime if we have wraparound.
	 */
	@Test
	public void testSlimeLogicWithWraparound() {
		Model map = new Model();
		ArrayList<Point> pitPoints = new ArrayList<Point>();
		pitPoints.add(new Point(0, 0));
		map.setPits(pitPoints);
		assertTrue(map.getCell(new Point(0, 0)).getPit());
		assertTrue(map.getCell(new Point(0, 1)).getSlime());
		assertTrue(map.getCell(new Point(1, 0)).getSlime());
		assertTrue(map.getCell(new Point(0, 9)).getSlime());
		assertTrue(map.getCell(new Point(9, 0)).getSlime());
	}

	/*
	 * More wraparound testing of pits and slime
	 */
	@Test
	public void testSlimeLogicWithMoreWraparound() {
		Model map = new Model();
		ArrayList<Point> pitPoints = new ArrayList<Point>();
		pitPoints.add(new Point(9, 9));
		map.setPits(pitPoints);
		assertTrue(map.getCell(new Point(9, 9)).getPit());
		assertTrue(map.getCell(new Point(8, 9)).getSlime());
		assertTrue(map.getCell(new Point(9, 8)).getSlime());
		assertTrue(map.getCell(new Point(0, 9)).getSlime());
		assertTrue(map.getCell(new Point(9, 0)).getSlime());
	}

	/*
	 * This tests the placement of the wumpus and the generation of blood.
	 */
	@Test
	public void testSetWumpus() {
		Model map = new Model();
		map.setWumpus(new Point(4, 4));
		assertTrue(map.getCell(new Point(4, 4)).getWumpus());
		assertTrue(map.getCell(new Point(4, 2)).getBlood());
		assertTrue(map.getCell(new Point(3, 3)).getBlood());
		assertTrue(map.getCell(new Point(3, 4)).getBlood());
		assertTrue(map.getCell(new Point(3, 5)).getBlood());
		assertTrue(map.getCell(new Point(2, 4)).getBlood());
		assertTrue(map.getCell(new Point(3, 4)).getBlood());
		assertTrue(map.getCell(new Point(5, 4)).getBlood());
		assertTrue(map.getCell(new Point(6, 4)).getBlood());
		assertTrue(map.getCell(new Point(3, 5)).getBlood());
		assertTrue(map.getCell(new Point(4, 5)).getBlood());
		assertTrue(map.getCell(new Point(5, 5)).getBlood());
		assertTrue(map.getCell(new Point(4, 6)).getBlood());
	}

	/*
	 * This tests the wraparound for wumpus
	 */
	@Test
	public void testWumpusWraparound() {
		Model map = new Model();
		map.setWumpus(new Point(0, 0));
		assertTrue(map.getCell(new Point(0, 0)).getWumpus());
		assertTrue(map.getCell(new Point(1, 0)).getBlood());
		assertTrue(map.getCell(new Point(2, 0)).getBlood());
		assertTrue(map.getCell(new Point(8, 0)).getBlood());
		assertTrue(map.getCell(new Point(9, 0)).getBlood());
		assertTrue(map.getCell(new Point(0, 1)).getBlood());
		assertTrue(map.getCell(new Point(1, 1)).getBlood());
		assertTrue(map.getCell(new Point(0, 2)).getBlood());
		assertTrue(map.getCell(new Point(9, 1)).getBlood());
		assertTrue(map.getCell(new Point(0, 8)).getBlood());
		assertTrue(map.getCell(new Point(9, 0)).getBlood());
		assertTrue(map.getCell(new Point(9, 1)).getBlood());
		assertTrue(map.getCell(new Point(9, 9)).getBlood());
	}

	/*
	 * This method will take the role of the game runner and play through an
	 * entire game scenario. It will not use user input and will pass in valid
	 * points to the gameMap.
	 * 
	 * This method asserts that all non lethal rooms have the correct
	 * statements.
	 */
	@Test
	public void testGameAndAllNonLethalStatementsAndPitDeath() {
		Model map = new Model();
		map.setWumpus(new Point(4, 4));
		assertTrue(map.getCell(new Point(4, 4)).getWumpus());
		assertTrue(map.getCell(new Point(4, 4)).getWumpus());
		assertTrue(map.getCell(new Point(4, 2)).getBlood());
		assertTrue(map.getCell(new Point(3, 3)).getBlood());
		assertTrue(map.getCell(new Point(3, 4)).getBlood());
		assertTrue(map.getCell(new Point(3, 5)).getBlood());
		assertTrue(map.getCell(new Point(2, 4)).getBlood());
		assertTrue(map.getCell(new Point(3, 4)).getBlood());
		assertTrue(map.getCell(new Point(5, 4)).getBlood());
		assertTrue(map.getCell(new Point(6, 4)).getBlood());
		assertTrue(map.getCell(new Point(3, 5)).getBlood());
		assertTrue(map.getCell(new Point(4, 5)).getBlood());
		assertTrue(map.getCell(new Point(5, 5)).getBlood());
		assertTrue(map.getCell(new Point(4, 6)).getBlood());

		ArrayList<Point> pitPoints = new ArrayList<Point>();
		pitPoints.add(new Point(2, 2));
		pitPoints.add(new Point(5, 5));
		pitPoints.add(new Point(9, 9));
		map.setPits(pitPoints);

		assertTrue(map.getCell(new Point(2, 2)).getPit());
		assertTrue(map.getCell(new Point(1, 2)).getSlime());
		assertTrue(map.getCell(new Point(3, 2)).getSlime());
		assertTrue(map.getCell(new Point(2, 1)).getSlime());
		assertTrue(map.getCell(new Point(2, 3)).getSlime());

		assertTrue(map.getCell(new Point(5, 5)).getPit());
		assertTrue(map.getCell(new Point(5, 6)).getSlime());
		assertTrue(map.getCell(new Point(6, 5)).getSlime());
		assertTrue(map.getCell(new Point(5, 4)).getGoop());
		assertTrue(map.getCell(new Point(4, 5)).getGoop());

		assertTrue(map.getCell(new Point(9, 9)).getPit());
		assertTrue(map.getCell(new Point(8, 9)).getSlime());
		assertTrue(map.getCell(new Point(9, 8)).getSlime());
		assertTrue(map.getCell(new Point(0, 9)).getSlime());
		assertTrue(map.getCell(new Point(9, 0)).getSlime());

		map.setHunter(new Point(3, 8));
		assertTrue(map.getHunter().equals(new Point(3, 8)));
		assertTrue(map.isRunning());

		map.hunterMove('d');
		assertTrue(map.getHunter().equals(new Point(4, 8)));
		assertTrue(map.isRunning());
		assertTrue(map.getCell(new Point(4, 8)).getIsEmpty());
		assertTrue(map
				.getCurrentState()
				.equals("You look around all you see is nothing. The silence is deafaning."));

		map.hunterMove('s');
		assertTrue(map.getHunter().equals(new Point(4, 9)));
		assertTrue(map.isRunning());
		assertTrue(map.getCell(new Point(4, 9)).getIsEmpty());
		assertTrue(map
				.getCurrentState()
				.equals("You look around all you see is nothing. The silence is deafaning."));

		map.hunterMove('a');
		assertTrue(map.getHunter().equals(new Point(3, 9)));
		assertTrue(map.isRunning());
		assertTrue(map.getCell(new Point(3, 9)).getIsEmpty());
		assertTrue(map
				.getCurrentState()
				.equals("You look around all you see is nothing. The silence is deafaning."));

		map.hunterMove('w');
		assertTrue(map.getHunter().equals(new Point(3, 8)));
		assertTrue(map.isRunning());
		assertTrue(map.getCell(new Point(3, 8)).getIsEmpty());
		assertTrue(map
				.getCurrentState()
				.equals("You look around all you see is nothing. The silence is deafaning."));

		map.hunterMove('d');
		assertTrue(map.getHunter().equals(new Point(4, 8)));
		assertTrue(map.isRunning());
		assertTrue(map.getCell(new Point(4, 8)).getIsEmpty());
		assertTrue(map
				.getCurrentState()
				.equals("You look around all you see is nothing. The silence is deafaning."));

		map.hunterMove('w');
		assertTrue(map.getHunter().equals(new Point(4, 7)));
		assertTrue(map.isRunning());
		assertTrue(map.getCell(new Point(4, 7)).getIsEmpty());
		assertTrue(map
				.getCurrentState()
				.equals("You look around all you see is nothing. The silence is deafaning."));

		map.hunterMove('w');
		assertTrue(map.getHunter().equals(new Point(4, 6)));
		assertTrue(map.isRunning());
		assertFalse(map.getCell(new Point(4, 6)).getIsEmpty());
		assertTrue(map.getCell(new Point(4, 6)).getBlood());
		assertTrue(map
				.getCurrentState()
				.equals("Your feet slip a bit. You look down and see the floor covered in blood."));

		map.hunterMove('w');
		assertTrue(map.getHunter().equals(new Point(4, 5)));
		assertTrue(map.isRunning());
		assertFalse(map.getCell(new Point(4, 5)).getIsEmpty());
		assertTrue(map.getCell(new Point(4, 5)).getGoop());
		assertTrue(map
				.getCurrentState()
				.equals("Eww. You walked onto a reddish green mix of blood and slime. It looks like goop."));

		map.hunterMove('s');
		assertTrue(map.getHunter().equals(new Point(4, 6)));
		assertTrue(map.isRunning());
		assertFalse(map.getCell(new Point(4, 6)).getIsEmpty());
		assertTrue(map.getCell(new Point(4, 6)).getBlood());
		assertTrue(map
				.getCurrentState()
				.equals("Your feet slip a bit. You look down and see the floor covered in blood."));

		map.hunterMove('d');
		assertTrue(map.getHunter().equals(new Point(5, 6)));
		assertTrue(map.isRunning());
		assertFalse(map.getCell(new Point(5, 6)).getIsEmpty());
		assertTrue(map.getCell(new Point(5, 6)).getSlime());
		assertTrue(map.getCurrentState().equals(
				"Your shoes are now covered in some sort of slime."));

		map.hunterMove('w');
		assertTrue(map.getHunter().equals(new Point(5, 5)));
		assertFalse(map.getCell(new Point(5, 5)).getIsEmpty());
		assertTrue(map.getCell(new Point(5, 5)).getPit());
		assertTrue(map.getCurrentState().equals(
				"You loose you footing and fall into a bottemless pit.\n"
						+ "GAME OVER"));
		assertFalse(map.isRunning());
	}

	/*
	 * This method will take the role of the game runner and play through an
	 * entire game scenario. It will not use user input and will pass in valid
	 * points to the gameMap.
	 * 
	 * This method asserts that all rooms on the path to the wumpus have the
	 * correct state and the wumpus death statement is correct
	 */
	@Test
	public void testGameWalkIntoWumpusDeath() {
		Model map = new Model();
		map.setWumpus(new Point(4, 4));
		assertTrue(map.getCell(new Point(4, 4)).getWumpus());
		assertTrue(map.getCell(new Point(4, 4)).getWumpus());
		assertTrue(map.getCell(new Point(4, 2)).getBlood());
		assertTrue(map.getCell(new Point(3, 3)).getBlood());
		assertTrue(map.getCell(new Point(3, 4)).getBlood());
		assertTrue(map.getCell(new Point(3, 5)).getBlood());
		assertTrue(map.getCell(new Point(2, 4)).getBlood());
		assertTrue(map.getCell(new Point(3, 4)).getBlood());
		assertTrue(map.getCell(new Point(5, 4)).getBlood());
		assertTrue(map.getCell(new Point(6, 4)).getBlood());
		assertTrue(map.getCell(new Point(3, 5)).getBlood());
		assertTrue(map.getCell(new Point(4, 5)).getBlood());
		assertTrue(map.getCell(new Point(5, 5)).getBlood());
		assertTrue(map.getCell(new Point(4, 6)).getBlood());

		ArrayList<Point> pitPoints = new ArrayList<Point>();
		pitPoints.add(new Point(2, 2));
		pitPoints.add(new Point(5, 5));
		pitPoints.add(new Point(9, 9));
		map.setPits(pitPoints);

		assertTrue(map.getCell(new Point(2, 2)).getPit());
		assertTrue(map.getCell(new Point(1, 2)).getSlime());
		assertTrue(map.getCell(new Point(3, 2)).getSlime());
		assertTrue(map.getCell(new Point(2, 1)).getSlime());
		assertTrue(map.getCell(new Point(2, 3)).getSlime());

		assertTrue(map.getCell(new Point(5, 5)).getPit());
		assertTrue(map.getCell(new Point(5, 6)).getSlime());
		assertTrue(map.getCell(new Point(6, 5)).getSlime());
		assertTrue(map.getCell(new Point(5, 4)).getGoop());
		assertTrue(map.getCell(new Point(4, 5)).getGoop());

		assertTrue(map.getCell(new Point(9, 9)).getPit());
		assertTrue(map.getCell(new Point(8, 9)).getSlime());
		assertTrue(map.getCell(new Point(9, 8)).getSlime());
		assertTrue(map.getCell(new Point(0, 9)).getSlime());
		assertTrue(map.getCell(new Point(9, 0)).getSlime());

		map.setHunter(new Point(3, 8));
		assertTrue(map.getHunter().equals(new Point(3, 8)));
		assertTrue(map.isRunning());

		map.hunterMove('d');
		assertTrue(map.getHunter().equals(new Point(4, 8)));
		assertTrue(map.isRunning());
		assertTrue(map.getCell(new Point(4, 8)).getIsEmpty());
		assertTrue(map
				.getCurrentState()
				.equals("You look around all you see is nothing. The silence is deafaning."));

		map.hunterMove('s');
		assertTrue(map.getHunter().equals(new Point(4, 9)));
		assertTrue(map.isRunning());
		assertTrue(map.getCell(new Point(4, 9)).getIsEmpty());
		assertTrue(map
				.getCurrentState()
				.equals("You look around all you see is nothing. The silence is deafaning."));

		map.hunterMove('a');
		assertTrue(map.getHunter().equals(new Point(3, 9)));
		assertTrue(map.isRunning());
		assertTrue(map.getCell(new Point(3, 9)).getIsEmpty());
		assertTrue(map
				.getCurrentState()
				.equals("You look around all you see is nothing. The silence is deafaning."));

		map.hunterMove('w');
		assertTrue(map.getHunter().equals(new Point(3, 8)));
		assertTrue(map.isRunning());
		assertTrue(map.getCell(new Point(3, 8)).getIsEmpty());
		assertTrue(map
				.getCurrentState()
				.equals("You look around all you see is nothing. The silence is deafaning."));

		map.hunterMove('d');
		assertTrue(map.getHunter().equals(new Point(4, 8)));
		assertTrue(map.isRunning());
		assertTrue(map.getCell(new Point(4, 8)).getIsEmpty());
		assertTrue(map
				.getCurrentState()
				.equals("You look around all you see is nothing. The silence is deafaning."));

		map.hunterMove('w');
		assertTrue(map.getHunter().equals(new Point(4, 7)));
		assertTrue(map.isRunning());
		assertTrue(map.getCell(new Point(4, 7)).getIsEmpty());
		assertTrue(map
				.getCurrentState()
				.equals("You look around all you see is nothing. The silence is deafaning."));

		map.hunterMove('w');
		assertTrue(map.getHunter().equals(new Point(4, 6)));
		assertTrue(map.isRunning());
		assertFalse(map.getCell(new Point(4, 6)).getIsEmpty());
		assertTrue(map.getCell(new Point(4, 6)).getBlood());
		assertTrue(map
				.getCurrentState()
				.equals("Your feet slip a bit. You look down and see the floor covered in blood."));

		map.hunterMove('w');
		assertTrue(map.getHunter().equals(new Point(4, 5)));
		assertTrue(map.isRunning());
		assertFalse(map.getCell(new Point(4, 5)).getIsEmpty());
		assertTrue(map.getCell(new Point(4, 5)).getGoop());
		assertTrue(map
				.getCurrentState()
				.equals("Eww. You walked onto a reddish green mix of blood and slime. It looks like goop."));

		map.hunterMove('w');
		assertTrue(map.getHunter().equals(new Point(4, 4)));
		assertFalse(map.getCell(new Point(4, 4)).getIsEmpty());
		assertTrue(map.getCell(new Point(4, 4)).getWumpus());
		assertTrue(map.getCurrentState().equals(
				"You walk into the wumpus. Dinner is serverd.\n"
						+ "For the wumpus.\n" + "GAME OVER"));
		assertFalse(map.isRunning());
	}

	/*
	 * This method will take the role of the game runner and play through an
	 * entire game scenario. It will not use user input and will pass in valid
	 * points to the gameMap.
	 * 
	 * This method asserts that all rooms on the path to the wumpus have the
	 * correct state and shooting the wumpus directly works.
	 */
	@Test
	public void testGameShootUpwardsIntoWumpus() {
		Model map = new Model();
		map.setWumpus(new Point(4, 4));
		assertTrue(map.getCell(new Point(4, 4)).getWumpus());
		assertTrue(map.getCell(new Point(4, 4)).getWumpus());
		assertTrue(map.getCell(new Point(4, 2)).getBlood());
		assertTrue(map.getCell(new Point(3, 3)).getBlood());
		assertTrue(map.getCell(new Point(3, 4)).getBlood());
		assertTrue(map.getCell(new Point(3, 5)).getBlood());
		assertTrue(map.getCell(new Point(2, 4)).getBlood());
		assertTrue(map.getCell(new Point(3, 4)).getBlood());
		assertTrue(map.getCell(new Point(5, 4)).getBlood());
		assertTrue(map.getCell(new Point(6, 4)).getBlood());
		assertTrue(map.getCell(new Point(3, 5)).getBlood());
		assertTrue(map.getCell(new Point(4, 5)).getBlood());
		assertTrue(map.getCell(new Point(5, 5)).getBlood());
		assertTrue(map.getCell(new Point(4, 6)).getBlood());

		ArrayList<Point> pitPoints = new ArrayList<Point>();
		pitPoints.add(new Point(2, 2));
		pitPoints.add(new Point(5, 5));
		pitPoints.add(new Point(9, 9));
		map.setPits(pitPoints);

		assertTrue(map.getCell(new Point(2, 2)).getPit());
		assertTrue(map.getCell(new Point(1, 2)).getSlime());
		assertTrue(map.getCell(new Point(3, 2)).getSlime());
		assertTrue(map.getCell(new Point(2, 1)).getSlime());
		assertTrue(map.getCell(new Point(2, 3)).getSlime());

		assertTrue(map.getCell(new Point(5, 5)).getPit());
		assertTrue(map.getCell(new Point(5, 6)).getSlime());
		assertTrue(map.getCell(new Point(6, 5)).getSlime());
		assertTrue(map.getCell(new Point(5, 4)).getGoop());
		assertTrue(map.getCell(new Point(4, 5)).getGoop());

		assertTrue(map.getCell(new Point(9, 9)).getPit());
		assertTrue(map.getCell(new Point(8, 9)).getSlime());
		assertTrue(map.getCell(new Point(9, 8)).getSlime());
		assertTrue(map.getCell(new Point(0, 9)).getSlime());
		assertTrue(map.getCell(new Point(9, 0)).getSlime());

		map.setHunter(new Point(3, 8));
		assertTrue(map.getHunter().equals(new Point(3, 8)));
		assertTrue(map.isRunning());

		map.hunterMove('d');
		assertTrue(map.getHunter().equals(new Point(4, 8)));
		assertTrue(map.isRunning());
		assertTrue(map.getCell(new Point(4, 8)).getIsEmpty());
		assertTrue(map
				.getCurrentState()
				.equals("You look around all you see is nothing. The silence is deafaning."));

		map.hunterMove('s');
		assertTrue(map.getHunter().equals(new Point(4, 9)));
		assertTrue(map.isRunning());
		assertTrue(map.getCell(new Point(4, 9)).getIsEmpty());
		assertTrue(map
				.getCurrentState()
				.equals("You look around all you see is nothing. The silence is deafaning."));

		map.hunterMove('a');
		assertTrue(map.getHunter().equals(new Point(3, 9)));
		assertTrue(map.isRunning());
		assertTrue(map.getCell(new Point(3, 9)).getIsEmpty());
		assertTrue(map
				.getCurrentState()
				.equals("You look around all you see is nothing. The silence is deafaning."));

		map.hunterMove('w');
		assertTrue(map.getHunter().equals(new Point(3, 8)));
		assertTrue(map.isRunning());
		assertTrue(map.getCell(new Point(3, 8)).getIsEmpty());
		assertTrue(map
				.getCurrentState()
				.equals("You look around all you see is nothing. The silence is deafaning."));

		map.hunterMove('d');
		assertTrue(map.getHunter().equals(new Point(4, 8)));
		assertTrue(map.isRunning());
		assertTrue(map.getCell(new Point(4, 8)).getIsEmpty());
		assertTrue(map
				.getCurrentState()
				.equals("You look around all you see is nothing. The silence is deafaning."));

		map.hunterMove('w');
		assertTrue(map.getHunter().equals(new Point(4, 7)));
		assertTrue(map.isRunning());
		assertTrue(map.getCell(new Point(4, 7)).getIsEmpty());
		assertTrue(map
				.getCurrentState()
				.equals("You look around all you see is nothing. The silence is deafaning."));

		map.hunterMove('w');
		assertTrue(map.getHunter().equals(new Point(4, 6)));
		assertTrue(map.isRunning());
		assertFalse(map.getCell(new Point(4, 6)).getIsEmpty());
		assertTrue(map.getCell(new Point(4, 6)).getBlood());
		assertTrue(map
				.getCurrentState()
				.equals("Your feet slip a bit. You look down and see the floor covered in blood."));

		map.hunterMove('w');
		assertTrue(map.getHunter().equals(new Point(4, 5)));
		assertTrue(map.isRunning());
		assertFalse(map.getCell(new Point(4, 5)).getIsEmpty());
		assertTrue(map.getCell(new Point(4, 5)).getGoop());
		assertTrue(map
				.getCurrentState()
				.equals("Eww. You walked onto a reddish green mix of blood and slime. It looks like goop."));

		map.shootArrow('W');
		assertTrue(map.getHunter().equals(new Point(4, 5)));
		assertFalse(map.getCell(new Point(4, 5)).getIsEmpty());
		assertFalse(map.getCell(new Point(4, 5)).getWumpus());
		assertTrue(map
				.getCurrentState()
				.equals("You aim and fire you weapon of choice. Your trusty bow.\n"
						+ "The arrow whistles through the air. Your aim is true.\n"
						+ "The beast is dead.\n" + "You return home a hero."));
		assertFalse(map.isRunning());
	}

	/*
	 * This method will take the role of the game runner and play through an
	 * entire game scenario. It will not use user input and will pass in valid
	 * points to the gameMap.
	 * 
	 * This method asserts that all rooms on the path to the wumpus have the
	 * correct state and wraparound arrows can shoot the wumpus
	 */
	@Test
	public void testGameShootVerticalIntoWumpusWithArrowWraparound() {
		Model map = new Model();
		map.setWumpus(new Point(4, 4));
		assertTrue(map.getCell(new Point(4, 4)).getWumpus());
		assertTrue(map.getCell(new Point(4, 4)).getWumpus());
		assertTrue(map.getCell(new Point(4, 2)).getBlood());
		assertTrue(map.getCell(new Point(3, 3)).getBlood());
		assertTrue(map.getCell(new Point(3, 4)).getBlood());
		assertTrue(map.getCell(new Point(3, 5)).getBlood());
		assertTrue(map.getCell(new Point(2, 4)).getBlood());
		assertTrue(map.getCell(new Point(3, 4)).getBlood());
		assertTrue(map.getCell(new Point(5, 4)).getBlood());
		assertTrue(map.getCell(new Point(6, 4)).getBlood());
		assertTrue(map.getCell(new Point(3, 5)).getBlood());
		assertTrue(map.getCell(new Point(4, 5)).getBlood());
		assertTrue(map.getCell(new Point(5, 5)).getBlood());
		assertTrue(map.getCell(new Point(4, 6)).getBlood());

		ArrayList<Point> pitPoints = new ArrayList<Point>();
		pitPoints.add(new Point(2, 2));
		pitPoints.add(new Point(5, 5));
		pitPoints.add(new Point(9, 9));
		map.setPits(pitPoints);

		assertTrue(map.getCell(new Point(2, 2)).getPit());
		assertTrue(map.getCell(new Point(1, 2)).getSlime());
		assertTrue(map.getCell(new Point(3, 2)).getSlime());
		assertTrue(map.getCell(new Point(2, 1)).getSlime());
		assertTrue(map.getCell(new Point(2, 3)).getSlime());

		assertTrue(map.getCell(new Point(5, 5)).getPit());
		assertTrue(map.getCell(new Point(5, 6)).getSlime());
		assertTrue(map.getCell(new Point(6, 5)).getSlime());
		assertTrue(map.getCell(new Point(5, 4)).getGoop());
		assertTrue(map.getCell(new Point(4, 5)).getGoop());

		assertTrue(map.getCell(new Point(9, 9)).getPit());
		assertTrue(map.getCell(new Point(8, 9)).getSlime());
		assertTrue(map.getCell(new Point(9, 8)).getSlime());
		assertTrue(map.getCell(new Point(0, 9)).getSlime());
		assertTrue(map.getCell(new Point(9, 0)).getSlime());

		map.setHunter(new Point(3, 8));
		assertTrue(map.getHunter().equals(new Point(3, 8)));
		assertTrue(map.isRunning());

		map.hunterMove('d');
		assertTrue(map.getHunter().equals(new Point(4, 8)));
		assertTrue(map.isRunning());
		assertTrue(map.getCell(new Point(4, 8)).getIsEmpty());
		assertTrue(map
				.getCurrentState()
				.equals("You look around all you see is nothing. The silence is deafaning."));

		map.hunterMove('s');
		assertTrue(map.getHunter().equals(new Point(4, 9)));
		assertTrue(map.isRunning());
		assertTrue(map.getCell(new Point(4, 9)).getIsEmpty());
		assertTrue(map
				.getCurrentState()
				.equals("You look around all you see is nothing. The silence is deafaning."));

		map.hunterMove('a');
		assertTrue(map.getHunter().equals(new Point(3, 9)));
		assertTrue(map.isRunning());
		assertTrue(map.getCell(new Point(3, 9)).getIsEmpty());
		assertTrue(map
				.getCurrentState()
				.equals("You look around all you see is nothing. The silence is deafaning."));

		map.hunterMove('w');
		assertTrue(map.getHunter().equals(new Point(3, 8)));
		assertTrue(map.isRunning());
		assertTrue(map.getCell(new Point(3, 8)).getIsEmpty());
		assertTrue(map
				.getCurrentState()
				.equals("You look around all you see is nothing. The silence is deafaning."));

		map.hunterMove('d');
		assertTrue(map.getHunter().equals(new Point(4, 8)));
		assertTrue(map.isRunning());
		assertTrue(map.getCell(new Point(4, 8)).getIsEmpty());
		assertTrue(map
				.getCurrentState()
				.equals("You look around all you see is nothing. The silence is deafaning."));

		map.hunterMove('w');
		assertTrue(map.getHunter().equals(new Point(4, 7)));
		assertTrue(map.isRunning());
		assertTrue(map.getCell(new Point(4, 7)).getIsEmpty());
		assertTrue(map
				.getCurrentState()
				.equals("You look around all you see is nothing. The silence is deafaning."));

		map.hunterMove('w');
		assertTrue(map.getHunter().equals(new Point(4, 6)));
		assertTrue(map.isRunning());
		assertFalse(map.getCell(new Point(4, 6)).getIsEmpty());
		assertTrue(map.getCell(new Point(4, 6)).getBlood());
		assertTrue(map
				.getCurrentState()
				.equals("Your feet slip a bit. You look down and see the floor covered in blood."));

		map.hunterMove('w');
		assertTrue(map.getHunter().equals(new Point(4, 5)));
		assertTrue(map.isRunning());
		assertFalse(map.getCell(new Point(4, 5)).getIsEmpty());
		assertTrue(map.getCell(new Point(4, 5)).getGoop());
		assertTrue(map
				.getCurrentState()
				.equals("Eww. You walked onto a reddish green mix of blood and slime. It looks like goop."));

		map.shootArrow('S');
		assertTrue(map.getHunter().equals(new Point(4, 5)));
		assertFalse(map.getCell(new Point(4, 5)).getIsEmpty());
		assertFalse(map.getCell(new Point(4, 5)).getWumpus());
		assertTrue(map
				.getCurrentState()
				.equals("You aim and fire you weapon of choice. Your trusty bow.\n"
						+ "The arrow whistles through the air. Your aim is true.\n"
						+ "The beast is dead.\n" + "You return home a hero."));
		assertFalse(map.isRunning());
	}

	/*
	 * This method will take the role of the game runner and play through an
	 * entire game scenario. It will not use user input and will pass in valid
	 * points to the gameMap.
	 * 
	 * This method asserts that all rooms on the path to the wumpus have the
	 * correct state and shooting the wumpus horizontally works
	 */
	@Test
	public void testGameShootHorizontal() {
		Model map = new Model();
		map.setWumpus(new Point(4, 4));
		assertTrue(map.getCell(new Point(4, 4)).getWumpus());
		assertTrue(map.getCell(new Point(4, 4)).getWumpus());
		assertTrue(map.getCell(new Point(4, 2)).getBlood());
		assertTrue(map.getCell(new Point(3, 3)).getBlood());
		assertTrue(map.getCell(new Point(3, 4)).getBlood());
		assertTrue(map.getCell(new Point(3, 5)).getBlood());
		assertTrue(map.getCell(new Point(2, 4)).getBlood());
		assertTrue(map.getCell(new Point(3, 4)).getBlood());
		assertTrue(map.getCell(new Point(5, 4)).getBlood());
		assertTrue(map.getCell(new Point(6, 4)).getBlood());
		assertTrue(map.getCell(new Point(3, 5)).getBlood());
		assertTrue(map.getCell(new Point(4, 5)).getBlood());
		assertTrue(map.getCell(new Point(5, 5)).getBlood());
		assertTrue(map.getCell(new Point(4, 6)).getBlood());

		ArrayList<Point> pitPoints = new ArrayList<Point>();
		pitPoints.add(new Point(2, 2));
		pitPoints.add(new Point(5, 5));
		pitPoints.add(new Point(9, 9));
		map.setPits(pitPoints);

		assertTrue(map.getCell(new Point(2, 2)).getPit());
		assertTrue(map.getCell(new Point(1, 2)).getSlime());
		assertTrue(map.getCell(new Point(3, 2)).getSlime());
		assertTrue(map.getCell(new Point(2, 1)).getSlime());
		assertTrue(map.getCell(new Point(2, 3)).getSlime());

		assertTrue(map.getCell(new Point(5, 5)).getPit());
		assertTrue(map.getCell(new Point(5, 6)).getSlime());
		assertTrue(map.getCell(new Point(6, 5)).getSlime());
		assertTrue(map.getCell(new Point(5, 4)).getGoop());
		assertTrue(map.getCell(new Point(4, 5)).getGoop());

		assertTrue(map.getCell(new Point(9, 9)).getPit());
		assertTrue(map.getCell(new Point(8, 9)).getSlime());
		assertTrue(map.getCell(new Point(9, 8)).getSlime());
		assertTrue(map.getCell(new Point(0, 9)).getSlime());
		assertTrue(map.getCell(new Point(9, 0)).getSlime());

		map.setHunter(new Point(3, 8));
		assertTrue(map.getHunter().equals(new Point(3, 8)));
		assertTrue(map.isRunning());

		map.hunterMove('d');
		assertTrue(map.getHunter().equals(new Point(4, 8)));
		assertTrue(map.isRunning());
		assertTrue(map.getCell(new Point(4, 8)).getIsEmpty());
		assertTrue(map
				.getCurrentState()
				.equals("You look around all you see is nothing. The silence is deafaning."));

		map.hunterMove('s');
		assertTrue(map.getHunter().equals(new Point(4, 9)));
		assertTrue(map.isRunning());
		assertTrue(map.getCell(new Point(4, 9)).getIsEmpty());
		assertTrue(map
				.getCurrentState()
				.equals("You look around all you see is nothing. The silence is deafaning."));

		map.hunterMove('a');
		assertTrue(map.getHunter().equals(new Point(3, 9)));
		assertTrue(map.isRunning());
		assertTrue(map.getCell(new Point(3, 9)).getIsEmpty());
		assertTrue(map
				.getCurrentState()
				.equals("You look around all you see is nothing. The silence is deafaning."));

		map.hunterMove('w');
		assertTrue(map.getHunter().equals(new Point(3, 8)));
		assertTrue(map.isRunning());
		assertTrue(map.getCell(new Point(3, 8)).getIsEmpty());
		assertTrue(map
				.getCurrentState()
				.equals("You look around all you see is nothing. The silence is deafaning."));

		map.hunterMove('d');
		assertTrue(map.getHunter().equals(new Point(4, 8)));
		assertTrue(map.isRunning());
		assertTrue(map.getCell(new Point(4, 8)).getIsEmpty());
		assertTrue(map
				.getCurrentState()
				.equals("You look around all you see is nothing. The silence is deafaning."));

		map.hunterMove('w');
		assertTrue(map.getHunter().equals(new Point(4, 7)));
		assertTrue(map.isRunning());
		assertTrue(map.getCell(new Point(4, 7)).getIsEmpty());
		assertTrue(map
				.getCurrentState()
				.equals("You look around all you see is nothing. The silence is deafaning."));

		map.hunterMove('w');
		assertTrue(map.getHunter().equals(new Point(4, 6)));
		assertTrue(map.isRunning());
		assertFalse(map.getCell(new Point(4, 6)).getIsEmpty());
		assertTrue(map.getCell(new Point(4, 6)).getBlood());
		assertTrue(map
				.getCurrentState()
				.equals("Your feet slip a bit. You look down and see the floor covered in blood."));

		map.hunterMove('w');
		assertTrue(map.getHunter().equals(new Point(4, 5)));
		assertTrue(map.isRunning());
		assertFalse(map.getCell(new Point(4, 5)).getIsEmpty());
		assertTrue(map.getCell(new Point(4, 5)).getGoop());
		assertTrue(map
				.getCurrentState()
				.equals("Eww. You walked onto a reddish green mix of blood and slime. It looks like goop."));

		map.hunterMove('a');
		assertTrue(map.getHunter().equals(new Point(3, 5)));
		assertTrue(map.isRunning());
		assertFalse(map.getCell(new Point(3, 5)).getIsEmpty());
		assertTrue(map.getCell(new Point(3, 5)).getBlood());
		assertTrue(map
				.getCurrentState()
				.equals("Your feet slip a bit. You look down and see the floor covered in blood."));

		map.hunterMove('w');
		assertTrue(map.getHunter().equals(new Point(3, 4)));
		assertTrue(map.isRunning());
		assertFalse(map.getCell(new Point(4, 4)).getIsEmpty());
		assertTrue(map.getCell(new Point(3, 4)).getBlood());
		assertTrue(map
				.getCurrentState()
				.equals("Your feet slip a bit. You look down and see the floor covered in blood."));

		map.shootArrow('D');
		assertTrue(map.getHunter().equals(new Point(3, 4)));
		assertFalse(map.getCell(new Point(3, 4)).getIsEmpty());
		assertFalse(map.getCell(new Point(3, 4)).getWumpus());
		assertTrue(map
				.getCurrentState()
				.equals("You aim and fire you weapon of choice. Your trusty bow.\n"
						+ "The arrow whistles through the air. Your aim is true.\n"
						+ "The beast is dead.\n" + "You return home a hero."));
		assertFalse(map.isRunning());
	}

	/*
	 * This method will take the role of the game runner and play through an
	 * entire game scenario. It will not use user input and will pass in valid
	 * points to the gameMap.
	 * 
	 * This method asserts that all rooms on the path to the wumpus have the
	 * correct state and shooting the wumpus horizontally works
	 */
	@Test
	public void testGameShootHorizontalWithWrapAround() {
		Model map = new Model();
		map.setWumpus(new Point(4, 4));
		assertTrue(map.getCell(new Point(4, 4)).getWumpus());
		assertTrue(map.getCell(new Point(4, 4)).getWumpus());
		assertTrue(map.getCell(new Point(4, 2)).getBlood());
		assertTrue(map.getCell(new Point(3, 3)).getBlood());
		assertTrue(map.getCell(new Point(3, 4)).getBlood());
		assertTrue(map.getCell(new Point(3, 5)).getBlood());
		assertTrue(map.getCell(new Point(2, 4)).getBlood());
		assertTrue(map.getCell(new Point(3, 4)).getBlood());
		assertTrue(map.getCell(new Point(5, 4)).getBlood());
		assertTrue(map.getCell(new Point(6, 4)).getBlood());
		assertTrue(map.getCell(new Point(3, 5)).getBlood());
		assertTrue(map.getCell(new Point(4, 5)).getBlood());
		assertTrue(map.getCell(new Point(5, 5)).getBlood());
		assertTrue(map.getCell(new Point(4, 6)).getBlood());

		ArrayList<Point> pitPoints = new ArrayList<Point>();
		pitPoints.add(new Point(2, 2));
		pitPoints.add(new Point(5, 5));
		pitPoints.add(new Point(9, 9));
		map.setPits(pitPoints);

		assertTrue(map.getCell(new Point(2, 2)).getPit());
		assertTrue(map.getCell(new Point(1, 2)).getSlime());
		assertTrue(map.getCell(new Point(3, 2)).getSlime());
		assertTrue(map.getCell(new Point(2, 1)).getSlime());
		assertTrue(map.getCell(new Point(2, 3)).getSlime());

		assertTrue(map.getCell(new Point(5, 5)).getPit());
		assertTrue(map.getCell(new Point(5, 6)).getSlime());
		assertTrue(map.getCell(new Point(6, 5)).getSlime());
		assertTrue(map.getCell(new Point(5, 4)).getGoop());
		assertTrue(map.getCell(new Point(4, 5)).getGoop());

		assertTrue(map.getCell(new Point(9, 9)).getPit());
		assertTrue(map.getCell(new Point(8, 9)).getSlime());
		assertTrue(map.getCell(new Point(9, 8)).getSlime());
		assertTrue(map.getCell(new Point(0, 9)).getSlime());
		assertTrue(map.getCell(new Point(9, 0)).getSlime());

		map.setHunter(new Point(3, 8));
		assertTrue(map.getHunter().equals(new Point(3, 8)));
		assertTrue(map.isRunning());

		map.hunterMove('d');
		assertTrue(map.getHunter().equals(new Point(4, 8)));
		assertTrue(map.isRunning());
		assertTrue(map.getCell(new Point(4, 8)).getIsEmpty());
		assertTrue(map
				.getCurrentState()
				.equals("You look around all you see is nothing. The silence is deafaning."));

		map.hunterMove('s');
		assertTrue(map.getHunter().equals(new Point(4, 9)));
		assertTrue(map.isRunning());
		assertTrue(map.getCell(new Point(4, 9)).getIsEmpty());
		assertTrue(map
				.getCurrentState()
				.equals("You look around all you see is nothing. The silence is deafaning."));

		map.hunterMove('a');
		assertTrue(map.getHunter().equals(new Point(3, 9)));
		assertTrue(map.isRunning());
		assertTrue(map.getCell(new Point(3, 9)).getIsEmpty());
		assertTrue(map
				.getCurrentState()
				.equals("You look around all you see is nothing. The silence is deafaning."));

		map.hunterMove('w');
		assertTrue(map.getHunter().equals(new Point(3, 8)));
		assertTrue(map.isRunning());
		assertTrue(map.getCell(new Point(3, 8)).getIsEmpty());
		assertTrue(map
				.getCurrentState()
				.equals("You look around all you see is nothing. The silence is deafaning."));

		map.hunterMove('d');
		assertTrue(map.getHunter().equals(new Point(4, 8)));
		assertTrue(map.isRunning());
		assertTrue(map.getCell(new Point(4, 8)).getIsEmpty());
		assertTrue(map
				.getCurrentState()
				.equals("You look around all you see is nothing. The silence is deafaning."));

		map.hunterMove('w');
		assertTrue(map.getHunter().equals(new Point(4, 7)));
		assertTrue(map.isRunning());
		assertTrue(map.getCell(new Point(4, 7)).getIsEmpty());
		assertTrue(map
				.getCurrentState()
				.equals("You look around all you see is nothing. The silence is deafaning."));

		map.hunterMove('w');
		assertTrue(map.getHunter().equals(new Point(4, 6)));
		assertTrue(map.isRunning());
		assertFalse(map.getCell(new Point(4, 6)).getIsEmpty());
		assertTrue(map.getCell(new Point(4, 6)).getBlood());
		assertTrue(map
				.getCurrentState()
				.equals("Your feet slip a bit. You look down and see the floor covered in blood."));

		map.hunterMove('w');
		assertTrue(map.getHunter().equals(new Point(4, 5)));
		assertTrue(map.isRunning());
		assertFalse(map.getCell(new Point(4, 5)).getIsEmpty());
		assertTrue(map.getCell(new Point(4, 5)).getGoop());
		assertTrue(map
				.getCurrentState()
				.equals("Eww. You walked onto a reddish green mix of blood and slime. It looks like goop."));

		map.hunterMove('a');
		assertTrue(map.getHunter().equals(new Point(3, 5)));
		assertTrue(map.isRunning());
		assertFalse(map.getCell(new Point(3, 5)).getIsEmpty());
		assertTrue(map.getCell(new Point(3, 5)).getBlood());
		assertTrue(map
				.getCurrentState()
				.equals("Your feet slip a bit. You look down and see the floor covered in blood."));

		map.hunterMove('w');
		assertTrue(map.getHunter().equals(new Point(3, 4)));
		assertTrue(map.isRunning());
		assertFalse(map.getCell(new Point(4, 4)).getIsEmpty());
		assertTrue(map.getCell(new Point(3, 4)).getBlood());
		assertTrue(map
				.getCurrentState()
				.equals("Your feet slip a bit. You look down and see the floor covered in blood."));

		map.shootArrow('A');
		assertTrue(map.getHunter().equals(new Point(3, 4)));
		assertFalse(map.getCell(new Point(3, 4)).getIsEmpty());
		assertFalse(map.getCell(new Point(3, 4)).getWumpus());
		assertTrue(map
				.getCurrentState()
				.equals("You aim and fire you weapon of choice. Your trusty bow.\n"
						+ "The arrow whistles through the air. Your aim is true.\n"
						+ "The beast is dead.\n" + "You return home a hero."));
		assertFalse(map.isRunning());
	}

	/*
	 * This method will take the role of the game runner and play through an
	 * entire game scenario. It will not use user input and will pass in valid
	 * points to the gameMap.
	 * 
	 * This method asserts that shooting left when there is no wumpus in that
	 * direction, wraparound included will kill the player.
	 */
	@Test
	public void testGameShootSelfLeftWrap() {
		Model map = new Model();
		map.setWumpus(new Point(4, 4));
		assertTrue(map.getCell(new Point(4, 4)).getWumpus());
		assertTrue(map.getCell(new Point(4, 4)).getWumpus());
		assertTrue(map.getCell(new Point(4, 2)).getBlood());
		assertTrue(map.getCell(new Point(3, 3)).getBlood());
		assertTrue(map.getCell(new Point(3, 4)).getBlood());
		assertTrue(map.getCell(new Point(3, 5)).getBlood());
		assertTrue(map.getCell(new Point(2, 4)).getBlood());
		assertTrue(map.getCell(new Point(3, 4)).getBlood());
		assertTrue(map.getCell(new Point(5, 4)).getBlood());
		assertTrue(map.getCell(new Point(6, 4)).getBlood());
		assertTrue(map.getCell(new Point(3, 5)).getBlood());
		assertTrue(map.getCell(new Point(4, 5)).getBlood());
		assertTrue(map.getCell(new Point(5, 5)).getBlood());
		assertTrue(map.getCell(new Point(4, 6)).getBlood());

		ArrayList<Point> pitPoints = new ArrayList<Point>();
		pitPoints.add(new Point(2, 2));
		pitPoints.add(new Point(5, 5));
		pitPoints.add(new Point(9, 9));
		map.setPits(pitPoints);

		assertTrue(map.getCell(new Point(2, 2)).getPit());
		assertTrue(map.getCell(new Point(1, 2)).getSlime());
		assertTrue(map.getCell(new Point(3, 2)).getSlime());
		assertTrue(map.getCell(new Point(2, 1)).getSlime());
		assertTrue(map.getCell(new Point(2, 3)).getSlime());

		assertTrue(map.getCell(new Point(5, 5)).getPit());
		assertTrue(map.getCell(new Point(5, 6)).getSlime());
		assertTrue(map.getCell(new Point(6, 5)).getSlime());
		assertTrue(map.getCell(new Point(5, 4)).getGoop());
		assertTrue(map.getCell(new Point(4, 5)).getGoop());

		assertTrue(map.getCell(new Point(9, 9)).getPit());
		assertTrue(map.getCell(new Point(8, 9)).getSlime());
		assertTrue(map.getCell(new Point(9, 8)).getSlime());
		assertTrue(map.getCell(new Point(0, 9)).getSlime());
		assertTrue(map.getCell(new Point(9, 0)).getSlime());

		map.setHunter(new Point(3, 8));
		assertTrue(map.getHunter().equals(new Point(3, 8)));
		assertTrue(map.isRunning());

		map.shootArrow('A');
		assertTrue(map.getHunter().equals(new Point(3, 8)));
		assertTrue(map.getCell(new Point(3, 8)).getIsEmpty());
		assertEquals(
				map.getCurrentState(),
				("You aim and fire you weapon of choice. Your trusty bow.\n"
						+ "The arrow whistles through the air. Alas your target is not in that direction.\n"
						+ "As you are about to turn away a portal appears infront of the arrow. The arrow enters the portal.\n"
						+ "You hear a sound behind you. Somehow another portal has opened up behind you. The arrow is flying towards you.\n"
						+ "The arrow hits you in the ending your carrer as an explorer. Unable to walk and loosing blood you die alone."));
		assertFalse(map.isRunning());
	}

	/*
	 * This method will take the role of the game runner and play through an
	 * entire game scenario. It will not use user input and will pass in valid
	 * points to the gameMap.
	 * 
	 * This method asserts that shooting right when there is no wumpus in that
	 * direction, wraparound included will kill the player.
	 */
	@Test
	public void testGameShootSelfRightWrap() {
		Model map = new Model();
		map.setWumpus(new Point(4, 4));
		assertTrue(map.getCell(new Point(4, 4)).getWumpus());
		assertTrue(map.getCell(new Point(4, 4)).getWumpus());
		assertTrue(map.getCell(new Point(4, 2)).getBlood());
		assertTrue(map.getCell(new Point(3, 3)).getBlood());
		assertTrue(map.getCell(new Point(3, 4)).getBlood());
		assertTrue(map.getCell(new Point(3, 5)).getBlood());
		assertTrue(map.getCell(new Point(2, 4)).getBlood());
		assertTrue(map.getCell(new Point(3, 4)).getBlood());
		assertTrue(map.getCell(new Point(5, 4)).getBlood());
		assertTrue(map.getCell(new Point(6, 4)).getBlood());
		assertTrue(map.getCell(new Point(3, 5)).getBlood());
		assertTrue(map.getCell(new Point(4, 5)).getBlood());
		assertTrue(map.getCell(new Point(5, 5)).getBlood());
		assertTrue(map.getCell(new Point(4, 6)).getBlood());

		ArrayList<Point> pitPoints = new ArrayList<Point>();
		pitPoints.add(new Point(2, 2));
		pitPoints.add(new Point(5, 5));
		pitPoints.add(new Point(9, 9));
		map.setPits(pitPoints);

		assertTrue(map.getCell(new Point(2, 2)).getPit());
		assertTrue(map.getCell(new Point(1, 2)).getSlime());
		assertTrue(map.getCell(new Point(3, 2)).getSlime());
		assertTrue(map.getCell(new Point(2, 1)).getSlime());
		assertTrue(map.getCell(new Point(2, 3)).getSlime());

		assertTrue(map.getCell(new Point(5, 5)).getPit());
		assertTrue(map.getCell(new Point(5, 6)).getSlime());
		assertTrue(map.getCell(new Point(6, 5)).getSlime());
		assertTrue(map.getCell(new Point(5, 4)).getGoop());
		assertTrue(map.getCell(new Point(4, 5)).getGoop());

		assertTrue(map.getCell(new Point(9, 9)).getPit());
		assertTrue(map.getCell(new Point(8, 9)).getSlime());
		assertTrue(map.getCell(new Point(9, 8)).getSlime());
		assertTrue(map.getCell(new Point(0, 9)).getSlime());
		assertTrue(map.getCell(new Point(9, 0)).getSlime());

		map.setHunter(new Point(3, 8));
		assertTrue(map.getHunter().equals(new Point(3, 8)));
		assertTrue(map.isRunning());

		map.shootArrow('D');
		assertTrue(map.getHunter().equals(new Point(3, 8)));
		assertTrue(map.getCell(new Point(3, 8)).getIsEmpty());
		assertTrue(map
				.getCurrentState()
				.equals("You aim and fire you weapon of choice. Your trusty bow.\n"
						+ "The arrow whistles through the air. Alas your target is not in that direction.\n"
						+ "As you are about to turn away a portal appears infront of the arrow. The arrow enters the portal.\n"
						+ "You hear a sound behind you. Somehow another portal has opened up behind you. The arrow is flying towards you.\n"
						+ "The arrow hits you in the ending your carrer as an explorer. Unable to walk and loosing blood you die alone."));
		assertFalse(map.isRunning());
	}

	/*
	 * This method will take the role of the game runner and play through an
	 * entire game scenario. It will not use user input and will pass in valid
	 * points to the gameMap.
	 * 
	 * This method asserts that shooting right when there is no wumpus in that
	 * direction, wraparound included will kill the player.
	 */
	@Test
	public void testGameShootUpWrap() {
		Model map = new Model();
		map.setWumpus(new Point(4, 4));
		assertTrue(map.getCell(new Point(4, 4)).getWumpus());
		assertTrue(map.getCell(new Point(4, 4)).getWumpus());
		assertTrue(map.getCell(new Point(4, 2)).getBlood());
		assertTrue(map.getCell(new Point(3, 3)).getBlood());
		assertTrue(map.getCell(new Point(3, 4)).getBlood());
		assertTrue(map.getCell(new Point(3, 5)).getBlood());
		assertTrue(map.getCell(new Point(2, 4)).getBlood());
		assertTrue(map.getCell(new Point(3, 4)).getBlood());
		assertTrue(map.getCell(new Point(5, 4)).getBlood());
		assertTrue(map.getCell(new Point(6, 4)).getBlood());
		assertTrue(map.getCell(new Point(3, 5)).getBlood());
		assertTrue(map.getCell(new Point(4, 5)).getBlood());
		assertTrue(map.getCell(new Point(5, 5)).getBlood());
		assertTrue(map.getCell(new Point(4, 6)).getBlood());

		ArrayList<Point> pitPoints = new ArrayList<Point>();
		pitPoints.add(new Point(2, 2));
		pitPoints.add(new Point(5, 5));
		pitPoints.add(new Point(9, 9));
		map.setPits(pitPoints);

		assertTrue(map.getCell(new Point(2, 2)).getPit());
		assertTrue(map.getCell(new Point(1, 2)).getSlime());
		assertTrue(map.getCell(new Point(3, 2)).getSlime());
		assertTrue(map.getCell(new Point(2, 1)).getSlime());
		assertTrue(map.getCell(new Point(2, 3)).getSlime());

		assertTrue(map.getCell(new Point(5, 5)).getPit());
		assertTrue(map.getCell(new Point(5, 6)).getSlime());
		assertTrue(map.getCell(new Point(6, 5)).getSlime());
		assertTrue(map.getCell(new Point(5, 4)).getGoop());
		assertTrue(map.getCell(new Point(4, 5)).getGoop());

		assertTrue(map.getCell(new Point(9, 9)).getPit());
		assertTrue(map.getCell(new Point(8, 9)).getSlime());
		assertTrue(map.getCell(new Point(9, 8)).getSlime());
		assertTrue(map.getCell(new Point(0, 9)).getSlime());
		assertTrue(map.getCell(new Point(9, 0)).getSlime());

		map.setHunter(new Point(3, 8));
		assertTrue(map.getHunter().equals(new Point(3, 8)));
		assertTrue(map.isRunning());

		map.shootArrow('D');
		assertTrue(map.getHunter().equals(new Point(3, 8)));
		assertTrue(map.getCell(new Point(3, 8)).getIsEmpty());
		assertTrue(map
				.getCurrentState()
				.equals("You aim and fire you weapon of choice. Your trusty bow.\n"
						+ "The arrow whistles through the air. Alas your target is not in that direction.\n"
						+ "As you are about to turn away a portal appears infront of the arrow. The arrow enters the portal.\n"
						+ "You hear a sound behind you. Somehow another portal has opened up behind you. The arrow is flying towards you.\n"
						+ "The arrow hits you in the ending your carrer as an explorer. Unable to walk and loosing blood you die alone."));
		assertFalse(map.isRunning());
	}

	/*
	 * This method will take the role of the game runner and play through an
	 * entire game scenario. It will not use user input and will pass in valid
	 * points to the gameMap.
	 * 
	 * This method asserts that shooting down when there is no wumpus in that
	 * direction, wraparound included will kill the player.
	 */
	@Test
	public void testGameShootSelfDownWrap() {
		Model map = new Model();
		map.setWumpus(new Point(4, 4));
		assertTrue(map.getCell(new Point(4, 4)).getWumpus());
		assertTrue(map.getCell(new Point(4, 4)).getWumpus());
		assertTrue(map.getCell(new Point(4, 2)).getBlood());
		assertTrue(map.getCell(new Point(3, 3)).getBlood());
		assertTrue(map.getCell(new Point(3, 4)).getBlood());
		assertTrue(map.getCell(new Point(3, 5)).getBlood());
		assertTrue(map.getCell(new Point(2, 4)).getBlood());
		assertTrue(map.getCell(new Point(3, 4)).getBlood());
		assertTrue(map.getCell(new Point(5, 4)).getBlood());
		assertTrue(map.getCell(new Point(6, 4)).getBlood());
		assertTrue(map.getCell(new Point(3, 5)).getBlood());
		assertTrue(map.getCell(new Point(4, 5)).getBlood());
		assertTrue(map.getCell(new Point(5, 5)).getBlood());
		assertTrue(map.getCell(new Point(4, 6)).getBlood());

		ArrayList<Point> pitPoints = new ArrayList<Point>();
		pitPoints.add(new Point(2, 2));
		pitPoints.add(new Point(5, 5));
		pitPoints.add(new Point(9, 9));
		map.setPits(pitPoints);

		assertTrue(map.getCell(new Point(2, 2)).getPit());
		assertTrue(map.getCell(new Point(1, 2)).getSlime());
		assertTrue(map.getCell(new Point(3, 2)).getSlime());
		assertTrue(map.getCell(new Point(2, 1)).getSlime());
		assertTrue(map.getCell(new Point(2, 3)).getSlime());

		assertTrue(map.getCell(new Point(5, 5)).getPit());
		assertTrue(map.getCell(new Point(5, 6)).getSlime());
		assertTrue(map.getCell(new Point(6, 5)).getSlime());
		assertTrue(map.getCell(new Point(5, 4)).getGoop());
		assertTrue(map.getCell(new Point(4, 5)).getGoop());

		assertTrue(map.getCell(new Point(9, 9)).getPit());
		assertTrue(map.getCell(new Point(8, 9)).getSlime());
		assertTrue(map.getCell(new Point(9, 8)).getSlime());
		assertTrue(map.getCell(new Point(0, 9)).getSlime());
		assertTrue(map.getCell(new Point(9, 0)).getSlime());

		map.setHunter(new Point(3, 8));
		assertTrue(map.getHunter().equals(new Point(3, 8)));
		assertTrue(map.isRunning());

		map.shootArrow('S');
		assertTrue(map.getHunter().equals(new Point(3, 8)));
		assertTrue(map.getCell(new Point(3, 8)).getIsEmpty());
		assertTrue(map
				.getCurrentState()
				.equals("You aim and fire you weapon of choice. Your trusty bow.\n"
						+ "The arrow whistles through the air. Alas your target is not in that direction.\n"
						+ "As you are about to turn away a portal appears infront of the arrow. The arrow enters the portal.\n"
						+ "You hear a sound behind you. Somehow another portal has opened up behind you. The arrow is flying towards you.\n"
						+ "The arrow hits you in the ending your carrer as an explorer. Unable to walk and loosing blood you die alone."));
		assertFalse(map.isRunning());
	}
}
