package com.bitnei.apitest.testcases.batteryanalysis;

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

import com.bitnei.apitest.testcases.saas.GetCookie;
import com.bitnei.apitest.testcases.saas.SaasGetSec;
import com.bitnei.apitest.tool.DiffMethod;
import com.bitnei.apitest.utils.ExcelReader;
import com.bitnei.apitest.utils.ExcleUtil;
import com.bitnei.apitest.utils.RestClient;

import net.sf.json.JSONObject;

/** 
* @author 作者 hangang
* @version 创建时间：2020年9月2日 下午5:14:48 
* 类说明 
*/
public class BatteryAnalysis {
	
	String host;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	GetCookie getCookisaas = new GetCookie();
	String[] authorization = null;
	ExcelReader ex ;
    ExcleUtil excleUtil;
	SaasGetSec saasGetSec = new SaasGetSec();
		
	@Parameters({"hostsaas","pathroad"})
	@BeforeClass
	public void setUp(String hostsaas,String pathroad) throws IOException, InterruptedException {
		host = hostsaas;	
		String ExcelFilePath= pathroad ;
        String sheetName="电池数据分析平台";
        ex = new ExcelReader(ExcelFilePath, sheetName);
		authorization = saasGetSec.GetSec();	
		       
	}
	
	@Test(description="电池数据分析平台",priority =0,dataProvider="dp")
	public void BatteryAnalysisData(String desc,String url,String paras,String result) throws ClientProtocolException, IOException {
		restClient = new RestClient();
		DiffMethod diffMethod = new DiffMethod();
		//准备请求头信息
		HashMap<String,String> headermap = new HashMap<String,String>();
		headermap.put("Content-Type", "application/json"); //这个在postman中可以查询到
		headermap.put("pub-sec",authorization[1]);
		headermap.put("sg-sec",authorization[2]);
		headermap.put("to-sec",authorization[0]);
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
