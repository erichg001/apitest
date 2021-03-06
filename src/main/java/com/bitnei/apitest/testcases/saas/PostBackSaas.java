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
* @author 作者 eric_hg
* @version 创建时间：2019年9月20日 下午3:32:45 
* 类说明 
*/
public class PostBackSaas {
	
	String host;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	GetCookie getCookisaas = new GetCookie();
	String[] authorization = null;
	ExcelReader ex ;
    ExcleUtil excleUtil;
    SaasGetSec saasGetSec = new SaasGetSec();
		
	@Parameters({"backsaas","pathroad"})
	@BeforeClass
	public void setUp(String backsaas,String pathroad) throws InterruptedException {
		host = backsaas;
		String ExcelFilePath= pathroad ;
        String sheetName="backsaas";
        ex = new ExcelReader(ExcelFilePath, sheetName);
		authorization = saasGetSec.GetSec();	
		       
	}
	
	@Test(description="saas后台用户查询",priority =0,dataProvider="dp")
	public void UserInfoQueryData(String desc,String url,String paras,String result) throws ClientProtocolException, IOException {
		restClient = new RestClient();
		DiffMethod diffMethod = new DiffMethod();
		//DiffMethodByExcept diffMethod = new DiffMethodByExcept();
		//准备请求头信息
		HashMap<String,String> headermap = new HashMap<String,String>();
		headermap.put("Content-Type", "application/json"); //这个在postman中可以查询到
		headermap.put("pub-sec",authorization[1]);
		headermap.put("sg-sec",authorization[2]);
		headermap.put("to-sec",authorization[0]);
		//入参设置
		url= host+url;
		//调用接口	
		System.out.println("url----"+url);
		System.out.println("paras----"+paras);
		closeableHttpResponse = restClient.post(url, paras, headermap);		
		HttpEntity entity = closeableHttpResponse.getEntity();
		String str = EntityUtils.toString(entity, "utf-8");
		//System.out.println("source-----"+str);
		//System.out.println("except-----"+result);
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
