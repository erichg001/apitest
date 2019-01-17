package com.bitnei.apitest.testcases.datasend;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.bitnei.apitest.controller.PlatFormServicelmpl;
import com.bitnei.apitest.controller.UserServiceImpl;
import com.bitnei.apitest.dao.impl.PlatFormDao;
import com.bitnei.apitest.dataprovider.datasend.PlatFormProvider;
import com.bitnei.apitest.pro.datasendpro.PlatFormPro;
import com.bitnei.apitest.testcases.GetCookie;
import com.bitnei.apitest.tool.DiffMethod;
import com.bitnei.apitest.utils.RestClient;

import net.sf.json.JSONObject;

public class PlatForm {
	String url;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	GetCookie getCookie = new GetCookie();
	String cookie = "";
	PlatFormServicelmpl userserviceimpl = new PlatFormServicelmpl();
	@Resource
	PlatFormPro platformpro = new PlatFormPro();
	
	
    @Resource
    private PlatFormDao platformdao;
	
	
	@BeforeClass
	public void setUp() {
		//url = host + "/rest/dataForwardConfig/platform/add";
		//设置cookie		
		try {
			 cookie = getCookie.login();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
//	@Parameters({"host"})
//	@BeforeMethod()
//	public void setUp1(String host) {
//		url = host + "/rest/dataForwardConfig/platform/add";
//	}
//	
//	@Test(description="数据转发平台配置新增数据",priority =0,dataProvider="dataprovider1",
//			dataProviderClass=PlatFormProvider.class)
//	public void PlatFormAdd(String st) throws ClientProtocolException, IOException {
//		restClient = new RestClient();
//		DiffMethod diffMethod = new DiffMethod();
//		//准备请求头信息
//		HashMap<String,String> headermap = new HashMap<String,String>();
//		headermap.put("Content-Type", "application/json"); //这个在postman中可以查询到
//		headermap.put("Cookie",cookie );	
//		//入参设置
//		PlatFormPro platformpro = new PlatFormPro();
//		platformpro.setAddress("10.10.1.1");
//		platformpro.setCdKey("autotest");
//		platformpro.setForwardMode("SOCKET");
//		platformpro.setNotes("autotesthg");
//		platformpro.setNsPort("8000");
//		platformpro.setPassword("autotesthg");
//		platformpro.setPriority("1");
//		platformpro.setStaticForwardPlatform("上海平台");
//		platformpro.setUnitName("autotestplant");
//		platformpro.setUsername("autotesthg");
//		
//		String proJsonString = JSON.toJSONString(platformpro);
//		System.out.println("evdetailproJsonString------------"+proJsonString);
//		//调用接口
//		closeableHttpResponse = restClient.post(url, proJsonString, headermap);
//		//System.out.println("closeableHttpResponse------------"+closeableHttpResponse);		
//		HttpEntity entity = closeableHttpResponse.getEntity();
//		String str = EntityUtils.toString(entity, "utf-8");
//		System.out.println("source==========="+str);
//		JSONObject lastobject = new JSONObject();
//		System.out.println("except =============="+st);
//		lastobject = diffMethod.diffFormatJson(JSONObject.fromObject(str),JSONObject.fromObject(st));
//		System.out.println(lastobject.toString());
//		JSONObject jsonDiff = new JSONObject();
//		Assert.assertEquals(lastobject.toString(), "{}");
//	}
	
	@Parameters({"host"})
	@BeforeMethod()
	public void setUp2(String host) {
		url = host + "/rest/dataForwardConfig/platform/update";
	}
	

	
	@Test(description="数据转发平台配置修改数据",priority =1,dataProvider="dataprovider1",
			dataProviderClass=PlatFormProvider.class)
	public void PlatFormModify(String st) throws ClientProtocolException, IOException {
		restClient = new RestClient();
		DiffMethod diffMethod = new DiffMethod();
		System.out.println("evdetailproJsonString------------"+platformpro);
				
		
		List<PlatFormPro> list = userserviceimpl.listUser("autotestplant");
		System.out.println("evdetailproJsonString------------"+list);
		
		//准备请求头信息
		HashMap<String,String> headermap = new HashMap<String,String>();
		headermap.put("Content-Type", "application/json"); //这个在postman中可以查询到
		headermap.put("Cookie",cookie );	
		//入参设置
		PlatFormPro platformpro = new PlatFormPro();
		platformpro.setAddress("10.10.1.1");
		platformpro.setCdKey("autotest");
		platformpro.setForwardMode("SOCKET");
		platformpro.setNotes("autotesthg");
		platformpro.setNsPort("8000");
		platformpro.setPassword("autotesthg");
		platformpro.setPriority("1");
		platformpro.setStaticForwardPlatform("上海平台");
		platformpro.setUnitName("autotestplant0000");
		platformpro.setUsername("autotesthg");
		
		
		
		String proJsonString = JSON.toJSONString(platformpro);
		System.out.println("evdetailproJsonString------------"+proJsonString);
		//调用接口
		closeableHttpResponse = restClient.post(url, proJsonString, headermap);
		//closeableHttpResponse = restClient.post(url, headermap,evdetailpro);
		//System.out.println("closeableHttpResponse------------"+closeableHttpResponse);		
		HttpEntity entity = closeableHttpResponse.getEntity();
		String str = EntityUtils.toString(entity, "utf-8");
		System.out.println("source==========="+str);
		JSONObject lastobject = new JSONObject();
		System.out.println("except =============="+st);
		lastobject = diffMethod.diffFormatJson(JSONObject.fromObject(str),JSONObject.fromObject(st));
		System.out.println(lastobject.toString());
		JSONObject jsonDiff = new JSONObject();
		Assert.assertEquals(lastobject.toString(), "{}");
	}
}
