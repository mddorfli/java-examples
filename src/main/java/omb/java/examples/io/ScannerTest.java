package omb.java.examples.io;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ScannerTest {

	public static void main(String[] args) {
		Scanner s = new Scanner("1 2 3 n 45 67 0.5 abcd hello");
		try {
			while (s.hasNext()) {
				System.out.println(s.nextInt());
			}
		} catch (InputMismatchException e) {
			e.printStackTrace();
		}
		
		s = new Scanner("1 2 3 n 45 67 0.5 abcd hello");
		while(s.hasNext()) {
			if (s.hasNextInt()) {
				System.out.println("int "+s.nextInt());
			} else if (s.hasNextDouble()) {
				System.out.println("double "+s.nextDouble());
			} else {
				System.out.println("string "+s.next());
			}
		}
	}
	
}
