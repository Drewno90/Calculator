package calculator.tests;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import calculator.calcapp.CalcApp;

public class CalculationTest {

	@Test
	public void additionSuccess()
	{
		CalcApp.result=0;
		CalcApp.initializeCommands();
		CalcApp.calculations.get("+").execute(5.0f, 4.9f);
		assertEquals("5+4.9 must be 9.9",9.9, CalcApp.result, 0.1);

	}
	
	@Test
	public void subtractionSuccess()
	{
		CalcApp.result=0;
		CalcApp.initializeCommands();
		CalcApp.calculations.get("-").execute(5.0f, 4.9f);
		assertEquals("5-4.9 must be 0.1",0.1, CalcApp.result,0.1);
	}
	
	@Test
	public void mulitiplicationSuccess()
	{
		CalcApp.result=0;
		CalcApp.initializeCommands();
		CalcApp.calculations.get("*").execute(2, 2.5f);
		assertEquals("2*2.5 must be 5", 5, CalcApp.result,0);
	}
	
	@Test
	public void divisionSuccess()
	{
		CalcApp.result=0;
		CalcApp.initializeCommands();
		CalcApp.calculations.get("/").execute(2, 0.5f);
		assertEquals("2*0.5 must be 4", 4, CalcApp.result,0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void divisionByZero()
	{
		CalcApp.result=0;
		CalcApp.initializeCommands();
		CalcApp.calculations.get("/").execute(2, 0);
	}
	
}
