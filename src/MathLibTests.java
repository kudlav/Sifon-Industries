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
	
	@Test
	public void multiplicationTest(){
		assertEquals(50f, MathLib.imul(10f, 5f), 0.0001f);
		assertEquals(-50f, MathLib.imul(-10f, 5f), 0.0001f);
		assertEquals(-50f, MathLib.imul(10f, -5f), 0.0001f);
		assertEquals(50f, MathLib.imul(-10f, -5f), 0.0001f);
		
		assertEquals(50f, MathLib.imul(5f, 10f), 0.0001f);
		assertEquals(-50f, MathLib.imul(-5f, 10f), 0.0001f);
		assertEquals(-50f, MathLib.imul(5f, -10f), 0.0001f);
		assertEquals(50f, MathLib.imul(-5f, -10f), 0.0001f);
		
		assertEquals(8f, MathLib.imul(8f, 1f), 0.0001f);
		assertEquals(-8f, MathLib.imul(-8f, 1f), 0.0001f);
		assertEquals(-8f, MathLib.imul(8f, -1f), 0.0001f);
		assertEquals(8f, MathLib.imul(-8f, -1f), 0.0001f);
		
		assertEquals(0f, MathLib.imul(0f, 5f), 0.0001f);
		assertEquals(0f, MathLib.imul(0f, -5f), 0.0001f);	
		
		assertEquals(0f, MathLib.imul(0.4f, 0f), 0.0001f);
		assertEquals(0f, MathLib.imul(-0.4f, 0f), 0.0001f);
	}
	
	@Test
	public void additionTest(){
		assertEquals(15f, MathLib.add(10f, 5f), 0.0001f);
		assertEquals(-5f, MathLib.add(-10f, 5f), 0.0001f);
		assertEquals(5f, MathLib.add(10f, -5f), 0.0001f);
		assertEquals(-15f, MathLib.add(-10f, -5f), 0.0001f);
		
		assertEquals(15f, MathLib.add(5f, 10f), 0.0001f);
		assertEquals(5f, MathLib.add(-5f, 10f), 0.0001f);
		assertEquals(-5f, MathLib.add(5f, -10f), 0.0001f);
		assertEquals(-15f, MathLib.add(-5f, -10f), 0.0001f);
		
		assertEquals(9f, MathLib.add(8f, 1f), 0.0001f);
		assertEquals(-7f, MathLib.add(-8f, 1f), 0.0001f);
		assertEquals(7f, MathLib.add(8f, -1f), 0.0001f);
		assertEquals(-9f, MathLib.add(-8f, -1f), 0.0001f);
		
		assertEquals(5f, MathLib.add(0f, 5f), 0.0001f);
		assertEquals(-5f, MathLib.add(0f, -5f), 0.0001f);	
		
		assertEquals(0.4f, MathLib.add(0.4f, 0f), 0.0001f);
		assertEquals(-0.4f, MathLib.add(-0.4f, 0f), 0.0001f);
	}
	
	@Test
	public void subtractionTest(){
		assertEquals(5f, MathLib.sub(10f, 5f), 0.0001f);
		assertEquals(-15f, MathLib.sub(-10f, 5f), 0.0001f);
		assertEquals(15f, MathLib.sub(10f, -5f), 0.0001f);
		assertEquals(-5f, MathLib.sub(-10f, -5f), 0.0001f);
		
		assertEquals(-5f, MathLib.sub(5f, 10f), 0.0001f);
		assertEquals(-15f, MathLib.sub(-5f, 10f), 0.0001f);
		assertEquals(15f, MathLib.sub(5f, -10f), 0.0001f);
		assertEquals(5f, MathLib.sub(-5f, -10f), 0.0001f);
		
		assertEquals(7f, MathLib.sub(8f, 1f), 0.0001f);
		assertEquals(-9f, MathLib.sub(-8f, 1f), 0.0001f);
		assertEquals(9f, MathLib.sub(8f, -1f), 0.0001f);
		assertEquals(-7f, MathLib.sub(-8f, -1f), 0.0001f);
		
		assertEquals(-5f, MathLib.sub(0f, 5f), 0.0001f);
		assertEquals(5f, MathLib.sub(0f, -5f), 0.0001f);	
		
		assertEquals(0.4f, MathLib.sub(0.4f, 0f), 0.0001f);
		assertEquals(-0.4f, MathLib.sub(-0.4f, 0f), 0.0001f);
	}

}