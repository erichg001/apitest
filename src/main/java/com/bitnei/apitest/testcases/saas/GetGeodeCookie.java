package com.bitnei.apitest.testcases.saas;

import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.Test;

/** 
* @author 作者 hangang
* @version 创建时间：2020年1月9日 下午4:19:34 
* 类说明 
*/
public class GetGeodeCookie {
	@Test
	public String login() throws ClientProtocolException, IOException {
		//获取浏览器首次返回cookie的url
        String urls = "http://10.10.21.70:7070/pulse/login";        
		String cookie = "";
        HttpClient httpClient = new HttpClient();
        // 模拟登陆，按实际服务器端要求选用 Post 或 Get 请求方式
        PostMethod postMethod = new PostMethod(urls);
        
        // 设置登陆时要求的信息，用户名和密码
        NameValuePair[] data = { new NameValuePair("username", "admin"), new NameValuePair("password", "admin") };
        postMethod.setRequestBody(data);

        try {
            // 设置 HttpClient 接收 Cookie,用与浏览器一样的策略
            httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
            int statusCode=httpClient.executeMethod(postMethod);
            //System.out.println("statusCode = "+statusCode);              
            // 获得登陆后的 Cookie
            Cookie[] cookies = httpClient.getState().getCookies();
            StringBuffer tmpcookies = new StringBuffer();
            for (Cookie c : cookies) {
                tmpcookies.append(c.toString() + ";");
                cookie =c.toString();
                System.out.println("cookies = "+cookie);
            }           
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        HashMap<String,String> headermap = new HashMap<String,String>();
		headermap.put("Content-Type", "application/json"); //这个在postman中可以查询到
		headermap.put("Cookie", cookie);
		System.out.println(headermap);
        
		return cookie;
		
	}

}
