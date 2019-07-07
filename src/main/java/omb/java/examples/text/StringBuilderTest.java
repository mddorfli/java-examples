package omb.java.examples.text;

import java.util.Date;

public class StringBuilderTest {

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder(8);
		System.out.println(sb.length());
		sb.append(new Date());
		System.out.println(sb.toString());

		sb.setLength(7);
		System.out.println(sb.toString());

		System.out.println("" + sb);
	}
}
