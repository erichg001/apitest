package com.bitnei.apitest.webtestcases.saas;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SaasLogin {
	
	private WebDriver driver; 
	private int delay = 10;
	private String userName = "SaaS@bitnei.cn";
	String pw = "abc123";
	String wrongpw = "123ab";
	WebDriverWait waitVar = null;
	String host;
	
	@Parameters({"hostsaas"})
	@BeforeMethod
	public void setUp(String hostsaas){
		host = hostsaas;
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
	    
		ChromeOptions options = new ChromeOptions();
	    DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(ChromeOptions.CAPABILITY, options);
        
//        LoggingPreferences logPrefs = new LoggingPreferences();
//        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
//        cap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        cap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        
        driver = new ChromeDriver(cap);
	    //可以采用隐式设置通用的等待时间
	    driver.manage().timeouts().implicitlyWait(delay, TimeUnit.SECONDS);
	    //也可以采用显式根据条件判断结果设置等待时间
	    waitVar = new WebDriverWait(driver, delay);  
	    driver.manage().window().maximize();
	}
	
	@AfterMethod
	public void afterTest() {
	     //关闭并退出浏览器  
	     driver.quit();  
	}
	
//	@Test(description="点击登录按钮跳转正确")
//	public void Login() throws InterruptedException{		
//		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);         
//		driver.get(host+"/home"); 
//		Thread.sleep(1000);
//		driver.findElement(By.xpath("/html/body/app-root/app-sass/div/header/nav/div[2]/div/a[4]")).click(); 
//		String get_title = driver.getCurrentUrl();
//		assertTrue(get_title.equals(host+"/passport/login"));          
//	}
//	
//	@Test(description="登录失败,请输入用户名")
//	public void LoginNoName() throws InterruptedException {
//		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
//		driver.get(host+"/passport/login");
//	    Thread.sleep(1000);
//	    driver.findElement(By.xpath("/html/body/app-root/layout-passport/passport-login/div/div/div/form/nz-form-item[4]/button")).click();
//	    //waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app/changan-layout/div/header/nav/div[1]/a[2]")));
//	    assertTrue(driver.findElement(By.xpath("/html/body/app-root/layout-passport/passport-login/div/div/div/form/nz-alert/div/span")).getText().equals("请输入用户名"));
//	}                                           
//	
//	@Test(description="登录失败,请输入密码")
//	public void LoginNoPassWord() throws InterruptedException {
//		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
//		driver.get(host+"/passport/login");
//	    driver.findElement(By.xpath("/html/body/app-root/layout-passport/div/div/passport-login/div/form/nz-form-item[1]/nz-form-control/div/span/nz-input-group/input")).sendKeys(userName);
//	    Thread.sleep(1000);
//	    driver.findElement(By.xpath("/html/body/app-root/layout-passport/div/div/passport-login/div/form/nz-form-item[4]/button")).click();
//	    //waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app/changan-layout/div/header/nav/div[1]/a[2]")));
//	    assertTrue(driver.findElement(By.xpath("/html/body/app-root/layout-passport/div/div/passport-login/div/form/nz-alert/div/span")).getText().equals("请输入密码"));
//	}
//	
//	
//	@Test(description="登录失败,用户名密码错误")
//	public void LoginFaild() throws InterruptedException {
//		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
//		driver.get(host+"/passport/login");
//	    driver.findElement(By.xpath("/html/body/app-root/layout-passport/div/div/passport-login/div/form/nz-form-item[1]/nz-form-control/div/span/nz-input-group/input")).sendKeys(userName);
//	    driver.findElement(By.xpath("/html/body/app-root/layout-passport/div/div/passport-login/div/form/nz-form-item[2]/nz-form-control/div/span/nz-input-group/input")).sendKeys(wrongpw);
//	    Thread.sleep(1000);
//	    driver.findElement(By.xpath("/html/body/app-root/layout-passport/div/div/passport-login/div/form/nz-form-item[4]/button")).click();
//	    //waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app/changan-layout/div/header/nav/div[1]/a[2]")));
//	    assertTrue(driver.findElement(By.xpath("/html/body/app-root/layout-passport/div/div/passport-login/div/form/nz-alert/div/span")).getText().equals("用户名或密码错误！"));
//	}
//	
	@Test(description="登录成功")
	public void LoginSucsess() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.get(host+"/passport/login");
//	    waitVar.until(ExpectedConditions.elementToBeClickable(By.xpath
//				("/html/body/app-root/layout-passport/div/div/passport-login/div/form/nz-form-item[4]/button")));
	    driver.findElement(By.xpath("/html/body/app-root/layout-passport/passport-login/div/div/div/form/nz-form-item[1]/nz-form-control/div/span/nz-input-group/input")).sendKeys(userName);
	    driver.findElement(By.xpath("/html/body/app-root/layout-passport/passport-login/div/div/div/form/nz-form-item[2]/nz-form-control/div/span/nz-input-group/input")).sendKeys(pw);
	    Thread.sleep(1000);
	    driver.findElement(By.xpath("/html/body/app-root/layout-passport/passport-login/div/div/div/form/nz-form-item[4]/button")).click();        

	    waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app/changan-layout/div/header/nav/div[1]/a[2]")));
	    assertTrue(driver.getTitle().equals("新能源汽车大数据平台"));
	    
        
        LogEntries logs = driver.manage().logs().get(LogType.PERFORMANCE);     	    
	    String tosec = "" ;
	    String pubsec = "";
	    String sgsec = "";     

        for (Iterator<LogEntry> it = logs.iterator(); it.hasNext();){
            LogEntry entry = it.next();
            try{
                JSONObject json = new JSONObject(entry.getMessage());
                JSONObject message = json.getJSONObject("message");
                String method = message.getString("method");                
                if (method != null && "Network.requestWillBeSent".equals(method)){
                    JSONObject params = message.getJSONObject("params");
                    JSONObject request = params.getJSONObject("request");                    
                    JSONObject headers = request.getJSONObject("headers");                   
                    if (headers.has("to-sec")) {
                    	tosec = headers.getString("to-sec");
                    }
                    if (headers.has("pub-sec")) {
                    	pubsec = headers.getString("pub-sec");
                    }
                    if (headers.has("sg-sec")) {
                        sgsec = headers.getString("sg-sec");
                        }
                }
            } catch (JSONException e){
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        System.out.println("to-sec=====: " + tosec);
        System.out.println("pub-sec=====: " + pubsec);
        System.out.println("pub-sec=====: " + sgsec);
    }


}
