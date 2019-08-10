package unitTests.WordHuntTests;

import static org.junit.Assert.*;

import org.junit.Test;

import wordhunt.Coordinate;

/**
 * Unit test suite for Coordinate class
 * 
 * @author sammychien
 *
 */
public class CoordinateTest {
	
	@Test
	public void Coordinate_AverageCase_Calculated() {
		Coordinate c = Coordinate.calcCoordinate(5, 3, 7);
		
		assertTrue(c.getCol() == 1);
		assertTrue(c.getRow() == 2);
	}

	@Test
	public void Coordinate_AverageCaseIndex1_Calculated() {
		Coordinate c = Coordinate.calcCoordinate(5, 3, 1);
		
		assertTrue(c.getCol() == 1);
		assertTrue(c.getRow() == 0);
	}
	
	@Test
	public void Coordinate_AverageCaseIndex3_Calculated() {
		Coordinate c = Coordinate.calcCoordinate(5, 3, 3);
		
		assertTrue(c.getCol() == 0);
		assertTrue(c.getRow() == 1);
	}
	
	@Test
	public void Coordinate_AverageCaseIndex11_Calculated() {
		Coordinate c = Coordinate.calcCoordinate(5, 3, 11);
		
		assertTrue(c.getCol() == 2);
		assertTrue(c.getRow() == 3);
	}
	
	@Test
	public void Coordinate_IndexAs0_Calculated() {
		Coordinate c = Coordinate.calcCoordinate(5, 3, 0);
		
		assertTrue(c.getCol() == 0);
		assertTrue(c.getRow() == 0);
	}

	@Test
	public void Coordinate_IndexOnOutsideLimit_ReturnNull() {
		Coordinate c = Coordinate.calcCoordinate(5, 3, 15);
		assertNull(c);
	}
	
	@Test
	public void Coordinate_NegativeIndex_ReturnNull() {
		Coordinate c = Coordinate.calcCoordinate(5, 3, -1);
		assertNull(c);		
	}
	
	@Test
	public void Coordinate_IndexOutOfBounds_ReturnNull() {
		Coordinate c = Coordinate.calcCoordinate(5, 3, 17);
		assertNull(c);
	}
	
	@Test
	public void Coordinate_HugeParamsIndexInBounds_Calculated() {
		Coordinate c = Coordinate.calcCoordinate(9999, 9999, 9999);
		
		assertTrue(c.getCol() == 0);
		assertTrue(c.getRow() == 1);
	}
	
	@Test
	public void Coordinate_HugeParamsIndexInsideMaxEdge_Calculated() {
		Coordinate c = Coordinate.calcCoordinate(9999, 9999, 99980000);
		
		assertTrue(c.getCol() == 9998);
		assertTrue(c.getRow() == 9998);
	}
	
	@Test
	public void Coordinate_HugeParamsIndexOutsideMaxEdge_Calculated() {
		Coordinate c = Coordinate.calcCoordinate(9999, 9999, 99980001);
		
		assertNull(c);
	}
	
	@Test
	public void Coordinate_ZeroRowsAverageIndex_ReturnNull() {
		Coordinate c = Coordinate.calcCoordinate(0, 15, 10);
		assertNull(c);
	}
	
	@Test
	public void Coordinate_ZeroRowsZeroIndex_ReturnNull() {
		Coordinate c = Coordinate.calcCoordinate(0, 15, 0);
		assertNull(c);
	}
	
	@Test
	public void Coordinate_ZeroRowsOutOfBoundsIndex_ReturnNull() {
		Coordinate c = Coordinate.calcCoordinate(0, 15, 100);
		assertNull(c);
	}
	
	@Test
	public void Coordinate_ZeroColsAverageIndex_ReturnNull() {
		Coordinate c = Coordinate.calcCoordinate(15, 0, 10);
		assertNull(c);
	}
	
	@Test
	public void Coordinate_ZeroColsZeroIndex_ReturnNull() {
		Coordinate c = Coordinate.calcCoordinate(15, 0, 0);
		assertNull(c);
	}
	
	
}
