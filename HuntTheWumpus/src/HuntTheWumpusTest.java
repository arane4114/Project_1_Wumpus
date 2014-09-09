import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.Test;


public class HuntTheWumpusTest {
	
	/*
	 * This test checks the positioning of slime around a pit. There is no wraparound. 
	 */
	@Test
	public void testSlimeLogic() {
		Map map = new Map();
		ArrayList<Point> pitPoints = new ArrayList<Point>();
		Point point = new Point(2,2);
		pitPoints.add(point);
		map.setPits(pitPoints);
		assertTrue(map.getCell(point).getPit());
		assertTrue(map.getCell(new Point(1,2)).getSlime());
		assertTrue(map.getCell(new Point(3,2)).getSlime());
		assertTrue(map.getCell(new Point(2,1)).getSlime());
		assertTrue(map.getCell(new Point(2,3)).getSlime());
		System.out.println(map.toStringDebug());
	}
	
	/*
	 * This test checks the positioning of slime if we have wraparound.
	 */
	@Test
	public void testSlimeLogicWithWraparound(){
		Map map = new Map();
		ArrayList<Point> pitPoints = new ArrayList<Point>();
		pitPoints.add(new Point(0,0));
		map.setPits(pitPoints);
		assertTrue(map.getCell(new Point(0,0)).getPit());
		assertTrue(map.getCell(new Point(0,1)).getSlime());
		assertTrue(map.getCell(new Point(1,0)).getSlime());
		assertTrue(map.getCell(new Point(0,9)).getSlime());
		assertTrue(map.getCell(new Point(9,0)).getSlime());
	}
	
	/*
	 * More wraparound testing of pits and slime
	 */
	@Test
	public void testSlimeLogicWithMoreWraparound(){
		Map map = new Map();
		ArrayList<Point> pitPoints = new ArrayList<Point>();
		pitPoints.add(new Point(9,9));
		map.setPits(pitPoints);
		assertTrue(map.getCell(new Point(9,9)).getPit());
		assertTrue(map.getCell(new Point(8,9)).getSlime());
		assertTrue(map.getCell(new Point(9,8)).getSlime());
		assertTrue(map.getCell(new Point(0,9)).getSlime());
		assertTrue(map.getCell(new Point(9,0)).getSlime());
	}
	
	/*
	 * This tests the placement of the wumpus and the generation of blood.
	 */
	@Test
	public void testSetWumpus(){
		Map map = new Map();
		map.setWumpus(new Point(4,4));
		assertTrue(map.getCell(new Point(4,4)).getWumpus());
		assertTrue(map.getCell(new Point(4,2)).getBlood());
		assertTrue(map.getCell(new Point(3,3)).getBlood());
		assertTrue(map.getCell(new Point(3,4)).getBlood());
		assertTrue(map.getCell(new Point(3,5)).getBlood());
		assertTrue(map.getCell(new Point(2,4)).getBlood());
		assertTrue(map.getCell(new Point(3,4)).getBlood());
		assertTrue(map.getCell(new Point(5,4)).getBlood());
		assertTrue(map.getCell(new Point(6,4)).getBlood());
		assertTrue(map.getCell(new Point(3,5)).getBlood());
		assertTrue(map.getCell(new Point(4,5)).getBlood());
		assertTrue(map.getCell(new Point(5,5)).getBlood());
		assertTrue(map.getCell(new Point(4,6)).getBlood());
		System.out.println(map.toStringDebug());
	}
	
	/*
	 * This tests the wraparound for wumpus
	 */
	@Test
	public void testWumpusWraparound(){
		Map map = new Map();
		map.setWumpus(new Point(0,0));
		assertTrue(map.getCell(new Point(0,0)).getWumpus());
		assertTrue(map.getCell(new Point(1,0)).getBlood());
		assertTrue(map.getCell(new Point(2,0)).getBlood());
		assertTrue(map.getCell(new Point(8,0)).getBlood());
		assertTrue(map.getCell(new Point(9,0)).getBlood());
		assertTrue(map.getCell(new Point(0,1)).getBlood());
		assertTrue(map.getCell(new Point(1,1)).getBlood());
		assertTrue(map.getCell(new Point(0,2)).getBlood());
		assertTrue(map.getCell(new Point(9,1)).getBlood());
		assertTrue(map.getCell(new Point(0,8)).getBlood());
		assertTrue(map.getCell(new Point(9,0)).getBlood());
		assertTrue(map.getCell(new Point(9,1)).getBlood());
		assertTrue(map.getCell(new Point(9,9)).getBlood());
	}
}