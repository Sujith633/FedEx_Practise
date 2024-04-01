package com.example.jsf.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {
	
	Connection cn = null;
	Statement st = null;
	ResultSet rs = null;
	
	public Connection getConnection() {
		Connection connect = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/fedex", "root", "root");
			connect.setAutoCommit(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connect;

	}
	public List<User> listUsers(){
		List<User> list = new ArrayList<>();
		cn = getConnection();
		try {
			String query = "select * from users";
			st = cn.createStatement();
			rs = st.executeQuery(query);
			while(rs.next()) {
				int users_id = rs.getInt("users_id");
				String username = rs.getString("username");
				String email = rs.getString("email");
				list.add(new User(users_id, username, email));				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				cn.close();
				st.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return list;
	}
	
	public void addUser(User user) {
		Connection cn = getConnection();
		PreparedStatement ps = null;
		String query = "insert into users (username, email) values (?,?)";
		try {
			ps = cn.prepareStatement(query);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.execute();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				cn.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	public User getUser(int users_id) {
		Connection cn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select * from users where users_id = ?";
		User user = null;
		
		try {
			ps = cn.prepareStatement(query);
			ps.setInt(1, users_id);
			rs = ps.executeQuery();
			if(rs.next()) {
				user = new User(rs.getInt("users_id"), 
						rs.getString("username"), 
						rs.getString("email"));
			}else {
				return null;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				cn.close();
				ps.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return user;
	}
	public void updateUser(User user) {
		Connection cn = getConnection();
		PreparedStatement ps = null;
		String query = "update users set username=?,email=? where users_id=?";
		try {
			ps = cn.prepareStatement(query);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setInt(3, user.getUsers_id());
			
			ps.execute();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				cn.close();
				ps.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	public void deleteUser(int users_id) {
		Connection cn = getConnection();
		PreparedStatement ps = null;
		String query = "delete from users where users_id =?";
		try {
			ps = cn.prepareStatement(query);
			ps.setInt(1, users_id);
			ps.execute();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				cn.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	}
}
