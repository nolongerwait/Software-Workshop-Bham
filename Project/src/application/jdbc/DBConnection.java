package application.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DBConnection class defines the connection to Database
 * @author Zetian Qin zxq876
 */
public class DBConnection {
	private static String url = "jdbc:postgresql://mod-msc-sw1.cs.bham.ac.uk/";
	private static String user = "sabarmati";
	private static String pwd = "k9eo4twn09";
	static{
		try {
			Class.forName("org.postgresql.Driver");	
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * The method gets Connection Object
	 * @return Connection Object
	 */
	public static Connection getConnection(){
		try {
			return DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * The method closes the Connection Object & Statement Object & ResultSet Object
	 * @param connection the Object which Database connect to
	 * @param stmt Statement Object
	 * @param rs ResultSet Object
	 */
	public static void close(Connection connection,Statement stmt,ResultSet rs){
		if (rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * The method closes the Connection Object & Statement Object
	 * @param connection Database Object
	 * @param stmt Statement Object
	 */
	public static void close(Connection connection,Statement stmt){
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * The method closes the ResultSet Object
	 * @param rs ResultSet Object
	 */
	public static void close(ResultSet rs){
		if (rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

