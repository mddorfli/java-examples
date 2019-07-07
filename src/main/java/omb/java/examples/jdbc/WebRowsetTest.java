package omb.java.examples.jdbc;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.WebRowSet;

public class WebRowsetTest {
	public static void main(String[] args) throws SQLException, IOException {
		JdbcTest.prepCustomerTable();
		
		try (WebRowSet wrs = RowSetProvider.newFactory().createWebRowSet()) {
			wrs.setUrl("jdbc:postgresql://localhost:5432/test");
			wrs.setUsername("test");
			wrs.setPassword("test");
			wrs.setCommand("select * from customer");
			wrs.execute();
			
			wrs.writeXml(System.out);
		}
	}
}
