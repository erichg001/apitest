package com.bitnei.selenium.page;

import java.io.IOException;

import org.openqa.selenium.By;

import com.bitnei.selenium.utils.ReadProperties;

public class ByMethond {
public static By bystr(String PropertiesKey) throws IOException{
		
		String PropertiesPath = "LoginElement.properties";
		//创建ReadProperties对象
		ReadProperties properties = new ReadProperties(PropertiesPath);
		
		String value = properties.getValue(PropertiesKey);
		//对value进行拆分
		String LocateMethon = value.split(">")[0];
		String LocateEle = value.split(">")[1];
		
		//System.out.println(LocateMethon+"========="+LocateEle);
		
		if(LocateMethon.equalsIgnoreCase("id")){
			
			return By.id(LocateEle);
		}else if(LocateMethon.equalsIgnoreCase("name")){
			
			return By.name(LocateEle);
		}else if(LocateMethon.equalsIgnoreCase("tagNmae")){
			
			return By.tagName(LocateEle);
		}else if(LocateMethon.equalsIgnoreCase("linkText")){
			
			return By.linkText(LocateEle);
		}else if(LocateMethon.equalsIgnoreCase("xpath")){
			
			return By.xpath(LocateEle);
		}else if(LocateMethon.equalsIgnoreCase("cssSelector")){
			
			return By.cssSelector(LocateEle);
		}else{
			
			return By.partialLinkText(LocateEle);
		}
		
	}

}
