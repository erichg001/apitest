package com.bitnei.selenium.testcase;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bitnei.apitest.utils.ExcelReader;
import com.bitnei.selenium.utils.WebDriverUtil;

/** 
* @author 作者 hangang
* @version 创建时间：2021年1月21日 下午4:54:27 
* 类说明 
*/
public class BackSaasLogin extends CaseBase{
	String host;
	ExcelReader ex ;
    WebDriverUtil webdriverutil;
    boolean res;
	
	@Parameters({"backsaas","pathsaasuser"})
	@BeforeClass
	public void setUp(String backsaas,String pathsaasuser) {
		host= backsaas;
		String ExcelFilePath = pathsaasuser;
        String sheetName="backsaas";
        ex = new ExcelReader(ExcelFilePath, sheetName);

	}
	

	@AfterMethod
	public void afterTest() {
	     //关闭并退出浏览器  
	     webdriverutil.closeAllBrowser();
	}	
	
	@DataProvider(name="dp")
    public Object[][] dp(){
     Object[][] sheetData = ex.getSheetDataByPD();
        return  sheetData ;
    }
	
	@Test(description="管理员登录",dataProvider="dp")
	public void LoginFaild(String desc,String url,String paras,String xpath,String result) throws InterruptedException {
		webdriverutil = new WebDriverUtil();
		webdriverutil.openBrowser(host+url, "chrome");
		webdriverutil.maxBrowser();
		webdriverutil.findElementByXpathAndClearSendkeys("/html/body/app-root/layout-passport/div/div/passport-login/div/form/nz-form-item[1]/nz-form-control/div/span/nz-input-group/input", paras.split(">>")[0]);
		webdriverutil.findElementByXpathAndClearSendkeys("/html/body/app-root/layout-passport/div/div/passport-login/div/form/nz-form-item[2]/nz-form-control/div/span/nz-input-group/input", paras.split(">>")[1]);
	    Thread.sleep(1000);
	    webdriverutil.findElementByXpathAndClick("/html/body/app-root/layout-passport/div/div/passport-login/div/form/nz-form-item[3]/button");
	    res = webdriverutil.findElementByXpath(xpath).getText().trim().equals(result);
	    try {
	    	if (res == false ) {
	    	webdriverutil.takeScreenshotByNow();
	    	Assert.fail("失败");
	    	}else { 
	    	Assert.assertTrue(res, "成功");
	    	}	    	
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    	    
	}

}
