package com.my.tang.controller.list;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUtil {
	public static Connection getConnection() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/springbasic";
			String dbID = "root";
			String dbPassword = "root";
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(dbURL, dbID, dbPassword);
			con.setAutoCommit( false );
			return con;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public static void close(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(PreparedStatement pstmt) {
		try {
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs) {
		try {
			if(rs!=null) {
			rs.close();}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void commit(Connection con) {
		try {
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection con) {
		try {
			con.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
