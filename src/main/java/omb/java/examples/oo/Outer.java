package omb.java.examples.oo;

public class Outer {

	int d;

	private class Inner1 {
		public void m1() {
			d = 5;
			// this.d = 5;
			Outer.this.d = 5;
		}
	}

	final class Inner2 {
		public Inner2() {
		}

		public void m1() {
			d = 5;
		}
	}

	abstract class Inner3 {
		public void main() {
			d = 5;
		}
	}

	public static void main(String[] args) {
		Inner2 second = new Outer().new Inner2();
		second.m1();
	}
}
