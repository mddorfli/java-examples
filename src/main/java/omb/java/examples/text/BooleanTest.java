package omb.java.examples.text;

public class BooleanTest {
	public static void main(String[] args) {
		System.out.println("Positives: ");
		testBoolean("true");
		testBoolean("t");
		testBoolean("1");
		testBoolean("tRuE");
		testBoolean("TRUE");
		testBoolean("yes");
		
		System.out.println("\nNegatives: ");
		testBoolean("false");
		testBoolean("no");
		testBoolean("fAlSe");
		testBoolean("NO");
		testBoolean("");
		testBoolean(null);
		
	}
	
	private static void testBoolean(String input) {
		System.out.printf("%10s -> %b\n", "\""+input+"\"", new Boolean(input));
	}
}
