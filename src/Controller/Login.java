package Controller;

import java.util.Scanner;

import dao.UserDaoImplements;
import user.UserInfo;


public class Login {
	
	
	public void login(){
		UserInfo user = new UserInfo();
		System.out.println("欢迎登录");
		System.out.println("请输入用户名:");
		Scanner input = new Scanner(System.in);
		user.setUserName(input.nextLine());
		
		System.out.println("请输入密码：");
		String passwordTest = input.nextLine();
		(new UserDaoImplements()).loginTest(user);
		if (passwordTest.equals(user.getUserPassword())) {
			
			System.out.println("验证通过，请�?");
		}
		else {
			System.out.println(user.getUserName());
			System.out.println(user.getUserPassword());
			System.out.println("密码错误");
		}
	}
	
	public int login(String name,String password){
		int flag;
		UserInfo user = new UserInfo();
		
		user.setUserName(name);
		
		
		(new UserDaoImplements()).loginTest(user);
		if (password.equals(user.getUserPassword())) {
			
			flag =1;
		}
		else {
			flag =0;
		}
		return flag;
	}
}
