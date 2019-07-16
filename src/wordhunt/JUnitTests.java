package wordhunt;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit test suite for Coordinate class
 * 
 * @author sammychien
 *
 */
public class JUnitTests {
	
	@Test
	public void testCalcCoords1() {
		Coordinate c = Coordinate.calcCoordinate(5, 3, 7);
		// check col
		assertTrue(c.getCol() == 1);
		// check row
		assertTrue(c.getRow() == 2);
	}
	
	@Test
	public void testCalcCoords2() {
		Coordinate c = Coordinate.calcCoordinate(5, 3, 0);
		// check col
		assertTrue(c.getCol() == 0);
		// check row
		assertTrue(c.getRow() == 0);
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
		assertTrue(c.getCol() == 1);
		// check row
		assertTrue(c.getRow() == 0);
	}
	@Test
	public void testCalcCoords7() {
		Coordinate c = Coordinate.calcCoordinate(5, 3, 3);
		// check col
		assertTrue(c.getCol() == 0);
		// check row
		assertTrue(c.getRow() == 1);
	}
	@Test
	public void testCalcCoords8() {
		Coordinate c = Coordinate.calcCoordinate(5, 3, 11);
		// check col
		assertTrue(c.getCol() == 2);
		// check row
		assertTrue(c.getRow() == 3);
	}
	
}
