package com.bitnei.apitest.testcases.saasonline;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import com.bitnei.apitest.testcases.saas.GetCookie;
import com.bitnei.apitest.utils.RestClient;

import net.sf.json.JSONObject;

/** 
* @author 作者 hangang
* @version 创建时间：2020年4月13日 上午10:06:32 
* 类说明 
*/
public class GetApiTokenOnline {
	String url;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	GetCookie getCookie = new GetCookie();
	String[] authorization = null;
	SaasGetSecOnline saasGetSec = new SaasGetSecOnline();
	
//	@Resource(name="platformdao") 
//    private PlatFormDao platformdao;
	
	@Test(description="api接口token获取")
	public String ApiToken() throws ClientProtocolException, IOException, InterruptedException {
		restClient = new RestClient();
		//设置cookie		
		authorization = saasGetSec.GetSec();
		//准备请求头信息
		HashMap<String,String> headermap = new HashMap<String,String>();
		headermap.put("Content-Type", "application/json"); //这个在postman中可以查询到
		headermap.put("pub-sec",authorization[1]);
		headermap.put("sg-sec",authorization[2]);
		headermap.put("to-sec",authorization[0]);
		//入参设置
		url = "https://oauth.bitnei.cn/acquire/token";
		//调用接口
		System.out.println("url------------"+url);	
		closeableHttpResponse = restClient.get(url, headermap);	
		HttpEntity entity = closeableHttpResponse.getEntity(); 
		String str = EntityUtils.toString(entity, "utf-8");
		JSONObject jsonobject = JSONObject.fromObject(str);		
		System.out.println("jsonobject==========="+jsonobject);
		String tokens =  jsonobject.getJSONObject("data").get("access_token").toString();
		System.out.println("str.indexOf(0)==========="+tokens);
		return tokens;

	}

}
