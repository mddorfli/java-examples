package omb.java.examples.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {

	private static int badValue;
	
	private static class Bad implements Runnable {
		
		private boolean increment;

		public Bad(boolean increment) {
			this.increment = increment;
		}
		
		@Override
		public void run() {
			for (int i = 0; i< 10000000; i++) {
				if (increment) {
					badValue++;
				} else {
					badValue--;
				}
			}
		}
	}
	
	private static AtomicInteger goodValue;
	
	private static class Good implements Runnable {
		private boolean increment;

		public Good(boolean increment) {
			this.increment = increment;
		}
		
		@Override
		public void run() {
			for (int i = 0; i< 10000000; i++) {
				if (increment) {
					goodValue.incrementAndGet();
				} else {
					goodValue.decrementAndGet();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println("You're doing it WRONG!!!");
		for (int i = 0; i < 10; i++) {
			badValue = 0;
			Thread badIncr = new Thread(new Bad(true));
			Thread badDecr = new Thread(new Bad(false));
			badIncr.start();
			badDecr.start();
			
			try {
				badIncr.join();
				badDecr.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Integer value is "+badValue);
		}
		
		System.out.println("You're doing it RIGHT!!!");
		for (int i = 0; i < 10; i++) {
			goodValue = new AtomicInteger(0);
			Thread goodIncr = new Thread(new Good(true));
			Thread goodDecr = new Thread(new Good(false));
			goodIncr.start();
			goodDecr.start();
			
			try {
				goodIncr.join();
				goodDecr.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Integer value is "+goodValue.get());
		}
	}
	
}
