package com.File.dao;

import java.sql.*;

public class Dao {
	private static Connection con;

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName(DBIntializer.DRIVER);
			con = DriverManager.getConnection(DBIntializer.CON_STRING, DBIntializer.USERNAME, DBIntializer.PASSWORD);
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}

	public static int save(String User, String Path) {
		int status = 0;
		try {
			Connection con = Dao.getConnection();
			PreparedStatement ps = con.prepareStatement("insert into Image(User,Path) values (?,?)");
			ps.setString(1, User);
			ps.setString(2, Path);

			status = ps.executeUpdate();

			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;
	}

	public static String getImageByUser(String User) {
		String path = "";
		try {
			Connection con = Dao.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from Image where User=?");
			ps.setString(1, User);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				path = rs.getString("Path");
			}
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return path;
	}

}
