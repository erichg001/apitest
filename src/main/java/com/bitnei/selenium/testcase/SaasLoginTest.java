package com.bitnei.selenium.testcase;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bitnei.apitest.utils.ExcelReader;
import com.bitnei.apitest.utils.ExcleUtil;
import com.bitnei.selenium.utils.WebDriverUtil;

public class SaasLoginTest extends CaseBase{
	String host;
	String url;
	String urlpara = "";
	String text;
	private WebDriver driver; 
	private int delay = 10;
	private String userName = "yaomeng@bitnei.cn";
    String pw = "123456a";
	String wrongpw = "123ab";
	WebDriverWait waitVar = null;
	ExcelReader ex ;
    private ExcleUtil excleUtil;
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
	
//	@Test(description="点击登录按钮跳转正确",dataProvider="dp")
//	public void Login(String desc,String url,String paras,String result){		
//		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);         
//		driver.get(host+url); 
//		driver.findElement(By.xpath("/html/body/app-root/app-sass/div/header/nav/div[2]/div/a[4]")).click();      
//		String get_title = driver.getCurrentUrl();
//		assertTrue(get_title.equals(result));          
//	}
//	
//	@Test(description="登录失败,请输入用户名",dataProvider="dp")
//	public void LoginNoName(String desc,String url,String paras,String result) throws InterruptedException {
//		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
//		driver.get(host+url);
//	    Thread.sleep(1000);
//	    driver.findElement(By.xpath("/html/body/app-root/layout-passport/div/div/passport-login/div/form/nz-form-item[4]/button")).click();
//	    //waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app/changan-layout/div/header/nav/div[1]/a[2]")));
//	    assertTrue(driver.findElement(By.xpath("/html/body/app-root/layout-passport/div/div/passport-login/div/form/nz-alert/div/span")).getText().equals(result));
//	}
	
	@Test(description="登录失败,用户名密码错误",dataProvider="dp")
	public void LoginFaild(String desc,String url,String paras,String xpath,String result) throws InterruptedException {
		webdriverutil = new WebDriverUtil();
		webdriverutil.openBrowser(host+url, "chrome");
		webdriverutil.maxBrowser();
		webdriverutil.findElementByXpathAndClearSendkeys("/html/body/app-root/layout-passport/div/div/passport-login/div/form/nz-form-item[1]/nz-form-control/div/span/nz-input-group/input", paras.split(">>")[0]);
		webdriverutil.findElementByXpathAndClearSendkeys("/html/body/app-root/layout-passport/div/div/passport-login/div/form/nz-form-item[2]/nz-form-control/div/span/nz-input-group/input", paras.split(">>")[1]);
	    Thread.sleep(1000);
	    webdriverutil.findElementByXpathAndClick("/html/body/app-root/layout-passport/div/div/passport-login/div/form/nz-form-item[4]/button");
	    res = webdriverutil.findElementByXpath(xpath).getText().equals(result);
	    if (res == false ) {
	    	try {
				webdriverutil.takeScreenshotByNow();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    assertTrue(res);
	}
	
	@DataProvider
    public Object[][] dp(){
     Object[][] sheetData = ex.getSheetDataByPD();
        return  sheetData ;
    }

}
