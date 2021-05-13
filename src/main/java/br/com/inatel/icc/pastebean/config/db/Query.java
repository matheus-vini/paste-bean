package br.com.inatel.icc.pastebean.config.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Query {
	
	private static String query;
	private static Long userId = (long) 0;
	private static Long pasteId = (long) 0;
	
	public static Long highestUserId() {
		query = "SELECT MAX(id) as maxid FROM User";
		
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:h2:file:./src/main/resources/pastebeandb", "sa", "");
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery(query);
			while(rs.next()) {
				userId = rs.getLong("maxid");
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userId;
	}
	
	public static Long highestPasteId() {
		query = "SELECT MAX(id) as maxid FROM Paste";
		
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:h2:file:./src/main/resources/pastebeandb", "sa", "");
			Statement stat = con.createStatement();
			ResultSet rs = stat.executeQuery(query);
			while(rs.next()) {
				pasteId = rs.getLong("maxid");
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pasteId;
	}

}
