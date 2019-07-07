package omb.java.examples.oo;

public class TestClass {
	static int si = 10;
	int ii = 20;

	public void inner() {
		int ai = 30; // automatic variable
		final int fai = 40; // automatic final variable
		class Inner {
			public Inner() {
				System.out.println(si + "  " + ii + "   " + fai);
				// System.out.println(ai);
			}
		}
		new Inner();
	}

	public static void main(String[] args) {
		new TestClass().inner();
	}
}