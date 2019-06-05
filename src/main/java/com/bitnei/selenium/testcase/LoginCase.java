package com.bitnei.selenium.testcase;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.bitnei.selenium.base.DriverBase;
import com.bitnei.selenium.busession.LoginBuss;
import com.bitnei.selenium.utils.ReadProperties;

public class LoginCase extends CaseBase{
	private DriverBase driver;
	private LoginBuss loginBuss;
	
	
	
	@AfterMethod
	public void afterTest() {
	     //关闭并退出浏览器  
	     driver.quit();  
	}
	
	/**
	 * 构造方法初始化loginBuss
	 * */
	public LoginCase(){
		this.driver = initDriver("chrome");
		loginBuss = new LoginBuss(driver);
	}
	
	@Test
	public void LoginTest() throws Exception{
		//读取配置文件
		ReadProperties readProperties = new ReadProperties("D:\\LoginElement.properties");		
		//这个driver来自于DriverBase  DriverBase类下封装了gerUrl方法
		String URL = readProperties.getValue("URL");
		driver.getUrl(URL);
		Thread.sleep(3000);
		//获取username 和 password
		String username = readProperties.getValue("LoginInfo").split(">")[0];
		String password = readProperties.getValue("LoginInfo").split(">")[1];
		
		loginBuss.bussLogin(username, password);
	}

}
