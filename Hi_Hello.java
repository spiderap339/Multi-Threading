package Multi_Threading;

import java.util.*;


public class Hi_Hello {
	public static int count = 1;
	public static int n = 0;
	
	public synchronized void Hi() {
		while (count <= (2*n)) {
			try {			
				System.out.println("Hi");
				count++;
				notify();
				if (count <= (2*n)) {
					wait();
				}
			}
			catch(Exception e) {
				
			}
		}
	}
	
	public synchronized void Hello() {		
		while (count <= (2*n)) {
			try {
				System.out.println("Hello");
				count++;
				
				// by notify() other thread comes out of wait.
				notify();
				if (count <= (2*n)) {
					// wait() will release the lock of current thread.
					wait();
				}
			}
			catch(Exception e) {
				
			}
		}
	}

	public static void main(String[] args) throws Exception{
		
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		Hi_Hello x = new Hi_Hello();
		
		Runnable obj1 = () ->
				{
					x.Hi();
				}
		;
		
		Runnable obj2 = () -> 
				{
					x.Hello();
				}
		;
		
		Thread t1 = new Thread(obj1);
		Thread t2 = new Thread(obj2);
		t1.start();
		t2.start();
		sc.close();
	}
}
