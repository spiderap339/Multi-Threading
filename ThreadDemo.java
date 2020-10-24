package Multi_Threading;

import java.util.*;

class Hiii extends Thread{
	
	public void run(){
		for(int i = 0; i < 5; i++) {
			try {
				System.out.println("Hi");
				wait();
//				sleep(1000);
			}
			catch(Exception e) {
				
			}
		}
	}
}

class Hellooo extends Thread{
	
	public void run() {
		for(int i = 0; i < 5; i++) {
			try {
				System.out.println("Hello");
				sleep(1000);
			}
			catch(Exception e) {
				
			}
		}
	}
}

public class ThreadDemo {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Hiii t1 = new Hiii();
		Hellooo t2 = new Hellooo();
		
		t1.start();
		t2.start();
		sc.close();
	}
}
