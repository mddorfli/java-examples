package omb.java.examples.i18n;

import java.util.ListResourceBundle;
import java.util.regex.Pattern;

public class Localizations_en_HR extends ListResourceBundle {

	@Override
	protected Object[][] getContents() {
		System.out.println("en_HR contents loading...");
		return new Object[][]{{"helloMatcher", Pattern.compile(".*(bok|ciao|hej).*")}};
	}

}
