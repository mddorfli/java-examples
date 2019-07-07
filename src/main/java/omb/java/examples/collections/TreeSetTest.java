package omb.java.examples.collections;

import java.util.TreeSet;

public class TreeSetTest {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		TreeSet<Integer> s = new TreeSet<Integer>();
		TreeSet<Integer> subs = new TreeSet<Integer>();
		for (int i = 324; i <= 328; i++) {
			s.add(i);
		}
		System.out.println(s);
		subs = (TreeSet) s.subSet(326, true, 328, true);
		subs.add(329);
		System.out.println(s + " " + subs);
	}
}