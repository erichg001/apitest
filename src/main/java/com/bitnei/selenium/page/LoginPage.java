package com.bitnei.selenium.page;

import java.io.IOException;

import org.openqa.selenium.WebElement;

import com.bitnei.selenium.base.BasePage;
import com.bitnei.selenium.base.DriverBase;

public class LoginPage extends BasePage{
	
	public LoginPage(DriverBase driver) {
		super(driver);
	}
	
	/**
	 * 获取登录按钮的element 
	 * super类BasePage有封装一个element方法 此处element可以直接使用
	 * @throws IOException 
	 * */
	public WebElement findLoginButtion() throws IOException{
		//return super.element(ByMethon.bystr("userCountBox"));
		return element(ByMethond.bystr("userCountBox"));
	}
	
	/**
	 * 获取"账号登录"的位置的element
	 * @throws IOException 
	 * */
	public WebElement checkCountLogin() throws IOException{
		return element(ByMethond.bystr("countLogin"));
	}
	
	/**
	 * 获取登录框的element
	 * @throws IOException 
	 * */
	public WebElement loginBox() throws IOException{
		return element(ByMethond.bystr("loginUser"));
	}
	
	/**
	 * 获取密码框的element
	 * @throws IOException 
	 * */
	public WebElement passwordElement() throws IOException{
		return element(ByMethond.bystr("loginPassword"));
	}
	
	/**
	 * 获取登录按钮的element
	 * @throws IOException 
	 * */
	public WebElement loginButtion() throws IOException{
		return element(ByMethond.bystr("loginButtion"));
	}

}
