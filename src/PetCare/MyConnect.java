package PetCare;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class MyConnect {
	private static Connection connection = null;

	public static Connection getConnection() {

		if (connection == null) {
			try {
				Class.forName(com.mysql.cj.jdbc.Driver.class.getName());
				connection = DriverManager.getConnection("jdbc:mysql://localhost/stoupetshop?useSSL=false", "root",
						"12345678");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}

	public static String login_Customer(String username, String password) {
		getConnection();
		String sql = "SELECT * FROM `customer` WHERE `CS_MAIL` = ? AND `CARD_NUM` = ?";
		//boolean result = false;
		PreparedStatement pre;
		try {
			pre = connection.prepareStatement(sql);
			pre.setString(1, username);
			pre.setString(2, password);
			ResultSet rec = pre.executeQuery();
			if (rec.next()) {
				return rec.getString("CS_NAME");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
		return "";
	}

	public static String login_Employee(String username, String password) {
		getConnection();
		String sql = "SELECT * FROM `employee` WHERE `EM_USER` = ? AND `EM_PASSWORD` = ?";
		//boolean result = false;
		PreparedStatement pre;
		try {
			pre = connection.prepareStatement(sql);
			pre.setString(1, username);
			pre.setString(2, password);
			ResultSet rec = pre.executeQuery();
			if (rec.next()) {
				return rec.getString("EM_NAME");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
		return "";
	}

}