package omb.java.examples.io;

import java.io.PrintWriter;
import java.util.Date;

public class PrintWriterTest {

	public static void main(String[] args) {
		PrintWriter pw = new PrintWriter(System.out);
		pw.printf("%t", new Date());
	}
}
