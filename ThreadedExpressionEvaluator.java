package Multi_Threading;
import java.util.*;

public class ThreadedExpressionEvaluator {
	public static String operator_var = "";
	public static String Expression = ""; 
	public int count = 0;
	public static int n1;
	public Random rand = new Random();
	public static Stack<String> stack_operator = new Stack<>();
	public static Stack<Object> stack_operand = new Stack<>();
	public static double result;
	
	public enum Operator {
	    ADD("+"),
	    SUBTRACT("-"),
	    MULTIPLY("*"),
	    DIVIDE("/");

	    private final String operator;

	    private Operator(String operator) {
	      this.operator = operator;
	    }

	    public String getOperator() {
	      return this.operator;
	    }
	  }
	
	public synchronized void operand() {
		try {

			
			while(count < n1) {
				double n = rand.nextInt(99) + 1;
				

				if (operator_var == "/") {		
					double x1 = (double) stack_operand.remove(stack_operand.size() - 1);
					stack_operand.push(x1 / n);
					stack_operator.pop();
					
				} else if (operator_var == "*") {
					double x1 = (double) stack_operand.pop();
					stack_operand.push(x1 * n);
					stack_operator.pop();
				} else {
					stack_operand.push(n);
				}

				Expression = Expression + n;
				System.out.println(Expression);
				count++;
				notify();
				Thread.sleep(500);
				if (count < n1) {
					wait();
				}
			}
		} catch(Exception e) {
			
		}
	}
	
	public synchronized void operator() {
		try {
			while (count < n1) {
				
				int n = rand.nextInt(4);
				if (n == 0) {
					Expression = Expression + Operator.ADD.getOperator();
					operator_var = Operator.ADD.getOperator();
				} else if (n == 1) {
					Expression = Expression + Operator.SUBTRACT.getOperator();
					operator_var = Operator.SUBTRACT.getOperator();
				} else if (n == 2) {
					Expression = Expression + Operator.MULTIPLY.getOperator();
					operator_var = Operator.MULTIPLY.getOperator();
				} else {
					Expression = Expression + Operator.DIVIDE.getOperator();
					operator_var = Operator.DIVIDE.getOperator();
				}
				
				System.out.println(Expression);
				stack_operator.push(operator_var);
				count++;
				notify();
				Thread.sleep(500);
				if (count < n1)
					wait();
				}
		} catch(Exception e) {
			
		}
	}
	
	public static double getResult() {
		return result;
	}
	
	public static String getExpression() {
		return Expression;
	}
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in); 
		System.out.println("Enter the length of expression");
		n1 = sc.nextInt();
		ThreadedExpressionEvaluator x = new ThreadedExpressionEvaluator();
		
		Runnable obj1 = () ->
				{
					x.operand();
				}
		;
		
		Runnable obj2 = () -> 
				{
					x.operator();
				}
		;
		
		Thread t1 = new Thread(obj1);
		Thread t2 = new Thread(obj2);
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		if (n1 % 2 == 0) {
			stack_operator.pop();
		}
		
		while(stack_operand.size() != 1) {
			double x1 = (double) stack_operand.remove(stack_operand.size() - 1);
			double x2 = (double) stack_operand.remove(stack_operand.size() - 1);
			String op = (String)stack_operator.remove(stack_operator.size() - 1);
			
			if (op == "+") {
				stack_operand.push(x1 + x2);
			} else {
				stack_operand.push(x2 - x1);
			}
		}
		result = (double)stack_operand.pop();
		
		System.out.println("Expression : " + ThreadedExpressionEvaluator.getExpression());
		System.out.println("Result : " + ThreadedExpressionEvaluator.getResult());
		
		sc.close();
	}
}