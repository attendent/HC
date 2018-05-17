package com.huachen.util;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class JdbcUtils {
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/chatroomSystem";
	private static String user = "root";
	private static String password = "admin";

	// 加载数据库驱动
	static {
		try {
			Class.forName(driver);
			System.out.println("数据库驱动加载成功");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 连接数据库
	public static Connection getCon() throws SQLException {
		Connection con = null;
		con = (Connection) DriverManager.getConnection(url, user, password);
		System.out.println("数据库连接成功");
		return con;
	}

	// 关闭
	public void close(java.sql.PreparedStatement ps, java.sql.Connection con, java.sql.ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
