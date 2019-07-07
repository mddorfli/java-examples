package omb.java.examples.collections;

import java.util.Arrays;

public class BinarySearchTest {

	private static char[] hello = {'h', 'e', 'l', 'l', 'o'};
	public static void main(String[] args) {
		Arrays.sort(hello);
		System.out.println("Sorted letters: "+String.valueOf(hello));
		for (char c : hello) {
			System.out.printf("binarySearch(%c) = %d\n", c, Arrays.binarySearch(hello, c));
		}
		System.out.println();
		char[] world ={'w', 'o', 'r', 'l', 'd'};
		for (char c : world) {
			System.out.printf("binarySearch(%c) = %d\n", c, Arrays.binarySearch(hello, c));
		}
	}
}
