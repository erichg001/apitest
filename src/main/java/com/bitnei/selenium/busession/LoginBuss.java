package com.bitnei.selenium.busession;

import com.bitnei.selenium.base.DriverBase;
import com.bitnei.selenium.handle.LoginPageHandle;

public class LoginBuss {
private LoginPageHandle loginPgeHandle;
	
	/**
	 * 构造方法 实例化loginPageHandle
	 * */
	public LoginBuss(DriverBase driver){
		loginPgeHandle = new LoginPageHandle(driver);
	}
	
	/**
	 * 执行操作业务
	 * */
	public void bussLogin(String username,String password){
		loginPgeHandle.findLoginButtion();
		loginPgeHandle.countLogin();
		loginPgeHandle.userLogin(username);
		loginPgeHandle.passwordLogin(password);
		loginPgeHandle.loginAction();
	}

}
