package calculator.commands;
import java.math.BigDecimal;
import java.util.Scanner;

import calculator.calcapp.CalcApp;

public class Division implements Calculation {

	@Override
	public void execute(float a, float b) {


		if(b!=0)
			CalcApp.result=a/b;
		else 
			throw new IllegalArgumentException("Argument 'divisor' is 0");
	}

	@Override
	public void printInfo() {
		System.out.println("Division");

	}

}
