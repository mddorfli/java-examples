package omb.java.examples.jdbc.dblock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Acquires a row-level read-lock, which is then left to time out after 3
 * seconds (works on a postgresql db).
 * 
 * @author mddorfli
 */
public class DBLockTest {

	public static void main(String[] args) {
		try (Connection conn = createConnection()) {
			try (Statement s = createStatement(conn)) {
				ResultSet rs = doLockingQuery(s);

				try (Connection conn2 = createConnection()) {
					try (Statement s2 = createStatement(conn2)) {
						doUpdate(s2);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

				printResults(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static Statement createStatement(Connection conn) throws SQLException {
		// return conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,
		// ResultSet.CONCUR_UPDATABLE,
		// ResultSet.CLOSE_CURSORS_AT_COMMIT);
		return conn.createStatement();
	}

	private static Connection createConnection() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mydb", "mydb", "mydb");
		// conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
		conn.setAutoCommit(false);
		try (Statement stmt = conn.createStatement()) {
			stmt.execute("SET LOCAL lock_timeout TO 3000");
		}
		return conn;
	}

	private static void printResults(ResultSet rs) throws SQLException {
		while (rs.next()) {
			System.out.println("Found user id " + rs.getInt(1) + " called " + rs.getString(2) + " " + rs.getString(3));
		}
		System.out.println();
	}

	private static ResultSet doLockingQuery(Statement s) throws SQLException {
		ResultSet rs = s.executeQuery("select id, firstname, lastname from person where id = 8 FOR UPDATE");
		System.out.println("Selected user with id 8 (row locked)");
		return rs;
	}

	private static int doUpdate(Statement s2) throws SQLException {
		int result = s2.executeUpdate("update person set firstname = 'Someone', lastname = 'Else' where id = 8");
		System.out.println("Updated user with id 8");
		return result;
	}
}
