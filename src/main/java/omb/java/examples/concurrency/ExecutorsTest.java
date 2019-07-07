package omb.java.examples.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

public class ExecutorsTest {

	static class MyCallable implements Callable<Integer> {
		@Override
		public Integer call() throws Exception {
			int secsRunning = 0;
			while(secsRunning < Math.random()*10) {
				System.out.print(secsRunning+"...");
				Thread.sleep(1000);
				secsRunning++;
			}
			System.out.println();
			return Integer.valueOf(secsRunning);
		}
	}
	
	public static void main(String[] args) {
		ExecutorService e1 = Executors.newCachedThreadPool();
		ExecutorService e2 = Executors.newFixedThreadPool(5);
		ExecutorService e3 = Executors.newSingleThreadExecutor();
		ScheduledExecutorService e4 = Executors.newScheduledThreadPool(5);
		ScheduledExecutorService e5 = Executors.newSingleThreadScheduledExecutor();
		
		System.out.println("Submitting job...");
		Future<Integer> result = e2.submit(new MyCallable());
		while(!result.isDone()) {
			System.out.print("Done yet? ");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
		}
		System.out.println("Done");
		try {
			System.out.println("Result is: "+result.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
