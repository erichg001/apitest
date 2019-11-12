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

import com.bitnei.apitest.utils.ExcelReader;
import com.bitnei.apitest.utils.ExcleUtil;
import com.bitnei.apitest.utils.RestClient;

import net.sf.json.JSONObject;

/** 
* @author 作者 eric_hg
* @version 创建时间：2019年10月21日 下午2:43:37 
* 类说明 
* api返回码验证
* 10000	SUCCESS
10001	令牌过期
10002	令牌无效
10003	令牌获取错误
20000	API授权过期
20001	无效用户IP
20002	API未被授权
20003	API访问过于频繁
20004	API访问超时
30001	API内部非法参数
30002	API内部非法参数号
30003	API违法类型
30004	不支持API查询
30005	不支持API导出
30006	API不可用
30007	API不存在
40000	未知错误
*/
public class ApiTest {
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
	public void setUp(String hostsaas,String pathroad) {
		host = hostsaas;	
		String ExcelFilePath= pathroad ;
        String sheetName="apicode";
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
	
	@Test(description="api返回码验证为10000",priority =0,dataProvider="dp")
	public void ApiTestCode(String desc,String url,String paras,String result) throws ClientProtocolException, IOException {
		restClient = new RestClient();
		//DiffMethod diffMethod = new DiffMethod();
		//DiffMethodByExcept diffMethod = new DiffMethodByExcept();
		//准备请求头信息
		HashMap<String,String> headermap = new HashMap<String,String>();
		headermap.put("Content-Type", "application/json"); //这个在postman中可以查询到
		headermap.put("Authorization","bearer "+token);
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
		status = closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(status, 200);
//		JSONObject lastobject = new JSONObject();
//		lastobject = diffMethod.diffFormatJson(JSONObject.fromObject(str),JSONObject.fromObject(result));
//		System.out.println(lastobject.toString());
//		Assert.assertEquals(lastobject.toString(), "{}");
		JSONObject jsonobject = JSONObject.fromObject(str);		
		System.out.println("jsonobject==========="+jsonobject);
		String code =  jsonobject.get("code").toString();
		System.out.println("code==========="+code);
		Assert.assertEquals(code, result);
	}
	
	@DataProvider
    public Object[][] dp(){
     Object[][] sheetData = ex.getSheetDataByPD();
        return  sheetData ;
    }

}
