package omb.java.examples.text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest {

	public static void main(String[] args) {

		Pattern x = Pattern.compile("aba");
		Matcher y = x.matcher("abababab");
		boolean b = false;
		while (b = y.find())
		{
			System.out.print(y.start() + " ");
		}
	}
}
