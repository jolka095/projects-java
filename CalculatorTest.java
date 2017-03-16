import org.junit.Test;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.Assert;
import static org.junit.Assert.*;

public class CalculatorTest {

//	@Test
//	public void testObject() {
//		CalculatorInterface c = new Calculator();
//		assertNotEquals("Obiekt wskazuje na NULL", null, c);
//	}
	
	@Test
	public void testSetReg() {
		CalculatorInterface c = new Calculator();
		int x = 123;
		c.setReg1(x);
		Assert.assertEquals("Blad metody ustawiajacej wartosc w rejestrze - nieprawidlowa wartosc w rejestrze nr 1", 123, c.getReg1());

		c.setReg2(x);
		c.swap();
		Assert.assertEquals("Blad metody ustawiajacej wartosc w rejestrze - nieprawidlowa wartosc w rejestrze nr 2", 123, c.getReg1());
	}
	
	@Test
	public void testSwap() {
		CalculatorInterface c = new Calculator();
		c.setReg1(10);
		c.setReg2(5);
		c.swap();
		Assert.assertEquals("Bledne dzialanie metody swap()", 5, c.getReg1());
	}
	
	@Test
	public void testAdd() {
		CalculatorInterface c = new Calculator();
		c.setReg1(20);
		c.setReg2(3);
		c.add();
		c.swap();
		Assert.assertEquals("Bledne dzialanie metody add()", 23, c.getReg1());
	}
	
	@Test
	public void testClear() {
		CalculatorInterface c = new Calculator();
		int x = 11;
		c.setReg1(x);
		c.clear();
		Assert.assertEquals("Nieprawidlowe dzialanie metody clear() - bledna wartosc w rejestrze nr 1", 0, c.getReg1());
		c.setReg2(x);
		c.clear();
 		c.swap();
 		Assert.assertEquals("Nieprawidlowe dzialanie metody clear() - bledna wartosc w rejestrze nr 2", 0, c.getReg1());
	}
	
	@Test
	public void testSetIfMatchReturnOld() {
		CalculatorInterface c = new Calculator();
		c.setReg1(3);
		c.setReg2(3);
		Assert.assertEquals("Bledne dzialanie metody testSetIfMatchReturnOld()", 3, c.setIfMatchReturnOld(3, 6));
	}
}