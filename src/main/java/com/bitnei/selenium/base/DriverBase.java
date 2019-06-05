package com.bitnei.selenium.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DriverBase {
	private WebDriver driver;
	
	/**
	 * 构造方法 创建对象时实例化driver
	 * */
	public DriverBase(String browersName){
		SelectDriver selectDriver = new SelectDriver();
		//将SelectDriver中的driver对象赋值给"private WebDriver driver"中的driver 这样driver对象就有值了！！
		this.driver = selectDriver.driverName(browersName);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	/**
	 * 封装Element方法
	 * */
	public WebElement element(By by){
		
		return driver.findElement(by);
	}
	
	/**
	 * 封装get方法
	 * */
	public void getUrl(String url){
		
		driver.get(url);
	}

	public void quit(){
		
		driver.quit();
	}
}
