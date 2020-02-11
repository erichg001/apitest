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
* @version 创建时间：2020年2月11日 上午11:04:14 
* 类说明 
*/
public class GetInsurance {

	String host;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	//GetCookie getCookisaas = new GetCookie();
	GetApiToken getToken = new GetApiToken ();
	String token = "";
	ExcelReader ex ;
    ExcleUtil excleUtil;
    int status;
		
	@Parameters({"hostsaas","pathroad"})
	@BeforeClass
	public void setUp(String hostsaas,String pathroad) throws InterruptedException {
		host = hostsaas;	
		String ExcelFilePath= pathroad ;
        String sheetName="平安保险";
        ex = new ExcelReader(ExcelFilePath, sheetName);
		//设置cookie	
		try {
			 token = getToken.ApiToken();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace(); 
		}	
		       
	}
	
	@Test(description="openapi接口",priority =0,dataProvider="dp")
	public void Insurance(String desc,String url,String paras,String signature,String timestamp,String result) throws ClientProtocolException, IOException {
		restClient = new RestClient();
		DiffMethod diffMethod = new DiffMethod();
		//DiffMethodByExcept diffMethod = new DiffMethodByExcept();
		//准备请求头信息
		HashMap<String,String> headermap = new HashMap<String,String>();
		headermap.put("Content-Type", "application/json"); //这个在postman中可以查询到
		headermap.put("Authorization","bearer "+token);
		headermap.put("signature", signature);
		headermap.put("timestamp", timestamp);
		//入参设置
		url= host+url+paras;
		//调用接口	
		System.out.println("url----"+url);
		System.out.println("paras----"+paras);
		closeableHttpResponse = restClient.get(url, headermap);		
		HttpEntity entity = closeableHttpResponse.getEntity();
		String str = EntityUtils.toString(entity, "utf-8");
		//System.out.println("source-----"+str);
		//System.out.println("except-----"+result);
		//System.out.println("getStatusLine"+closeableHttpResponse.getStatusLine().getStatusCode());
//		status = closeableHttpResponse.getStatusLine().getStatusCode();
//		Assert.assertEquals(status, 200);
		JSONObject lastobject = new JSONObject();
		lastobject = diffMethod.diffFormatJson(JSONObject.fromObject(str),JSONObject.fromObject(result));
		System.out.println(lastobject.toString());
		Assert.assertEquals(lastobject.toString(), "{}");
//		JSONObject jsonobject = JSONObject.fromObject(str);		
//		System.out.println("jsonobject==========="+jsonobject);
//		String code =  jsonobject.get("code").toString();
//		System.out.println("code==========="+code);
//		Assert.assertEquals(code, result);
	}
	
	@DataProvider
    public Object[][] dp(){
     Object[][] sheetData = ex.getSheetDataByPD();
        return  sheetData ;
    }

}
