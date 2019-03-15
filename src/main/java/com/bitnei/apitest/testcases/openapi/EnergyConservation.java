package com.bitnei.apitest.testcases.openapi;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bitnei.apitest.dataprovider.openapi.ZhuHaiProvider;
import com.bitnei.apitest.tool.DiffMethod;
import com.bitnei.apitest.utils.RestClient;

import net.sf.json.JSONObject;

public class EnergyConservation {
	String url;
	String urlpara = "";
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	String authorization = "f829155c-9374-4673-a005-10ce548af19e";
	
	@Parameters({"hostopenapi"})
	@BeforeClass
	public void setUp(String hostopenapi) {
		url = hostopenapi + "/external/1/zhuhai/api/energyConservation";	
	}
	@Test(description="碳排放接口查询正常",priority =0,dataProvider="dataprovider6",
			dataProviderClass=ZhuHaiProvider.class)
	public void EnergyConservationNormal(String st) throws ClientProtocolException, IOException {
		restClient = new RestClient();
		DiffMethod diffMethod = new DiffMethod();
		//准备请求头信息
		HashMap<String,String> headermap = new HashMap<String,String>();
		headermap.put("Authorization","Bearer "+authorization);
		//入参设置             
		urlpara= url+"?from=2019-02-10&to=2019-03-13";
		//调用接口
		System.out.println("url------------"+url);	
		System.out.println("headermap------------"+headermap);
		closeableHttpResponse = restClient.get(urlpara, headermap);
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

	@Test(description="碳排放接口时间超3个月",priority =0,dataProvider="dataprovider7",
			dataProviderClass=ZhuHaiProvider.class)
	public void EnergyConservationUpScope(String st) throws ClientProtocolException, IOException {
		restClient = new RestClient();
		DiffMethod diffMethod = new DiffMethod();
		//准备请求头信息
		HashMap<String,String> headermap = new HashMap<String,String>();
		headermap.put("Content-Type", "application/json"); 
		headermap.put("Authorization","Bearer "+authorization);
		//入参设置             
		urlpara= url+"?from=2019-01-10&to=2019-04-13";
		//调用接口
		closeableHttpResponse = restClient.get(urlpara, headermap);		
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

	@Test(description="碳排放接口开始间大于结束时间",priority =0,dataProvider="dataprovider8",
			dataProviderClass=ZhuHaiProvider.class)
	public void EnergyConservationWrongTime(String st) throws ClientProtocolException, IOException {
		restClient = new RestClient();
		DiffMethod diffMethod = new DiffMethod();
		//准备请求头信息
		HashMap<String,String> headermap = new HashMap<String,String>();
		headermap.put("Authorization","Bearer "+authorization);
		//入参设置             
		urlpara= url+"?from=2019-05-10&to=2019-04-13";
		//调用接口
		closeableHttpResponse = restClient.get(urlpara, headermap);		
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
	
	@Test(description="碳排放接口开始间大于结束时间",priority =0,dataProvider="dataprovider9",
			dataProviderClass=ZhuHaiProvider.class)
	public void EnergyConservationWrongDateFormat(String st) throws ClientProtocolException, IOException {
		restClient = new RestClient();
		DiffMethod diffMethod = new DiffMethod();
		//准备请求头信息
		HashMap<String,String> headermap = new HashMap<String,String>();
		headermap.put("Authorization","Bearer "+authorization);
		//入参设置             
		urlpara= url+"?from=2019.05.10&to=2019-04-13";
		//调用接口
		closeableHttpResponse = restClient.get(urlpara, headermap);		
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
