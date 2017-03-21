import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Rengyr
 *
 */
public class MathLibTests {
	
	@Test
	public void divideTest(){
		assertEquals(2f, MathLib.idiv(10f, 5f), 0.0001f);
		assertEquals(-2f, MathLib.idiv(-10f, 5f), 0.0001f);
		assertEquals(-2f, MathLib.idiv(10f, -5f), 0.0001f);
		assertEquals(2f, MathLib.idiv(-10f, -5f), 0.0001f);
		
		assertEquals(0.5f, MathLib.idiv(5f, 10f), 0.0001f);
		assertEquals(-0.5f, MathLib.idiv(-5f, 10f), 0.0001f);
		assertEquals(-0.5f, MathLib.idiv(5f, -10f), 0.0001f);
		assertEquals(0.5f, MathLib.idiv(-5f, -10f), 0.0001f);
		
		assertEquals(8f, MathLib.idiv(8f, 1f), 0.0001f);
		assertEquals(-8f, MathLib.idiv(-8f, 1f), 0.0001f);
		assertEquals(-8f, MathLib.idiv(8f, -1f), 0.0001f);
		assertEquals(8f, MathLib.idiv(-8f, -1f), 0.0001f);
		
		assertEquals(0f, MathLib.idiv(0f, 5f), 0.0001f);
		assertEquals(0f, MathLib.idiv(0f, 5f), 0.0001f);
		assertEquals(0f, MathLib.idiv(0f, -5f), 0.0001f);
		assertEquals(0f, MathLib.idiv(0f, -5f), 0.0001f);	
		
	}
	
	@Test
	public void zeroDivisionTest(){
		try {
			MathLib.idiv(10f, 0f);
			fail("Didn't throw zero divison exception!");
		} catch (IllegalArgumentException e) {}
		
		try {
			MathLib.idiv(-10f, 0f);
			fail("Didn't throw zero divison exception!");
		} catch (IllegalArgumentException e) {}
		
		try {
			MathLib.idiv(0.3f, 0f);
			fail("Didn't throw zero divison exception!");
		} catch (IllegalArgumentException e) {}
		
		try {
			MathLib.idiv(-0.3f, 0f);
			fail("Didn't throw zero divison exception!");
		} catch (IllegalArgumentException e) {}

	}
	
	@Test
	public void modTest(){
		assertEquals(2,MathLib.mod(8,3));
		assertEquals(0,MathLib.mod(9,3));
	}

}
