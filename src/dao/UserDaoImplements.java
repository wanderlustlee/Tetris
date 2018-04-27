package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import Controller.*;
import util.DBUtil;
import user.*;
public class UserDaoImplements implements UserDao{

	

	public int registerTest(UserInfo user) {
		// TODO Auto-generated method stub
		int flag = 0;
		Connection con = DBUtil.getConnection();
		String sql = "insert into gameuser(name,password) values(?,?)";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, user.getUserName());
			pst.setString(2, user.getUserPassword());
			pst.executeUpdate();
			System.out.println("注册成功");
			flag = 1;
			return flag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return flag;
			
		}
		
	}

	@Override
	public void loginTest(UserInfo user) {
		// TODO Auto-generated method stub
		
		Connection con = DBUtil.getConnection();
		String sql = "select * from gameuser where name = ?";
		 
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, user.getUserName());
			//System.out.println(user.getUserName());
			ResultSet res = pst.executeQuery();
			while (res.next()) {
				//user.setUserName(res.getString("name"));
				//System.out.println(res.getString("name"));
				//System.out.println(user.getUserName());
				user.setUserPassword(res.getString("password"));
				//System.out.println(res.getString("password"));
				//System.out.println(user.getUserPassword());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//System.out.println(e.getMessage());
			System.out.println("查询失败");
		}
		
		
		
	}

	@Override
	public void writeScore(UserInfo user) {
		// TODO Auto-generated method stub
		Connection con = DBUtil.getConnection();
		String sql = "update gameuser set score = ? where name = ?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(2, user.getUserName());
			pst.setInt(1, user.getScore());
			System.out.println(pst.executeUpdate());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("更新失败");
		}
	}

	
	
	
}
