package com.mybatis.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mybatis.entity.Role;


public class JdbcDemo {
	
	public static void main(String[] args) {
		JdbcDemo jdbc=new JdbcDemo();
		System.out.println(jdbc.getRole(1).toString());
	}
	private Connection getConnection(){
		Connection conn=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/mybatis?zeroDateTimeBehavior=convertToNull";
			String user="root";
			String password="123456";
			conn = DriverManager.getConnection(url, user, password);       
		}catch(Exception e){
			Logger.getLogger(JdbcDemo.class.getName()).log(Level.SEVERE, null, e);
			return null;
		}
		return conn;
	}
	public Role getRole(int id) {
		Connection connection = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement("select id,role_name,note from t_role where id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
//				Long roleId = rs.getLong("id"); //不知为何没有用上
				String userName = rs.getString("role_name");
				String note = rs.getString("note");
				Role role = new Role();
				role.setId(id);
				role.setRoleName(userName);
				role.setNote(note);
				return role;
			}
		} catch (SQLException ex) {
			Logger.getLogger(JdbcDemo.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			this.close(rs, ps, connection);
		}
		return null;
	}

	private void close(ResultSet rs, Statement stmt, Connection connection) {
		try {
			if (rs != null && !rs.isClosed())
				rs.close();
		} catch (SQLException ex) {
			Logger.getLogger(JdbcDemo.class.getName()).log(Level.SEVERE, null, ex);
		}
		try {
			if (stmt != null && !stmt.isClosed())
				stmt.close();
		} catch (SQLException ex) {
			Logger.getLogger(JdbcDemo.class.getName()).log(Level.SEVERE, null, ex);
		}
		try {
			if (connection != null && !connection.isClosed())
				connection.close();
		} catch (SQLException ex) {
			Logger.getLogger(JdbcDemo.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
