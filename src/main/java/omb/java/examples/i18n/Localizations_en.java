package omb.java.examples.i18n;


import java.util.ListResourceBundle;
import java.util.regex.Pattern;

public class Localizations_en extends ListResourceBundle {

	@Override
	protected Object[][] getContents() {
		System.out.println("en contents loading...");
		return new Object[][]{{"helloMatcher", Pattern.compile(".*(hi|hello|howdy).*")}};
	}
}
