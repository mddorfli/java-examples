package omb.java.examples.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

public class PasswordSnifferJdbcDriver implements Driver  {

	static {
		PasswordSnifferJdbcDriver sniffer = new PasswordSnifferJdbcDriver();
		try {
			DriverManager.registerDriver(sniffer);
			System.out.println("PasswordSnifferJdbcDriver initialised...");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean acceptsURL(String url) throws SQLException {
		System.out.println("PasswordSnifferJdbcDriver intercepted URL: "+url);
		return false;
	}

	@Override
	public Connection connect(String url, Properties info) throws SQLException {
		System.out.println("PasswordSnifferJdbcDriver intercepted URL: "+url+" with info "+info);
		return null;
	}

	@Override
	public int getMajorVersion() {
		return 0;
	}

	@Override
	public int getMinorVersion() {
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return null;
	}

	@Override
	public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
		return null;
	}

	@Override
	public boolean jdbcCompliant() {
		return false;
	}
	
}
