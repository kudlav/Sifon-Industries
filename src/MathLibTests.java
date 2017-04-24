import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;

/**
 * Class with JUnit tests for MathLib.
 * 
 * @author Rengyr
 * @author AdamKuba
 */
public class MathLibTests {
	
	/**
	 * Junit tests for {@link MathLib#idiv(BigDecimal, BigDecimal)} method.
	 */
	@Test
	public void divideTest(){
		assertTrue(new BigDecimal(2).compareTo(MathLib.idiv(new BigDecimal(10), new BigDecimal(5)))==0);
		assertTrue(new BigDecimal(-2).compareTo(MathLib.idiv(new BigDecimal(-10), new BigDecimal(5)))==0);
		assertTrue(new BigDecimal(-2).compareTo(MathLib.idiv(new BigDecimal(10), new BigDecimal(-5)))==0);
		assertTrue(new BigDecimal(2).compareTo(MathLib.idiv(new BigDecimal(-10), new BigDecimal(-5)))==0);
		
		assertTrue(new BigDecimal("0.5").compareTo(MathLib.idiv(new BigDecimal(5), new BigDecimal(10)))==0);
		assertTrue(new BigDecimal("-0.5").compareTo(MathLib.idiv(new BigDecimal(-5), new BigDecimal(10)))==0);
		assertTrue(new BigDecimal("-0.5").compareTo(MathLib.idiv(new BigDecimal(5), new BigDecimal(-10)))==0);
		assertTrue(new BigDecimal("0.5").compareTo(MathLib.idiv(new BigDecimal(-5), new BigDecimal(-10)))==0);
		
		assertTrue(new BigDecimal(8).compareTo(MathLib.idiv(new BigDecimal(8), new BigDecimal(1)))==0);
		assertTrue(new BigDecimal(-8).compareTo(MathLib.idiv(new BigDecimal(-8), new BigDecimal(1)))==0);
		assertTrue(new BigDecimal(-8).compareTo(MathLib.idiv(new BigDecimal(8), new BigDecimal(-1)))==0);
		assertTrue(new BigDecimal(8).compareTo(MathLib.idiv(new BigDecimal(-8), new BigDecimal(-1)))==0);
		
		assertTrue(new BigDecimal(0).compareTo(MathLib.idiv(new BigDecimal(0), new BigDecimal(5)))==0);
		assertTrue(new BigDecimal(0).compareTo(MathLib.idiv(new BigDecimal(0), new BigDecimal(-5)))==0);
		
	}
	
	/**
	 * Junit tests for zero division in {@link MathLib#idiv(BigDecimal, BigDecimal)} method.
	 */
	@Test
	public void zeroDivisionTest(){
		try {
			 MathLib.idiv(new BigDecimal(10), new BigDecimal(0));
			fail("Didn't throw zero divison exception!");
		} catch (ArithmeticException e) {}
		
		try {
			 MathLib.idiv(new BigDecimal(-10), new BigDecimal(0));
			fail("Didn't throw zero divison exception!");
		} catch (ArithmeticException e) {}
		
		try {
			 MathLib.idiv(new BigDecimal("0.3"), new BigDecimal(0));
			fail("Didn't throw zero divison exception!");
		} catch (ArithmeticException e) {}
		
		try {
			 MathLib.idiv(new BigDecimal("-0.3"), new BigDecimal(0));
			fail("Didn't throw zero divison exception!");
		} catch (ArithmeticException e) {}

	}
	
	/**
	 * Junit tests for {@link MathLib#mod(BigInteger, BigInteger)} method.
	 */
	@Test
	public void modTest(){
		assertTrue(new BigDecimal(2).compareTo(MathLib.mod(new BigInteger("8"),new BigInteger("3")))==0);
		assertTrue(new BigDecimal(0).compareTo(MathLib.mod(new BigInteger("9"),new BigInteger("3")))==0);
	}
	
