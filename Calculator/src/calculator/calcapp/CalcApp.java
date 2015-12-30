package calculator.calcapp;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

import calculator.commands.Addition;
import calculator.commands.Calculation;
import calculator.commands.Division;
import calculator.commands.Multiplication;
import calculator.commands.Subtraction;
import calculator.exceptions.BadInputException;
import calculator.exceptions.TooManyInputValuesException;

public class CalcApp {
	
	public static Map<String, Calculation> calculations = new HashMap<String, Calculation>();
	private static Scanner scan = new Scanner(System.in);
	public static float result=0;
	
	private static final Map<String, Integer> operators = new HashMap<String, Integer>();
	static
	{
		operators.put("+", 0);
		operators.put("-", 0);
		operators.put("*", 1);
		operators.put("/", 1);
	}
	
	private static boolean isOperator(String input)   
    {  
        return operators.containsKey(input);  
    }  
	
	 public static String InfixToRPN(String[] input){
	 	   	
	    	StringBuilder out = new StringBuilder();
	    	Stack<String> stack = new Stack<String>();

	    	for(String in: input)
	    	{
	    		if(isOperator(in))
	    		{
	    			if(stack.isEmpty() || stack.peek().equals("(") )
	    				stack.push(in);
	    			else
	    			{
	    				while(!stack.isEmpty())
	    				{
	    					String operator2 = stack.peek();
	    					if(operators.get(operator2)>=operators.get(in))
	    						out.append(stack.pop() + " ");
	    					else
	    						break;
	    				}
	    				stack.push(in);
	    			}
	    		}
	    		else if(in.equals("("))
	    				stack.push(in);
	    		else if(in.equals(")"))
	    		{
	    			while(!stack.peek().equals("("))
	    				out.append((stack.pop()+ " "));
	    			stack.pop();
	    		}
	    		else
	    			out.append(in+ " ");
	    	}
	    	while(!stack.isEmpty())
	    		out.append((stack.pop()+ " "));
	        
			return  out.toString();
	    	
	    }
	    	
	 public static float calculateRPN(String[] input) throws BadInputException, TooManyInputValuesException
	 {
	   	List<String> out = new ArrayList<String>();
	    Stack<String> stack = new Stack<String>();
		 
	    for(String in : input)
	    {
	    	if(!isOperator(in))
	    		stack.push(in);
	    	else
	    	{
	    		if(stack.size()<2)
	    			throw new BadInputException();
	    		else
	    		{
	    			float value2 = Float.parseFloat(stack.pop());
	    			float value1 = Float.parseFloat(stack.pop());
	    			calculations.get(in).execute(value1, value2);
	    			stack.push(Float.toString(CalcApp.result));
	    			CalcApp.result=0;
	    		}
	    	}
	    }
	    if(stack.size()==1)
	    	return Float.valueOf(stack.pop());
	    else
	    	throw new TooManyInputValuesException();
	
	 }
	 
	 public static void inputValidation(String input) throws BadInputException
	 {
		 if(!input.matches("^[-0-9\\.\\+\\*\\/\\-\\s]{3,}$"))
         {
      	   System.out.println("Bad input format!");
      	   System.exit(0);
         }
                   
	 }
	
    public static void initializeCommands(){
    	calculations.put("+", new Addition());
    	calculations.put("-", new Subtraction());
    	calculations.put("*", new Multiplication());
    	calculations.put("/", new Division());
    }
	
   
    
	public static void main(String[] args) {

		initializeCommands();
        System.out.println("This calculator works only with expressions with spaces between each number/operator ( e.g. 2 + 5 * ( 1 - 2) )");
        while (true) {

           System.out.println("Enter calculation ");
           
           String calc = scan.nextLine();
           calc = calc.trim();
           
           if(calc.equals("exit"))
           {
        	   System.out.println("System will be terminated...");
        	   scan.close();
        	   System.exit(0);
           }
           
           try {
			inputValidation(calc);
		} catch (BadInputException e1) {
			System.out.println("The user has not input sufficient values in the expression.");
			e1.printStackTrace();
		}
                      
           String[] input = calc.split(" ");

           String rpn = InfixToRPN(input);

           String[] input2= rpn.split(" ");
	          
           
           try {
   			result=calculateRPN(input2);
   		} catch (BadInputException e) {
   			System.out.println("The user has not input sufficient values in the expression.");
   			e.printStackTrace();
   		} catch (TooManyInputValuesException e) {
   			System.out.println("The user input has too many values or bad input format.");
   			e.printStackTrace();
   		}
           System.out.println(result);

        }
		
	}

}
