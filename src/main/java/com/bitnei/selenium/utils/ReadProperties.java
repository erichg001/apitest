package com.bitnei.selenium.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import com.aventstack.extentreports.utils.Reader;

public class ReadProperties {
	private String filePath = "D:\\ LoginElement.properties";
	private Properties properties;
	
	/**
	 * 构造方法 创建对象时自动返回pro对象  在new该对象的时候会自动加载readProperties()方法
	 * @throws IOException 
	 * */
	
	public ReadProperties(String filePath) throws IOException{
		this.filePath = filePath;
		//在new该对象的时候会自动加载readProperties()方法
		this.properties = readProperties();
	}
	
	/**
	 * 返回已经加载properties文件的pro对象
	 * @throws IOException 
	 * */
	public Properties readProperties() throws IOException{
		//创建对象
		Properties pro = new Properties();
		FileInputStream input = null;
		BufferedInputStream in = null;
		//读取properties文件到缓存
		try {
			File file = new File(filePath);
			input = new FileInputStream(file);
			in = new BufferedInputStream(input);
			//加载缓存到pro对象 prop.load(in)这么写 不能读取properties配置文件中的中文
			InputStreamReader reader = new InputStreamReader(in, "utf-8");
			pro.load(reader);
		    //pro.load(new InputStreamReader(in, "utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			input.close();
			in.close();
		}
 
		//返回pro对象
		return pro;
	}
	
	/**
	 * 使用全局的properties对象获取key对应的value值
	 * @return 
	 * */
	public String getValue(String key){
		
		return properties.getProperty(key);
	}

}