	/**
	 * Junit tests for {@link MathLib#imul(BigDecimal, BigDecimal)} method.
	 */
	@Test
	public void multiplicationTest(){
		assertTrue(new BigDecimal(50).compareTo(MathLib.imul(new BigDecimal(10), new BigDecimal(5)))==0);
		assertTrue(new BigDecimal(-50).compareTo(MathLib.imul(new BigDecimal(-10), new BigDecimal(5)))==0);
		assertTrue(new BigDecimal(-50).compareTo(MathLib.imul(new BigDecimal(10), new BigDecimal(-5)))==0);
		assertTrue(new BigDecimal(50).compareTo(MathLib.imul(new BigDecimal(-10), new BigDecimal(-5)))==0);
		
		assertTrue(new BigDecimal(50).compareTo(MathLib.imul(new BigDecimal(5), new BigDecimal(10)))==0);
		assertTrue(new BigDecimal(-50).compareTo(MathLib.imul(new BigDecimal(-5), new BigDecimal(10)))==0);
		assertTrue(new BigDecimal(-50).compareTo(MathLib.imul(new BigDecimal(5), new BigDecimal(-10)))==0);
		assertTrue(new BigDecimal(50).compareTo(MathLib.imul(new BigDecimal(-5), new BigDecimal(-10)))==0);
		
		assertTrue(new BigDecimal(8).compareTo(MathLib.imul(new BigDecimal(8), new BigDecimal(1)))==0);
		assertTrue(new BigDecimal(-8).compareTo(MathLib.imul(new BigDecimal(-8), new BigDecimal(1)))==0);
		assertTrue(new BigDecimal(-8).compareTo(MathLib.imul(new BigDecimal(8), new BigDecimal(-1)))==0);
		assertTrue(new BigDecimal(8).compareTo(MathLib.imul(new BigDecimal(-8), new BigDecimal(-1)))==0);
		
		assertTrue(new BigDecimal(0).compareTo(MathLib.imul(new BigDecimal(0), new BigDecimal(5)))==0);
		assertTrue(new BigDecimal(0).compareTo(MathLib.imul(new BigDecimal(-0), new BigDecimal(-5)))==0);
		
		assertTrue(new BigDecimal(0).compareTo(MathLib.imul(new BigDecimal("0.4"), new BigDecimal(0)))==0);
		assertTrue(new BigDecimal(0).compareTo(MathLib.imul(new BigDecimal("-0.4"), new BigDecimal(0)))==0);
	}
	
	/**
	 * Junit tests for {@link MathLib#add(BigDecimal, BigDecimal)} method.
	 */
	@Test
	public void additionTest(){
		assertTrue(new BigDecimal(15).compareTo(MathLib.add(new BigDecimal(10), new BigDecimal(5)))==0);
		assertTrue(new BigDecimal(-5).compareTo(MathLib.add(new BigDecimal(-10), new BigDecimal(5)))==0);
		assertTrue(new BigDecimal(5).compareTo(MathLib.add(new BigDecimal(10), new BigDecimal(-5)))==0);
		assertTrue(new BigDecimal(-15).compareTo(MathLib.add(new BigDecimal(-10), new BigDecimal(-5)))==0);
		
		assertTrue(new BigDecimal(15).compareTo(MathLib.add(new BigDecimal(5), new BigDecimal(10)))==0);
		assertTrue(new BigDecimal(5).compareTo(MathLib.add(new BigDecimal(-5), new BigDecimal(10)))==0);
		assertTrue(new BigDecimal(-5).compareTo(MathLib.add(new BigDecimal(5), new BigDecimal(-10)))==0);
		assertTrue(new BigDecimal(-15).compareTo(MathLib.add(new BigDecimal(-5), new BigDecimal(-10)))==0);
		
		assertTrue(new BigDecimal(9).compareTo(MathLib.add(new BigDecimal(8), new BigDecimal(1)))==0);
		assertTrue(new BigDecimal(-7).compareTo(MathLib.add(new BigDecimal(-8), new BigDecimal(1)))==0);
		assertTrue(new BigDecimal(7).compareTo(MathLib.add(new BigDecimal(8), new BigDecimal(-1)))==0);
		assertTrue(new BigDecimal(-9).compareTo(MathLib.add(new BigDecimal(-8), new BigDecimal(-1)))==0);
		
		assertTrue(new BigDecimal(5).compareTo(MathLib.add(new BigDecimal(0), new BigDecimal(5)))==0);
		assertTrue(new BigDecimal(-5).compareTo(MathLib.add(new BigDecimal(0), new BigDecimal(-5)))==0);
		
		assertTrue(new BigDecimal("0.4").compareTo(MathLib.add(new BigDecimal("0.4"), new BigDecimal(0)))==0);
		assertTrue(new BigDecimal("-0.4").compareTo(MathLib.add(new BigDecimal("-0.4"), new BigDecimal(0)))==0);
	}
	
