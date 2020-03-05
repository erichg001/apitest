package com.bitnei.apitest.testcases.saas;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bitnei.apitest.dao.impl.PlatFormDao;
import com.bitnei.apitest.dao.impl.ProtocolDao;
import com.bitnei.apitest.dao.impl.openapi.VehicleChargeMetaDao;
import com.bitnei.apitest.pro.Method;
import com.bitnei.apitest.sql.C;
import com.bitnei.apitest.tool.DiffMethod;
import com.bitnei.apitest.utils.ExcelReader;
import com.bitnei.apitest.utils.ExcleUtil;
import com.bitnei.apitest.utils.RestClient;

import net.sf.json.JSONObject;

/** 
* @author 作者 eric_hg
* @version 创建时间：2019年12月24日 下午2:08:13 
* 类说明 
*/
public class PostApiTestParas {

	String host;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	GetApiToken getToken = new GetApiToken ();
	String token = "";
	ExcelReader ex ;
    ExcleUtil excleUtil;
    int status;
		
	@Parameters({"hostsaas","pathroad"})
	@BeforeClass
	public void setUp(String hostsaas,String pathroad) throws InterruptedException {
		host = hostsaas;	
		String ExcelFilePath= pathroad ;
        String sheetName="apipostparas";
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
	
	
	@Test(description="api接口",priority =0,dataProvider="dp")
	public void ApiTest(String desc,String url,String paras,String result) throws ClientProtocolException, IOException, InterruptedException {
		restClient = new RestClient();
		DiffMethod diffMethod = new DiffMethod();
		//准备请求头信息
		HashMap<String,String> headermap = new HashMap<String,String>();
		headermap.put("Content-Type", "application/json"); //这个在postman中可以查询到
		headermap.put("Authorization","bearer "+token);
		//连接数据库
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-mybatis.xml");
		VehicleChargeMetaDao vehiclechargemetadao = (VehicleChargeMetaDao) context.getBean("vehiclechargemetadao");
		//入参设置
		url= host+url;
		//调用接口	
		//System.out.println("url----"+url);
		//System.out.println("paras----"+paras);
		closeableHttpResponse = restClient.post(url, paras, headermap);		
		HttpEntity entity = closeableHttpResponse.getEntity();
		String str = EntityUtils.toString(entity, "utf-8");
		System.out.println("source-----"+str);
		System.out.println("except-----"+result);
		//System.out.println("getStatusLine"+closeableHttpResponse.getStatusLine().getStatusCode());
		JSONObject lastobject = new JSONObject();
		lastobject = diffMethod.diffFormatJson(JSONObject.fromObject(str),JSONObject.fromObject(result));
		System.out.println(lastobject.toString());
		Assert.assertEquals(lastobject.toString(), "{}");
		//验证数据库，保存数据成功并删除数据
		Thread.sleep(1000);
		boolean b1 = vehiclechargemetadao.isExist(Method.where("car_no", C.EQ, "京ADK2768"));
		Assert.assertEquals(b1, true);
		vehiclechargemetadao.del(Method.where("car_no", C.EQ, "京ADK2768"));
	}
	
	@DataProvider
    public Object[][] dp(){
     Object[][] sheetData = ex.getSheetDataByPD();
        return  sheetData ;
    }

}
