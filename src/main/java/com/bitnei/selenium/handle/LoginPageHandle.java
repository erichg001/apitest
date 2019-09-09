package com.bitnei.selenium.handle;

import java.io.IOException;

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
	 * @throws IOException 
	 * */
	public void findLoginButtion() throws IOException{
		loginpage.click(loginpage.findLoginButtion());
	}
	
	/**
	 * 封装点击账号登录的方法
	 * @throws IOException 
	 * */
	public void countLogin() throws IOException{
		loginpage.click(loginpage.checkCountLogin());;
	}
	
	/**
	 * 封装登录框的操作
	 * @throws IOException 
	 * */
	public void userLogin(String username) throws IOException{
		loginpage.sendKeys(loginpage.loginBox(), username);
	}
	
	/**
	 * 封装密码输入
	 * @throws IOException 
	 * */
	public void passwordLogin(String password) throws IOException{
		loginpage.sendKeys(loginpage.passwordElement(), password);
	}
	
	/**
	 * 封装点击登录的操作
	 * @throws IOException 
	 * */
	public void loginAction() throws IOException{
		loginpage.click(loginpage.loginButtion());
	}

}
