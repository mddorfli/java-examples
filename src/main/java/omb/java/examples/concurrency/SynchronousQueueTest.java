package omb.java.examples.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueTest  {

	private static final String[] WORDS = new String[] { "the", "quick", "brown", "fox", "jumped", "over", "the",
			"lazy", "dog" };
	private static final SynchronousQueue<String> Q = new SynchronousQueue<>(true);
	
	private static class QueueReader implements Runnable {
		private String name;
		public QueueReader(String name) {
			this.name = name;
		}
		@Override
		public void run() {
			while(true) {
				try {
					String word = Q.poll(1, TimeUnit.SECONDS);
					if (word != null) {
//						System.out.println("Reader "+name+" took '"+word+"'");
						System.out.print(name);
					} else {
						break;
					}
					Thread.sleep(ThreadLocalRandom.current().nextInt(50));
				} catch (InterruptedException e) {
					System.out.println("Reader "+name+" interrupted!");
				}
			}
			System.out.println("Seems to be done. Reader "+name+" quitting.");
		}
	}
	
	public static void main(String[] args) {
		
		Runnable writer = new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					try {
						String word = WORDS[i%WORDS.length];
						Q.put(word);
//						System.out.println("Writer put '"+word+"'");
						Thread.sleep(ThreadLocalRandom.current().nextInt(100));
					} catch (InterruptedException e) {
						System.out.println("Writer interrupted!");
					}
				}
			}
		};

		new Thread(writer).start();
		ExecutorService exe = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 26; i++) {
			exe.execute(new QueueReader(""+(char)((int)'A'+i)));
		}
	}
	
}
