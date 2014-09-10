/*
 * @ - Author: Abhishek Rane
 * @ - Author: Bryce Hammond
 */
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.Test;

public class HuntTheWumpusTest {

	/*
	 * This test checks the positioning of slime around a pit. There is no
	 * wraparound.
	 */
	@Test
	public void testSlimeLogic() {
		Map map = new Map();
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
		Map map = new Map();
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
		Map map = new Map();
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
		Map map = new Map();
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
		Map map = new Map();
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
	 */
	@Test
	public void testGame() {
		Map map = new Map();
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
		assertTrue(map.isAlive());

		// move right
		map.hunterMove('d');
		assertTrue(map.getHunter().equals(new Point(4, 8)));
		assertTrue(map.isAlive());
		assertTrue(map.getCell(new Point(4, 8)).getIsEmpty());

		map.hunterMove('s');
		assertTrue(map.getHunter().equals(new Point(4, 9)));
		assertTrue(map.isAlive());
		assertTrue(map.getCell(new Point(4, 9)).getIsEmpty());

		map.hunterMove('a');
		assertTrue(map.getHunter().equals(new Point(3, 9)));
		assertTrue(map.isAlive());
		assertTrue(map.getCell(new Point(3, 9)).getIsEmpty());

		map.hunterMove('w');
		assertTrue(map.getHunter().equals(new Point(3, 8)));
		assertTrue(map.isAlive());
		assertTrue(map.getCell(new Point(3, 8)).getIsEmpty());

		map.hunterMove('d');
		assertTrue(map.getHunter().equals(new Point(4, 8)));
		assertTrue(map.isAlive());
		assertTrue(map.getCell(new Point(4, 8)).getIsEmpty());

		map.hunterMove('w');
		assertTrue(map.getHunter().equals(new Point(4, 7)));
		assertTrue(map.isAlive());
		assertTrue(map.getCell(new Point(4, 7)).getIsEmpty());

		map.hunterMove('w');
		assertTrue(map.getHunter().equals(new Point(4, 6)));
		assertTrue(map.isAlive());
		assertFalse(map.getCell(new Point(4, 6)).getIsEmpty());
		assertTrue(map.getCell(new Point(4, 6)).getBlood());

		map.hunterMove('w');
		assertTrue(map.getHunter().equals(new Point(4, 5)));
		assertTrue(map.isAlive());
		assertFalse(map.getCell(new Point(4, 5)).getIsEmpty());
		assertTrue(map.getCell(new Point(4, 5)).getGoop());

		System.out.println(map.toStringDebug());
	}
}
