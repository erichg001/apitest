package com.bitnei.selenium.testcase;

import com.bitnei.selenium.base.DriverBase;

public class CaseBase {
	/**
	 * 初始化driver
	 * */
	public DriverBase initDriver(String browers){
		DriverBase driver = new DriverBase(browers);
		return driver;
	}

}
