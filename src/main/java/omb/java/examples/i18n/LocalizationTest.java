package omb.java.examples.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocalizationTest {

	public static void main(String[] args) {
		ResourceBundle bundle;
		
		bundle = ResourceBundle.getBundle("omb.java.examples.i18n.Localizations", Locale.ENGLISH);
		System.out.println(bundle.getObject("helloMatcher"));

		bundle = ResourceBundle.getBundle("omb.java.examples.i18n.Localizations", new Locale("hr", "HR"));
		System.out.println(bundle.getObject("helloMatcher"));
		
		bundle = ResourceBundle.getBundle("omb.java.examples.i18n.Lables", Locale.ENGLISH);
		System.out.println(bundle.getString("hello"));
		System.out.println(bundle.getString("bye"));
		System.out.println(bundle.getString("wassup"));
		
		bundle = ResourceBundle.getBundle("omb.java.examples.i18n.Lables", new Locale("hr", "HR"));
		System.out.println(bundle.getString("hello"));
		System.out.println(bundle.getString("bye"));
		System.out.println(bundle.getString("wassup"));
	}
}
