package omb.java.examples.jdbc;

import java.sql.DriverManager;
import java.sql.SQLException;

public class PasswordSnifferTest {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
//		Class.forName("jdbc.PasswordSnifferJdbcDriver");
		DriverManager.getConnection("jdbc:sniffer", "username", "password");
	}
}
