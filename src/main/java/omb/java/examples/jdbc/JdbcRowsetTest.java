package omb.java.examples.jdbc;

import java.sql.Date;
import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class JdbcRowsetTest {
	public static void main(String[] args) throws SQLException {
//		JdbcTest.prepCustomerTable();
		
		RowSetFactory rsf = RowSetProvider.newFactory();
		try (JdbcRowSet rowSet = rsf.createJdbcRowSet()) {
			rowSet.setCommand("select * from customer");
			rowSet.setUrl("jdbc:postgresql://localhost:5432/test");
			rowSet.setUsername("test");
			rowSet.setPassword("test");
			rowSet.execute();
			
			rowSet.first();
			printRow(rowSet);
			int rank = rowSet.getInt("ranking");
			System.out.println("ranking upgrade! setting to "+(rank+1));
			rowSet.updateInt("ranking", rank+1);
			rowSet.updateRow();
			printRow(rowSet);
			
			rowSet.last();
			rowSet.deleteRow();
			
			rowSet.moveToInsertRow();
			rowSet.updateString("name", "harry");
			rowSet.updateString("email", "harry@microsoft.com");
			rowSet.insertRow();
			rowSet.moveToCurrentRow();
		}
	}
	
	private static void printRow(JdbcRowSet rowSet) throws SQLException {
		String name = rowSet.getString("name");
		String email = rowSet.getString("email");
		Date created = rowSet.getDate("dob");
		Integer ranking = rowSet.getInt("ranking");
		System.out.printf("user %1$s (email = %2$s, ranking %4$d) was born on %3$tA %3$tB %3$te, %3$tY\n", name, email, created, ranking);
	}
}
