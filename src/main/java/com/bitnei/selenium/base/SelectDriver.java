package com.bitnei.selenium.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SelectDriver {
	//这里只默认firefox 和 chrome两种浏览器
		public WebDriver driverName(String browersName){
			
			if(browersName.equalsIgnoreCase("firefox")){
				
				return new FirefoxDriver();
			}else{
				
				return new ChromeDriver();
			}
		}

}
