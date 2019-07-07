package omb.java.examples.concurrency;

import java.util.ArrayList;
import java.util.List;

public class ThreadTest2 implements Runnable {

	private volatile int number = 0;

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
//			synchronized(this) {
				number++;
//			}
			try {
				Thread.sleep((int)Math.random()*10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(number);
	}

	public static void main(String[] args)  {
		ThreadTest2 tt = new ThreadTest2();
		List<Thread> threads = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			Thread thread = new Thread(tt, "thread "+i);
			threads.add(thread);
			thread.start();
		}

		// wait until all threads have finished running.
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(thread.getName()+" joined");
		}
		System.out.println(tt.number);
	}
}
