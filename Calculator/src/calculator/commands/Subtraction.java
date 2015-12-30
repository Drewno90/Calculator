package calculator.commands;
import java.math.BigDecimal;
import java.util.Scanner;

import calculator.calcapp.CalcApp;

public class Subtraction implements Calculation {

	@Override
	public void execute(float a, float b) {

		CalcApp.result=a-b;

	}

	@Override
	public void printInfo() {
		System.out.println("Subtraction");

	}

}
