package omb.java.examples.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class JdbcTest {

	private Connection conn;

	public JdbcTest(Connection connection) {
		this.conn = connection;
	}

	public void dropCustomerTable() throws SQLException {
		try (Statement stmt = conn.createStatement()) {
			stmt.executeUpdate("DROP TABLE CUSTOMER");
		}
	}

	public void printCustomerData() throws SQLException {
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT * FROM CUSTOMER");
			System.out.println("Database contains: ");
			printCustomer(rs);
		}
		System.out.println();
	}

	private void printCustomer(ResultSet rs) throws SQLException {
		boolean dataExists = false;
		while(rs.next()) {
			dataExists = true;
			System.out.printf("id: %d, name: %s, email: %s, dob: %tF, ranking %d \n"
					, rs.getInt("ID")
					, rs.getString("NAME")
					, rs.getString("EMAIL")
					, rs.getDate("DOB")
					, rs.getInt("RANKING")
					);
		}
		if (!dataExists) {
			System.out.println("No data");
		}
	}

	private void printCustomerInfo(String... names) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM CUSTOMER WHERE UPPER(NAME) = UPPER(?)")) {
			for (int i = 0; i < names.length; i++) {
				stmt.setString(1, names[i]);
				ResultSet rs = stmt.executeQuery();
				System.out.print("CUSTOMER "+names[i]+": ");
				printCustomer(rs);
			}
		}
		System.out.println();
	}
	
	public void createCustomerTable() throws SQLException {
		try (Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(""
					+ "CREATE TABLE CUSTOMER (" 
					+ "  ID SERIAL PRIMARY KEY," 
					+ "  NAME VARCHAR(80) NOT NULL, "
					+ "  EMAIL VARCHAR(80), " 
					+ "  DOB TIMESTAMP, " 
					+ "  RANKING INTEGER" 
					+ ")");
		}
	}

	public void insertCustomerData() throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO CUSTOMER (NAME, EMAIL, DOB, RANKING) VALUES (?, ?, ?, ?)");
				Scanner scanner = new Scanner(getClass().getResourceAsStream("customerdata.csv"));) {
			Pattern regex = Pattern.compile("(\\w*),([\\w\\.@]*),([\\d-]*),(\\d*)");
			while (scanner.hasNext(regex)) {
				scanner.next(regex);
				MatchResult match = scanner.match();
				String name = match.group(1);
				String email = match.group(2);
				String date = match.group(3);
				String ranking = match.group(4);
				
				System.out.println("INSERTING " + name + ", " + email + ", " + date + ", " + ranking);
				stmt.setString(1, name);
				stmt.setString(2, email);
				stmt.setDate(3, java.sql.Date.valueOf(date));
				stmt.setInt(4, Integer.valueOf(ranking));
				stmt.executeUpdate();
			}
		}
		System.out.println();
	}

	public void generateReport(String tableName) throws SQLException {
		System.out.println("GENERATING REPORT FOR "+tableName+": ");
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT * FROM "+tableName);
			ResultSetMetaData columns = rs.getMetaData();
			for (int i = 1; i < columns.getColumnCount()+1; i++) {
				System.out.printf("%-"+columns.getColumnDisplaySize(i)+"s", columns.getColumnLabel(i) + " ("+columns.getColumnTypeName(i)+")");
			}
			System.out.println();
			
			while (rs.next()) {
				ResultSetMetaData metaData = rs.getMetaData();
				for (int i = 1; i < metaData.getColumnCount()+1; i++) {
					System.out.printf("%-"+metaData.getColumnDisplaySize(i)+"s", rs.getObject(i));
				}
				System.out.println();
			}
		}
		System.out.println();
	}
	
	public static void prepCustomerTable() {
		try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test", "test", "test")){
			JdbcTest jdbcTest = new JdbcTest(conn);
			try {
				jdbcTest.dropCustomerTable();
			} catch (SQLException e) {
				System.err.println("Couldn't drop customer table");
			}
			jdbcTest.createCustomerTable();
			jdbcTest.insertCustomerData();
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
	}

	public static void main(String[] args) throws SQLException {
		Properties props = new Properties();
		props.setProperty("user", "test");
		props.setProperty("password", "test");

		try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test", props)) {
			JdbcTest me = new JdbcTest(conn);
			try {
				me.printCustomerData();
			} catch (SQLException e) {
				me.createCustomerTable();
				me.insertCustomerData();
				me.printCustomerData();
			}

			me.printCustomerInfo("bob", "fred", "jane");
			
			me.generateReport("CUSTOMER");
			me.dropCustomerTable();
		}
	}
}
