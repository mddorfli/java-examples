package omb.java.examples.io;

import java.io.Console;

public class ConsoleTest { 

	public static void main(String args[]) {
		Console console = System.console();
		char[] readPassword = console.readPassword("Enter your password %s: ", "please");
		System.out.printf("You entered \"%s\". ", String.valueOf(readPassword));
	}
}