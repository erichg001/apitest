package com.bitnei.apitest.testcases.saas;

import java.io.IOException;
import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.bitnei.apitest.dao.impl.PlatFormDao;
import com.bitnei.apitest.dataprovider.saas.UserProvider;
import com.bitnei.apitest.pro.saas.UserPro;
import com.bitnei.apitest.tool.DiffMethod;
import com.bitnei.apitest.utils.RestClient;

import net.sf.json.JSONObject;

public class UserInfoQuery {
	String url;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	GetCookie getCookisaas = new GetCookie();
	String authorization = "";
	HashMap<String,String> createTimeRange = new HashMap<String,String>();
	
	@Resource(name="platformdao") 
    private PlatFormDao platformdao;
	
	
	@Parameters({"hostsaas"})
	@BeforeClass
	public void setUp(String hostsaas) {
		url = hostsaas + "/user-center/userInfo/userWithRoles";
		//设置cookie		
		try {
			 authorization = getCookisaas.login();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Test(description="saas平台用户查询",priority =0,dataProvider="dataprovider1",
			dataProviderClass=UserProvider.class)
	public void UserInfoQueryData(String st) throws ClientProtocolException, IOException {
		restClient = new RestClient();
		DiffMethod diffMethod = new DiffMethod();
		//准备请求头信息
		HashMap<String,String> headermap = new HashMap<String,String>();
		headermap.put("Content-Type", "application/json"); //这个在postman中可以查询到
		headermap.put("Cookie",authorization);
		//入参设置
		createTimeRange.put("from", "");
		createTimeRange.put("to","");
		UserPro userpro = new UserPro();
		userpro.setNickName("hangang");	
		userpro.setEmail("");
		userpro.setMobile("");
		userpro.setOrigin("");
		userpro.setStatus("");
		userpro.setUserRole("");
		userpro.setCreateTimeRange(createTimeRange);
		String proJsonString = JSON.toJSONString(userpro);
		System.out.println("headermap------------"+headermap);	
		System.out.println("proJsonString------------"+proJsonString);
		//调用接口
		System.out.println("url------------"+url);	
		closeableHttpResponse = restClient.post(url, proJsonString, headermap);		
		HttpEntity entity = closeableHttpResponse.getEntity();
		String str = EntityUtils.toString(entity, "utf-8");
		System.out.println("source==========="+str);
		JSONObject lastobject = new JSONObject();
		System.out.println("except =============="+st);
		lastobject = diffMethod.diffFormatJson(JSONObject.fromObject(str),JSONObject.fromObject(st));
		System.out.println(lastobject.toString());
		//JSONObject jsonDiff = new JSONObject();
		Assert.assertEquals(lastobject.toString(), "{}");
	}

}
