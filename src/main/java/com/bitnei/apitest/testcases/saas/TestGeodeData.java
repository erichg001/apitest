package com.bitnei.apitest.testcases.saas;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bitnei.apitest.tool.DiffMethod;
import com.bitnei.apitest.utils.ExcelReader;
import com.bitnei.apitest.utils.ExcleUtil;
import com.bitnei.apitest.utils.RestClient;

import net.sf.json.JSONObject;

/** 
* @author 作者 hangang
* @version 创建时间：2020年1月9日 下午4:09:23 
* 类说明 
* http://10.10.21.70:7070/pulse/login.html
* admin/admin
* 验证geode中实时车辆位置和里程数据偏离正常范围
*/
public class TestGeodeData {
	
	String host;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	GetGeodeCookie getgeodecookie = new GetGeodeCookie();
	String authorization = "";
	ExcelReader ex ;
    ExcleUtil excleUtil;
		
	@Parameters({"hostgeode","pathroad"})
	@BeforeClass
	public void setUp(String hostsaas,String pathroad) {
		host = hostsaas;	
		String ExcelFilePath= pathroad ;
        String sheetName="geodedata";
        ex = new ExcelReader(ExcelFilePath, sheetName);
		//设置cookie		
		try {
			 authorization = getgeodecookie.login();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		       
	}
	
	@Test(description="geode数据验证",priority =0,dataProvider="dp")
	public void UserInfoRgisterData(String desc,String url,String paras,String result) throws ClientProtocolException, IOException {
		restClient = new RestClient();
		DiffMethod diffMethod = new DiffMethod();
		//准备请求头信息
		HashMap<String,String> headermap = new HashMap<String,String>();
		headermap.put("Content-Type", "application/json"); //这个在postman中可以查询到
		headermap.put("Cookie",authorization);
		//入参设置
		url= host +url+paras;
		System.out.println("URL------"+url);
		//调用接口	
		closeableHttpResponse = restClient.get(url, headermap);	
		
		HttpEntity entity = closeableHttpResponse.getEntity();
		String str = EntityUtils.toString(entity, "utf-8");
		System.out.println("str"+str);
		JSONObject lastobject = new JSONObject();
		lastobject = diffMethod.diffFormatJson(JSONObject.fromObject(str),JSONObject.fromObject(result));
		Assert.assertEquals(lastobject.toString(), "{}");
	}
	
	@DataProvider
    public Object[][] dp(){
     Object[][] sheetData = ex.getSheetDataByPD();
        return  sheetData ;
    }

}
