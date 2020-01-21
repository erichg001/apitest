package com.bitnei.apitest.testcases.saas;

import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.bitnei.apitest.tool.DiffMethod;
import com.bitnei.apitest.utils.RestClient;

import net.sf.json.JSONObject;

/** 
* @author 作者 hangang
* @version 创建时间：2020年1月14日 上午11:24:22 
* 类说明 
*/
public class GetPub {
	@Test
	public void pub() throws ClientProtocolException, IOException {
		//获取浏览器首次返回cookie的url
        String urls = "http://test-saas.bitnei.cn/user-center/pub";        
		String cookie = "";
        HttpClient httpClient = new HttpClient();
        RestClient restClient = new RestClient();
        // 模拟登陆，按实际服务器端要求选用 Post 或 Get 请求方式
        //DiffMethod diffMethod = new DiffMethod();
		//准备请求头信息
//		HashMap<String,String> headermap = new HashMap<String,String>();
//		headermap.put("Content-Type", "application/json"); //这个在postman中可以查询到
//		headermap.put("Cookie",authorization);
//		//入参设置
//		url= host +url+paras;
//		System.out.println("URL------"+url);
		//调用接口	
		CloseableHttpResponse closeableHttpResponse = restClient.get(urls);	
		
		HttpEntity entity = closeableHttpResponse.getEntity();
		Header header=entity.getContentType();
		String str = EntityUtils.toString(entity, "utf-8");
		System.out.println("str"+header);
//		JSONObject lastobject = new JSONObject();
//		lastobject = diffMethod.diffFormatJson(JSONObject.fromObject(str),JSONObject.fromObject(result));
//		Assert.assertEquals(lastobject.toString(), "{}");
        
		//return cookie;
		
	}

}
