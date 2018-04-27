package Controller;

import dao.UserDaoImplements;
import user.UserInfo;

public class Register {
	
	
	public int register(String name,String password){
		UserInfo user = new UserInfo();
		
		user.setUserName(name);
		
		user.setUserPassword(password);
		int flag = (new UserDaoImplements()).registerTest(user);
		return flag;
	}
}