	/**
	 * Junit tests for {@link MathLib#sub(BigDecimal, BigDecimal)} method.
	 */
	@Test
	public void subtractionTest(){
		assertTrue(new BigDecimal(5).compareTo(MathLib.sub(new BigDecimal(10), new BigDecimal(5)))==0);
		assertTrue(new BigDecimal(-15).compareTo(MathLib.sub(new BigDecimal(-10), new BigDecimal(5)))==0);
		assertTrue(new BigDecimal(15).compareTo(MathLib.sub(new BigDecimal(10), new BigDecimal(-5)))==0);
		assertTrue(new BigDecimal(-5).compareTo(MathLib.sub(new BigDecimal(-10), new BigDecimal(-5)))==0);
		
		assertTrue(new BigDecimal(-5).compareTo(MathLib.sub(new BigDecimal(5), new BigDecimal(10)))==0);
		assertTrue(new BigDecimal(-15).compareTo(MathLib.sub(new BigDecimal(-5), new BigDecimal(10)))==0);
		assertTrue(new BigDecimal(15).compareTo(MathLib.sub(new BigDecimal(5), new BigDecimal(-10)))==0);
		assertTrue(new BigDecimal(5).compareTo(MathLib.sub(new BigDecimal(-5), new BigDecimal(-10)))==0);
		
		assertTrue(new BigDecimal(7).compareTo(MathLib.sub(new BigDecimal(8), new BigDecimal(1)))==0);
		assertTrue(new BigDecimal(-9).compareTo(MathLib.sub(new BigDecimal(-8), new BigDecimal(1)))==0);
		assertTrue(new BigDecimal(9).compareTo(MathLib.sub(new BigDecimal(8), new BigDecimal(-1)))==0);
		assertTrue(new BigDecimal(-7).compareTo(MathLib.sub(new BigDecimal(-8), new BigDecimal(-1)))==0);
		
		assertTrue(new BigDecimal(-5).compareTo(MathLib.sub(new BigDecimal(0), new BigDecimal(5)))==0);
		assertTrue(new BigDecimal(5).compareTo(MathLib.sub(new BigDecimal(0), new BigDecimal(-5)))==0);
		
		assertTrue(new BigDecimal("0.4").compareTo(MathLib.sub(new BigDecimal("0.4"), new BigDecimal(0)))==0);
		assertTrue(new BigDecimal("-0.4").compareTo(MathLib.sub(new BigDecimal("-0.4"), new BigDecimal(0)))==0);
	}

	/**
	 * Junit tests for {@link MathLib#fac(int)} method.
	 */
	@Test
	public void facTest(){
		assertTrue(new BigDecimal(24).compareTo(MathLib.fac(4))==0);
		assertTrue(new BigDecimal(120).compareTo(MathLib.fac(5))==0);
		
		assertTrue(new BigDecimal(1).compareTo(MathLib.fac(0))==0);
		
		assertTrue(new BigDecimal(-1).compareTo(MathLib.fac(-4))==0);

	}
	
	/**
	 * Junit tests for {@link MathLib#exp(BigInteger, BigDecimal)} method.
	 */
	@Test
	public void expTest(){
		assertTrue(new BigDecimal(4).compareTo(MathLib.exp(new BigInteger("2"), new BigDecimal(2)))==0);
		assertTrue(new BigDecimal(32).compareTo(MathLib.exp(new BigInteger("5"), new BigDecimal(2)))==0);
		
		assertTrue(new BigDecimal(1).compareTo(MathLib.exp(new BigInteger("0"), new BigDecimal(2)))==0);
		assertTrue(new BigDecimal(0).compareTo(MathLib.exp(new BigInteger("2"), new BigDecimal(0)))==0);
		
		assertTrue(new BigDecimal("0.25").compareTo(MathLib.exp(new BigInteger("2"), new BigDecimal("0.5")))==0);
		assertTrue(new BigDecimal("0.125").compareTo(MathLib.exp(new BigInteger("3"), new BigDecimal("0.5")))==0);
		
		assertTrue(new BigDecimal("0.001").compareTo(MathLib.exp(new BigInteger("-3"), new BigDecimal("10")))==0);
		assertTrue(new BigDecimal("-0.5").compareTo(MathLib.exp(new BigInteger("-1"), new BigDecimal("-2")))==0);

	}
	
	/**
	 * Junit tests for {@link MathLib#nRoot(BigInteger, BigDecimal)} method.
	 */
	@Test
	public void nRootTest(){
		assertTrue(new BigDecimal(2).compareTo(MathLib.nRoot(new BigInteger("2"), new BigDecimal(4)))==0);
		assertTrue(new BigDecimal(2).compareTo(MathLib.nRoot(new BigInteger("5"), new BigDecimal(32)))==0);
		
		assertTrue(new BigDecimal(0).compareTo(MathLib.nRoot(new BigInteger("2"), new BigDecimal(0)))==0);
		
		assertTrue(new BigDecimal("0.9330329915").compareTo(MathLib.nRoot(new BigInteger("10"), new BigDecimal(0.5)))==0);
	}
}
/*** End of MathLibTests.java file ***/