package omb.java.examples.text;

import java.text.NumberFormat;
import java.text.ParseException;

public class NumberFormatTest {
	public static void main(String[] args) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		System.out.println(nf.format(1234.5678d));
		try {
			System.out.println(nf.parse("1234.5678"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
