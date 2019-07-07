package omb.java.examples.text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {
	public static void main(String[] args) {
		System.out.println(Arrays.asList("name:john;;;sex:m;;;".split(";", -1)));
		{
			Pattern pattern = Pattern.compile("\\d*");
			Matcher matcher = pattern.matcher("ab34def");
			while (matcher.find()) {
				System.out.print(matcher.start() + matcher.group() + "-");
			}
			System.out.println();
		}

		{
			List<Integer> matches = new ArrayList<>();
			List<String> groups = new ArrayList<>();
			Pattern pattern = Pattern.compile("\\B");
//			Matcher matcher = pattern.matcher("^# ?$ab_34-def");
			Matcher matcher = pattern.matcher("^23 *$76 bc");
			while (matcher.find()) {
				System.out.print(matcher.start() + " ");
			}
			System.out.println();

			while(true) break;
			for(;;) break;
		}
	}
}
