package com.bitnei.selenium.handle;

import com.bitnei.selenium.base.DriverBase;
import com.bitnei.selenium.page.LoginPage;

public class LoginPageHandle {
	private DriverBase driver;
	private LoginPage loginpage;
	
	/**
	 * 构造方法
	 * */
	public LoginPageHandle(DriverBase driver){
		this.driver = driver;
		//loginpage放在这里的目的是使用driver,保证driver的一致性
		loginpage = new LoginPage(driver);
	}
	
	/**
	 * 封装寻找登录框按钮的操作
	 * */
	public void findLoginButtion(){
		loginpage.click(loginpage.findLoginButtion());
	}
	
	/**
	 * 封装点击账号登录的方法
	 * */
	public void countLogin(){
		loginpage.click(loginpage.checkCountLogin());;
	}
	
	/**
	 * 封装登录框的操作
	 * */
	public void userLogin(String username){
		loginpage.sendKeys(loginpage.loginBox(), username);
	}
	
	/**
	 * 封装密码输入
	 * */
	public void passwordLogin(String password){
		loginpage.sendKeys(loginpage.passwordElement(), password);
	}
	
	/**
	 * 封装点击登录的操作
	 * */
	public void loginAction(){
		loginpage.click(loginpage.loginButtion());
	}

}
