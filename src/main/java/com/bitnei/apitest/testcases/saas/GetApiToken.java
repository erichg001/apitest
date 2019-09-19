package com.bitnei.apitest.testcases.saas;

import java.io.IOException;
import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bitnei.apitest.dao.impl.PlatFormDao;
import com.bitnei.apitest.dataprovider.saas.UserProvider;
import com.bitnei.apitest.utils.RestClient;

import net.sf.json.JSONObject;

public class GetApiToken {
	
	String url;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	GetCookie getCookie = new GetCookie();
	String authorization = "";
	
	@Resource(name="platformdao") 
    private PlatFormDao platformdao;
	
	@Parameters({"testsaas"})
	@BeforeClass
	public void setUp(String testsaas) {
		url = "http://test-oauth.bitnei.cn/acquire/token";
		//设置cookie		
		try {
			 authorization = getCookie.login();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	@Test(description="saas平台用户查询",priority =0,dataProvider="dataprovider2",
			dataProviderClass=UserProvider.class)
	public String ApiToken(String st) throws ClientProtocolException, IOException {
		restClient = new RestClient();
		//准备请求头信息
		HashMap<String,String> headermap = new HashMap<String,String>();
		headermap.put("Content-Type", "application/json"); //这个在postman中可以查询到
		headermap.put("Cookie",authorization);
		//入参设置
		//调用接口
		System.out.println("url------------"+url);	
		closeableHttpResponse = restClient.get(url, headermap);	
		HttpEntity entity = closeableHttpResponse.getEntity(); 
		String str = EntityUtils.toString(entity, "utf-8");
		JSONObject jsonobject = JSONObject.fromObject(str);
		String tokens = (String) jsonobject.getJSONObject("data").get("access_token");
		//System.out.println("str.indexOf(0)==========="+tokens);
		return tokens;

	}


}
