package com.bitnei.selenium.page;

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
	 * */
	public WebElement findLoginButtion(){
		//return super.element(ByMethon.bystr("userCountBox"));
		return element(ByMethond.bystr("userCountBox"));
	}
	
	/**
	 * 获取"账号登录"的位置的element
	 * */
	public WebElement checkCountLogin(){
		return element(ByMethond.bystr("countLogin"));
	}
	
	/**
	 * 获取登录框的element
	 * */
	public WebElement loginBox(){
		return element(ByMethond.bystr("loginUser"));
	}
	
	/**
	 * 获取密码框的element
	 * */
	public WebElement passwordElement(){
		return element(ByMethond.bystr("loginPassword"));
	}
	
	/**
	 * 获取登录按钮的element
	 * */
	public WebElement loginButtion(){
		return element(ByMethond.bystr("loginButtion"));
	}

}
