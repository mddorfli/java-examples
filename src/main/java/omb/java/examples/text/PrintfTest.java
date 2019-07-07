package omb.java.examples.text;

public class PrintfTest {

	public static void main(String[] args) {
		System.out.print("\"");
		System.out.printf("%-5c", 'c');
		
		System.out.print("\"\n\"");
		System.out.printf("\"$%,.2f\"", -11234.2);
		
		System.out.print("\"\n\"");
		System.out.printf("%b", 100);
		
		System.out.print("\"\n\"");
		System.out.printf("\"%c\"", new Character('d'));
		
		System.out.print("\"\n\"");
		System.out.printf("%s", new Object());
		
		System.out.println("\"");
	}
	
}
