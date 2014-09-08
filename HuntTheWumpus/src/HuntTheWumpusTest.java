import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.Test;


public class HuntTheWumpusTest {

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
		System.out.println(map);
	}
}
