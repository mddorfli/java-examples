package omb.java.examples.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class CopyOnWriteArrayListTest implements Runnable {

	private List<String> list;
	private boolean stop;

	public CopyOnWriteArrayListTest(List<String> list) {
		this.list = list;
		stop = false;
	}
	
	/**
	 * @param stop the stop to set
	 */
	public void stop() {
		this.stop = true;
	}

	
	/**
	 * @return the stop
	 */
	public boolean hasStopped() {
		return stop;
	}

	@Override
	public void run() {
		for (Iterator<String> iter = list.iterator(); iter.hasNext(); ) {
			String next;
			try {
				next = iter.next();
			} catch (Exception e) {
				e.printStackTrace();
				stop = true;
				return;
			}
			System.out.println("Reading... "+next);
			if (stop) {
				return;
			}
		}
	}

	private static void doConcurrentModification(List<String> list) {
		CopyOnWriteArrayListTest reader = new CopyOnWriteArrayListTest(list);
		new Thread(reader).start();
		try {
			while(!list.isEmpty() && !reader.hasStopped()) {
				String removed = list.remove((int)(Math.random()*list.size()));
				System.out.println("Removing... "+removed + " ("+list.size()+" remaining)");
			}
		} catch (java.util.ConcurrentModificationException e) {
			e.printStackTrace();
			reader.stop();
		}
	}
	
	private static void addThousandsOfWords(List<String> list) {
		for (int i = 0; i < 1000; i++) {
			char[] word = new char[1+(int)(Math.random()*9)];
			for (int j = 0; j < word.length; j++) {
				word[j] = (char)(((int)'a')+((int)(Math.random()*26)));
			}
			list.add(new String(word));
		}
	}
	
	
	public static void main(String[] args) {
		List<String> cow = new CopyOnWriteArrayList<>();
		List<String> chicken = new ArrayList<>();
		
		addThousandsOfWords(cow);
		doConcurrentModification(cow);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		addThousandsOfWords(chicken);
		doConcurrentModification(chicken);
	}

}
