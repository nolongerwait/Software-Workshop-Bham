package application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.jdbc.DBConnection;
import application.model.User;

public class UserDAO {
	// Add User
	public int insert(User user) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// Get Connection
			con = DBConnection.getConnection();
			// Create Statement
			String sql = "insert into t_user(nickname,pwd,gender,birthday,email) values(?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getNickname());
			pstmt.setString(2, user.getPwd());
			pstmt.setString(3, user.getGender());
			pstmt.setDate(4, java.sql.Date.valueOf(user.getBirthday()));
			pstmt.setString(5, user.getEmail());
			// Execute SQL Statement
			int rows = pstmt.executeUpdate();
			return rows;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}finally{
			// Close Connection
			DBConnection.close(con, pstmt, null);
		}
	}
	
	// Search Users by Email and Password
	public User findBy(String email,String pwd) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		try {
			// Get Connection
			con = DBConnection.getConnection();
			// Create Statements
			String sql = "select nickname from t_user where email = ? and pwd = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, pwd);
			res = pstmt.executeQuery();
			
			User user = null;
			while(res.next()) {
				//System.out.println(res.getString("nickname") + "|");
				user = new User();
				user.setNickname(res.getString("nickname").trim());
				//System.out.println(user.getNickname() + "|");
			}
			
			return user;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			// Close Connection
			DBConnection.close(con, pstmt, res);
		}
	}
	
	// Search Users By Email
	public User findByName(String email) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		try {
			// Get Connection
			con = DBConnection.getConnection();
			// Create Statement
			String sql = "select * from t_user where email = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			res = pstmt.executeQuery();
			
			User user = null;
			while(res.next()) {
				user = new User();
				user.setEmail(res.getString("email"));
			}
			
			return user;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			// Close Connection
			DBConnection.close(con, pstmt, res);
		}
	}

	public boolean resetPassword(String Email, String OldPassword, String NewPassword) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// Get Connection
			con = DBConnection.getConnection();
			// Create Statement
			String sql = "update t_user set pwd = ? where email = ? and pwd = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, NewPassword);
			pstmt.setString(2, Email);
			pstmt.setString(3, OldPassword);
			int res = pstmt.executeUpdate();
			if(res > 0) {
				return true;
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally{
			// Close Connection
			DBConnection.close(con, pstmt, null);
		}
	}
		
}
