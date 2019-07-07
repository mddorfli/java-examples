package omb.java.examples.concurrency;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentMapTest {
	public static void main(String[] args) {
		ConcurrentMap<String,Integer> cmap = new ConcurrentHashMap<>();
		cmap.putIfAbsent("John", 23);
		cmap.putIfAbsent("John", 27);
		System.out.println(cmap.get("John"));
		
		cmap.replace("John", 27, 29);
		System.out.println(cmap.get("John"));
		
		cmap.replace("John", 23, 29);
		System.out.println(cmap.get("John"));
		
		cmap.remove("John", 23);
		System.out.println(cmap.get("John"));
		
		cmap.remove("John", 29);
		System.out.println(cmap.get("John"));
	}
}
