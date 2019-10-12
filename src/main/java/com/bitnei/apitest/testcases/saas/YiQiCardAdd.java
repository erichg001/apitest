package com.bitnei.apitest.testcases.saas;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bitnei.apitest.utils.ExcelReader;
import com.bitnei.apitest.utils.ExcleUtil;
import com.bitnei.apitest.utils.RestClient;

/** 
* @author 作者 eric_hg
* @version 创建时间：2019年9月29日 下午4:09:55 
* 类说明 
* 新增一汽报表，一个数据集下新建多个维护的报表，筛选条件为所有维度，通过excel读取参数
*/
public class YiQiCardAdd {
	String host;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	GetCookie getCookisaas = new GetCookie();
	String authorization = "";
	ExcelReader ex ;
    ExcleUtil excleUtil;
    int status;
		
	@Parameters({"hostsaas","pathroad"})
	@BeforeClass
	public void setUp(String hostsaas,String pathroad) {
		host = hostsaas;	
		String ExcelFilePath= pathroad ;
        String sheetName="yiqireport";
        ex = new ExcelReader(ExcelFilePath, sheetName);
		//设置cookie		
		try {
			 authorization = getCookisaas.login();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace(); 
		}	
		       
	}
	
	@Test(description="yiqi报表新增",priority =0,dataProvider="dp")
	public void CardAddData(String desc,String url,String paras,String result) throws ClientProtocolException, IOException {
		restClient = new RestClient();
		//DiffMethod diffMethod = new DiffMethod();
		//DiffMethodByExcept diffMethod = new DiffMethodByExcept();
		//准备请求头信息
		HashMap<String,String> headermap = new HashMap<String,String>();
		headermap.put("Content-Type", "application/json"); //这个在postman中可以查询到
		headermap.put("Cookie",authorization);
		//入参设置
		url= host+url;
		//调用接口	
		System.out.println("url----"+url);
		System.out.println("paras----"+paras);
		closeableHttpResponse = restClient.post(url, paras, headermap);		
//		HttpEntity entity = closeableHttpResponse.getEntity();
//		String str = EntityUtils.toString(entity, "utf-8");
		//System.out.println("source-----"+str);
		//System.out.println("except-----"+result);
		//System.out.println("getStatusLine"+closeableHttpResponse.getStatusLine().getStatusCode());
		status = closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(status, 200);
//		JSONObject lastobject = new JSONObject();
//		lastobject = diffMethod.diffFormatJson(JSONObject.fromObject(str),JSONObject.fromObject(result));
//		System.out.println(lastobject.toString());
//		Assert.assertEquals(lastobject.toString(), "{}");
	}
	
	@DataProvider
    public Object[][] dp(){
     Object[][] sheetData = ex.getSheetDataByPD();
        return  sheetData ;
    }

}
