/**
 * 
 */
package com.justin.springboottest.java7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;

/**
 * @author EazyBytes
 *
 */
public class JDBCEnhancements {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		viewResults();
		createJdbcRowset();
	}

	/**
	 * Sample implementation from Java 7
	 * 
	 * @throws Exception
	 *
	 */
	public static void viewResults() throws Exception {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jpa?serverTimezone=Asia/Taipei&characterEncoding=utf-8", "root", "2315");
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select * from employee");) {
			while (rs.next()) {
				System.out.println(rs.getString("first_name") + " " + rs.getString("last_name") + " " + rs.getString("email"));
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Sample implementation from Java 7
	 * 
	 * @throws Exception
	 * 
	 */
	public static void createJdbcRowset() throws Exception {
		try (JdbcRowSet jRS = RowSetProvider.newFactory().createJdbcRowSet();) {
			jRS.setUrl("jdbc:mysql://localhost:3306/jpa?serverTimezone=Asia/Taipei&characterEncoding=utf-8");
			jRS.setUsername("root");
			jRS.setPassword("2315");
			jRS.setCommand("select * from employee");
			jRS.execute();
			System.out.println();
			while (jRS.next()) {
				System.out.println(jRS.getString("first_name") + " " + jRS.getString("last_name") + " " + jRS.getString("email"));
			}
		} catch (Exception e) {
			throw e;
		}
	}

}
