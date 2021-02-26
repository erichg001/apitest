package com.bitnei.selenium.testcase;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bitnei.apitest.utils.ExcelReader;
import com.bitnei.selenium.utils.WebDriverUtil;

//import junit.framework.Assert;

public class UserRole extends CaseBase{
	
	String host;
	ExcelReader ex ;
    WebDriverUtil webdriverutil;
    boolean res;
	
	@Parameters({"hostsaas","pathsaasuser"})
	@BeforeClass
	public void setUp(String hostsaas,String pathsaasuser) {
		host= hostsaas;
		String ExcelFilePath = pathsaasuser;
        String sheetName="userrole";     
        ex = new ExcelReader(ExcelFilePath, sheetName);
	}
	

	@AfterClass
	public void afterTest() {
	     //关闭并退出浏览器  
	     webdriverutil.closeAllBrowser();
	}	
	
	@Test(description="用户登录点击用户管理",dataProvider="dp")
	public void User(String desc,String url,String paras,String xpath,String result) throws InterruptedException {
		webdriverutil = new WebDriverUtil();
		webdriverutil.openBrowser(host+url, "chrome");
		webdriverutil.maxBrowser();
		webdriverutil.findElementByXpathAndClearSendkeys("/html/body/app-root/layout-passport/passport-login/div/div/div/form/nz-form-item[1]/nz-form-control/div/span/nz-input-group/input", paras.split(">>")[0]);
		webdriverutil.findElementByXpathAndClearSendkeys("/html/body/app-root/layout-passport/passport-login/div/div/div/form/nz-form-item[2]/nz-form-control/div/span/nz-input-group/input", paras.split(">>")[1]);
	    Thread.sleep(1000);
	    webdriverutil.findElementByXpathAndClick("/html/body/app-root/layout-passport/passport-login/div/div/div/form/nz-form-item[4]/button");
	    webdriverutil.findElementByXpathAndClick("/html/body/app/changan-layout/div/header/nav/div[2]/div[3]");
	    webdriverutil.findElementByXpathAndClick("/html/body/app/changan-layout/div/header/nav/div[2]/div[3]/div/ul/li[3]/a/div[1]");
	    res = webdriverutil.findElementByXpath("/html/body/app-root/app-sass/div/div/app-left-sass-menu/nz-layout/nz-sider/div/ul/li[1]/span/span").getText().trim().equals("用户管理");
	    try {
	    	if (res == false ) {
	    	webdriverutil.takeScreenshotByNow();
	    	Assert.fail("用户跳转失败");
	    	}else {
	    	assertTrue(res);
	    	}	    	
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    
//	    webdriverutil.findElementByXpathAndClick("/html/body/app-root/app-sass/div/div/app-left-sass-menu/div/div/div[2]/app-user-manager/nz-card/div/st/nz-table/div/nz-spin/div/div[2]/div/div/div/div/table/tbody/tr[1]/td[9]/span[2]/nz-switch/span");
//	    res = webdriverutil.findElementByXpath("/html/body/div[2]/div[2]/div/nz-message-container/div/nz-message/div/div/div/span").getText().equals("用户状态已更新！");
//	    try {
//	    	if (res == false ) {
//	    	webdriverutil.takeScreenshotByNow();
//	    	Assert.fail("用户状态已更新失败");
//	    	}else {
//	    	assertTrue(res);
//	    	}	    	
//	    }catch(Exception e){
//	    	e.printStackTrace();
//	    }
	}
	
	@DataProvider
    public Object[][] dp(){
     Object[][] sheetData = ex.getSheetDataByPD();
        return  sheetData ;
    }	

}
