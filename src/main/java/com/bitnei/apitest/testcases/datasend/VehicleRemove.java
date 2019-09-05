package com.bitnei.apitest.testcases.datasend;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.bitnei.apitest.dao.impl.ForwardVehicleDao;
import com.bitnei.apitest.dao.impl.PlatFormDao;
import com.bitnei.apitest.dao.impl.ProtocolDao;
import com.bitnei.apitest.dao.impl.TaskDao;
import com.bitnei.apitest.dataprovider.datasend.PlatFormProvider;
import com.bitnei.apitest.pro.Method;
import com.bitnei.apitest.pro.datasendpro.ForwardVehiclePro;
import com.bitnei.apitest.pro.datasendpro.PlatFormPro;
import com.bitnei.apitest.pro.datasendpro.ProtocolPro;
import com.bitnei.apitest.pro.datasendpro.TaskPro;
import com.bitnei.apitest.sql.C;
import com.bitnei.apitest.tool.DiffMethod;
import com.bitnei.apitest.utils.RestClient;

import net.sf.json.JSONObject;

public class VehicleRemove {
	String url;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	GetCookieNew getCookie = new GetCookieNew();
	String cookie = "";
	
	@Resource(name="taskdao") 
    private TaskDao taskdao;
	
	@Parameters({"host"})
	@BeforeClass
	public void setUp(String host) {
		url = host + "/forward/dataForwardConfig/task/removeVehicle?operateOrigin=ForwardTask";
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
	
	@Test(description="任务添加车辆",priority =0,dataProvider="dataprovider1",
			dataProviderClass=PlatFormProvider.class)
	public void VehicleRemoveData(String st) throws ClientProtocolException, IOException {
		restClient = new RestClient();
		DiffMethod diffMethod = new DiffMethod();
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-mybatis.xml");
		ProtocolDao protocoldao = (ProtocolDao) context.getBean("protocoldao");
		PlatFormDao platformdao = (PlatFormDao) context.getBean("platformdao");
		TaskDao taskdao = (TaskDao)context.getBean("taskdao");
		List<PlatFormPro> listplat = platformdao.list(Method.where("unit_name", C.EQ, "autotestplant"));
		List<TaskPro> list = taskdao.list(Method.where("name", C.EQ, "autotesttask"));		
		//ForwardVehicleDao forwardvehicledao = (ForwardVehicleDao) context.getBean("forwardvehicledao");
		//List<List<PlatFormPro> listplat = platformdao.list(Method.where("unit_name", C.EQ, "autotestmodif"));> listveh = forwardvehicledao.list(Method.where("unit_name", C.EQ, "autotestmodif"));
		//准备请求头信息
		HashMap<String,String> headermap = new HashMap<String,String>();
		headermap.put("Content-Type", "application/json"); //这个在postman中可以查询到
		headermap.put("Cookie",cookie );
		//System.out.println("listplat.get(0).getId()------------"+listplat.get(0).getId());
		//入参设置
		ForwardVehiclePro forwardvehiclepro  = new ForwardVehiclePro();
		forwardvehiclepro.setVehicleIds("LS4ASE2E4JJ222266");
		forwardvehiclepro.setPlatformId(listplat.get(0).getId());
		forwardvehiclepro.setTaskId(list.get(0).getId());
		
		String proJsonString = JSON.toJSONString(forwardvehiclepro);
		System.out.println("proJsonString------------"+proJsonString);
		//调用接口
		closeableHttpResponse = restClient.post(url, proJsonString, headermap);
		//System.out.println("closeableHttpResponse------------"+closeableHttpResponse);		
		HttpEntity entity = closeableHttpResponse.getEntity();
		String str = EntityUtils.toString(entity, "utf-8");
		System.out.println("source==========="+str);
		JSONObject lastobject = new JSONObject();
		System.out.println("except =============="+st);
		lastobject = diffMethod.diffFormatJson(JSONObject.fromObject(str),JSONObject.fromObject(st));
		System.out.println(lastobject.toString());
		JSONObject jsonDiff = new JSONObject();
		//数据库删除任务，平台 ，协议
		taskdao.del(Method.where("name", C.EQ, "autotesttask"));
		platformdao.del(Method.where("unit_name", C.EQ, "autotestplant"));
		protocoldao.del(Method.where("name", C.EQ, "autotestng"));
		Assert.assertEquals(lastobject.toString(), "{}");
	}
	
	
}
