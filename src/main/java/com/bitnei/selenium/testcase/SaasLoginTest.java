package com.bitnei.selenium.testcase;

import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bitnei.apitest.utils.ExcelReader;
import com.bitnei.apitest.utils.ExcleUtil;
import com.bitnei.selenium.utils.WebDriverUtil;

public class SaasLoginTest extends CaseBase{
	String host;
	ExcelReader ex ;
    WebDriverUtil webdriverutil;
    boolean res;
	
	@Parameters({"hostsaas","pathsaasuser"})
	@BeforeClass
	public void setUp(String hostsaas,String pathsaasuser) {
		host= hostsaas;
		String ExcelFilePath = pathsaasuser;
        String sheetName="Sheet1";
        ex = new ExcelReader(ExcelFilePath, sheetName);

	}
//	@BeforeMethod
//	public void beforeTest() {
//		System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
//		driver = new ChromeDriver();
//	    //可以采用隐式设置通用的等待时间
//	    driver.manage().timeouts().implicitlyWait(delay, TimeUnit.SECONDS);
//	    //也可以采用显式根据条件判断结果设置等待时间
//	    waitVar = new WebDriverWait(driver, delay);  
//	    driver.manage().window().maximize();
//	}
	

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
	
	@Test(description="用户登录",dataProvider="dp")
	public void LoginFaild(String desc,String url,String paras,String xpath,String result) throws InterruptedException {
		webdriverutil = new WebDriverUtil();
		webdriverutil.openBrowser(host+url, "chrome");
		webdriverutil.maxBrowser();
		webdriverutil.findElementByXpathAndClearSendkeys("/html/body/app-root/layout-passport/passport-login/div/div/div/form/nz-form-item[1]/nz-form-control/div/span/nz-input-group/input", paras.split(">>")[0]);
		webdriverutil.findElementByXpathAndClearSendkeys("/html/body/app-root/layout-passport/passport-login/div/div/div/form/nz-form-item[2]/nz-form-control/div/span/nz-input-group/input", paras.split(">>")[1]);
	    Thread.sleep(1000);
	    webdriverutil.findElementByXpathAndClick("/html/body/app-root/layout-passport/passport-login/div/div/div/form/nz-form-item[4]/button");
	    res = webdriverutil.findElementByXpath(xpath).getText().equals(result);
	    try {
	    	if (res == false ) {
	    	webdriverutil.takeScreenshotByNow();
	    	Assert.fail("失败");
	    	//System.out.println("dp().toString().length()"+dp());
	    	//ExcleUtil.getCell(dp().length,2);
	    	//ExcleUtil.setCellData(1, 2, "失败");
	    	}else { 
	    	Assert.assertTrue(res, "成功");
	    	}	    	
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
//	    if (res == false ) {
//	    	try {
//				webdriverutil.takeScreenshotByNow();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//	    }
	    //assertTrue(res);
	}
	


}
