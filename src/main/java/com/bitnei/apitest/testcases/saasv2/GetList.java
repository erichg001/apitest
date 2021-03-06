package com.bitnei.apitest.testcases.saasv2;

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

import com.bitnei.apitest.testcases.saasv2.GetCookie;
import com.bitnei.apitest.tool.DiffMethod;
import com.bitnei.apitest.utils.ExcelReader;
import com.bitnei.apitest.utils.ExcleUtil;
import com.bitnei.apitest.utils.RestClient;

import net.sf.json.JSONObject;

/** 
* @author 作者 hangang
* @version 创建时间：2020年4月16日 上午11:16:27 
* 类说明 
*/
public class GetList {
	String host;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	String cookie;
	GetCookie getCookie = new GetCookie();
	ExcelReader ex ;
    ExcleUtil excleUtil;
		
	@Parameters({"saasv2","pathroad"})
	@BeforeClass
	public void setUp(String saasv2,String pathroad) throws  ClientProtocolException, IOException {
		host = saasv2;	
		String ExcelFilePath= pathroad ;
        String sheetName="saasv2查询get";
        ex = new ExcelReader(ExcelFilePath, sheetName);
        cookie = getCookie.login();
		//Thread.sleep(5000);
		       
	}
	
	@Test(description="saasv2平台get查询",priority =0,dataProvider="dp")
	public void GetSaasList(String desc,String url,String paras,String result) throws ClientProtocolException, IOException {
		restClient = new RestClient();
		DiffMethod diffMethod = new DiffMethod();
		
		//准备请求头信息
		HashMap<String,String> headermap = new HashMap<String,String>();
		headermap.put("Content-Type", "application/json"); //这个在postman中可以查询到
		headermap.put("Cookie", cookie);
		//入参设置
		url= host +url+paras;
		//调用接口	
		System.out.println("url----"+url);
		closeableHttpResponse = restClient.get(url, headermap);		
		HttpEntity entity = closeableHttpResponse.getEntity();
		String str = EntityUtils.toString(entity, "utf-8");
		System.out.println("source-----"+str);
		JSONObject lastobject = new JSONObject();
		lastobject = diffMethod.diffFormatJson(JSONObject.fromObject(str),JSONObject.fromObject(result));
		System.out.println(lastobject.toString());
		Assert.assertEquals(lastobject.toString(), "{}");
	}
	
	@DataProvider
    public Object[][] dp(){
     Object[][] sheetData = ex.getSheetDataByPD();
        return  sheetData ;
    }


}
