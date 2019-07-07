package omb.java.examples.oo;

import java.io.IOException;

interface I1 {
	void m1() throws java.io.IOException;
}

interface I2 {
	void m1() throws java.sql.SQLException;
}

public class InheritanceTest implements I1, I2 {

	@Override
	public void m1() {
		System.out.println("m1 called");
	}

	public static void main(String[] args) throws IOException {
		InheritanceTest t = new InheritanceTest();
		t.m1();
	}
}
