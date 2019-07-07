//in file B.java 
package omb.java.examples.oo.p2;

import omb.java.examples.oo.p1.A;

public class B extends omb.java.examples.oo.p1.A {
	public void process(A a) {
//		a.i = a.i * 2; // Does not compile
	}

	public static void main(String[] args) {
		A a = new B();
		B b = new B();
		b.process(a);
		System.out.println(a.getI());
	}
}