package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	private final static String DRIVER = "com.mysql.jdbc.Driver";
	private final static String URL = "jdbc:mysql://127.0.0.1/gameuser";
	private final static String NAME = "root";
	private final static String PASSWORD = "";
	public static Connection getConnection(){
		try {
			Class.forName(DRIVER);
			Connection con = DriverManager.getConnection(URL,NAME,PASSWORD);
			
			return con;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据库驱动不存在！");
            System.out.println(e.getMessage());
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据库连接不成功");
            System.out.println(e.getMessage());
		}
		return null;
	}
	
	public static void closeConnection(Connection con){
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void closeStatement(ResultSet res,Statement stat){
		try {
			if (res != null) {
				res.close();
			}
			stat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
