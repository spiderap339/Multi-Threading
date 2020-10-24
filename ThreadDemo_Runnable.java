package Multi_Threading;



public class ThreadDemo_Runnable {

	public static void main(String[] args) throws Exception{
		// Anonymous class using lambda expression
		Runnable obj1 = () ->
				{
					try {
						for(int i = 0; i < 5; i++) {
							System.out.println("Hi");
							Thread.sleep(1000);
							
						}
					}
					catch(Exception e) {
						
					}
				}
		;
		
		// Anonymous class using lambda expression
		Runnable obj2 = () -> 
				{
					try {
						for(int i = 0; i < 5; i++) {
							System.out.println("Hello");
							Thread.sleep(1000);
						}
					}
					catch(Exception e) {
						
					}
				}
		;
		
		Thread t1 = new Thread(obj1);
		
		Thread t2 = new Thread(obj2);
		
		
		t1.start();
		try { Thread.sleep(10); } catch(Exception e) {};
		t2.start();
		
		// main thread will wait to complete t1 and t2.
		t1.join();
		t2.join();
	}	
}
