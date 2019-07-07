package omb.java.examples.concurrency;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;

public class LinkedTransferQueueTest {

	private static final String[] WORDS = new String[] { "the", "quick", "brown", "fox", "jumped", "over", "the",
			"lazy", "dog" };

	public static void main(String[] args) {
		final LinkedTransferQueue<String> tq = new LinkedTransferQueue<>();

		System.out.println("You're doing it RIGHT!!!");
		// put in
		Thread putIn = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 10; i++) {
					tq.add(WORDS[(int) (Math.random() * WORDS.length)]);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						System.out.println("What? What?");
					}
				}
			};
		});

		// take out
		Thread takeOut = new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						String polled = tq.poll(2, TimeUnit.SECONDS);
						if (polled != null) {
							System.out.println(polled);
						} else {
							System.out.println("No more it seems...");
							break;
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		});

		putIn.start();
		takeOut.start();

		try {
			putIn.join();
			takeOut.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("You're doing it WRONG!!!");
		final Deque<String> dq = new LinkedList<>();

		putIn = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10000000; i++) {
					dq.add(WORDS[i % WORDS.length]);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		takeOut = new Thread(new Runnable() {
			@Override
			public void run() {
				int count = 0;
				while (true) {
					if (dq.isEmpty()) {
						System.out.println();
						try {
							Thread.sleep(1000);
							if (dq.isEmpty()) {
								break;
							}
						} catch (InterruptedException e) {
							System.out.println("What? What?");
						}
					}
					System.out.print('.');
					String removed = dq.remove();
					String expected = WORDS[count % WORDS.length];
					if (!expected.equals(removed)) {
						System.out.println("Mismatch: expected "+expected+" but got "+removed);
					}
					count++;
				}
				System.out.println("No more it seems...");
			}
		});
		putIn.start();
		takeOut.start();
	}

}
