package omb.java.examples.concurrency;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

	private static ReentrantLock rl = new ReentrantLock();
	
	public static void main(String[] args) {
		
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 1000; i++) {
					try {
						while(!rl.tryLock(1, TimeUnit.SECONDS)) {}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					try {
						System.out.println(Thread.currentThread().getName()+" got the lock!");
						Thread.sleep(ThreadLocalRandom.current().nextInt(50));
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						rl.unlock();
					}
				}
			}
		};
		new Thread(runnable, "t1").start();
		new Thread(runnable, "t2").start();
		new Thread(runnable, "t3").start();
	}
}
