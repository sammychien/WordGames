package wordhunt;

import static org.junit.Assert.*;

import org.junit.Test;

public class JUnitTests {
	
	@Test
	public void testCalcCoords1() {
		Coordinate c = Coordinate.calcCoordinate(5, 3, 7);
		// check col
		assertTrue(c.getX() == 1);
		// check row
		assertTrue(c.getY() == 2);
	}
	
	@Test
	public void testCalcCoords2() {
		Coordinate c = Coordinate.calcCoordinate(5, 3, 0);
		// check col
		assertTrue(c.getX() == 0);
		// check row
		assertTrue(c.getY() == 0);
	}

	@Test
	public void testCalcCoords3() {
		Coordinate c = Coordinate.calcCoordinate(5, 3, 15);
		assertNull(c);
	}
	
	@Test
	public void testCalcCoords4() {
		Coordinate c = Coordinate.calcCoordinate(5, 3, -1);
		assertNull(c);		
	}
	
	@Test
	public void testCalcCoords5() {
		Coordinate c = Coordinate.calcCoordinate(5, 3, 17);
		assertNull(c);
	}
	@Test
	public void testCalcCoords6() {
		Coordinate c = Coordinate.calcCoordinate(5, 3, 1);
		// check col
		assertTrue(c.getX() == 1);
		// check row
		assertTrue(c.getY() == 0);
	}
	@Test
	public void testCalcCoords7() {
		Coordinate c = Coordinate.calcCoordinate(5, 3, 3);
		// check col
		assertTrue(c.getX() == 0);
		// check row
		assertTrue(c.getY() == 1);
	}
	@Test
	public void testCalcCoords8() {
		Coordinate c = Coordinate.calcCoordinate(5, 3, 11);
		// check col
		assertTrue(c.getX() == 2);
		// check row
		assertTrue(c.getY() == 3);
	}
	
}
