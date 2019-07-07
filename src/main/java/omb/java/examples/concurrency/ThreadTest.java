package omb.java.examples.concurrency;

public class ThreadTest {

	public static void main(String args[]) throws Exception {
		Rocket a = new Rocket("Charlie");
		a.start();
		Rocket b = new Rocket("Horizon");
		b.start();
		
		Thread.sleep(1000);
		a.interrupt();
		
		Thread.sleep(1000);
		b.interrupt();
	}
	
	static class Rocket extends Thread {
		private String name;
		
		public Rocket(String name) {
			this.name = name;
		}
		
		
		public void run() {
			System.out.println("This is rocket "+name+": 3.. 2.. 1... Launch!!!");
			while (true) {
				try {
					sleep(10);
				} catch (InterruptedException e) {
					System.out.println("Mayday Mayday! "+name+" is crashing into ocean!!!");
					e.printStackTrace();
					break;
				}
			}
		}
	}
}

