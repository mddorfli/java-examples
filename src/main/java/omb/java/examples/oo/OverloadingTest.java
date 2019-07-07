package omb.java.examples.oo;

public class OverloadingTest {
	static void print(Object o) {
		System.out.println("Object");
	}
	
	static void print(Integer i) {
		System.out.println("Integer");
	}
	
	static void print(int... ints) {
		System.out.println("ints...");
	}
	
//	static void print(Integer... obj) {
//		System.out.println("Integers...");
//	}
	
	public static void main(String[] args) {
		System.out.print("int -> "); 
		print(1);
		
		System.out.print("Integer -> "); 
		print(new Integer(1));
		
		System.out.print("int[] -> "); 
		print(new int[3]);
		
		System.out.print("int... -> "); 
		print(1,2,3,4);
		
		System.out.print("Integer[] -> "); 
		print(new Integer[2]);
		
		System.out.print("Integer... -> "); 
		print(new Integer(1), new Integer(2));
	}
}
