package com.bitnei.apitest.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClient {
		//1. Get 请求方法
		public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		//创建一个可关闭的HttpClient对象
			CloseableHttpClient httpclient = HttpClients.createDefault();
			//创建一个HttpGet的请求对象
			HttpGet httpget = new HttpGet(url);
			//执行请求,相当于postman上点击发送按钮，然后赋值给HttpResponse对象接收
			CloseableHttpResponse httpResponse = httpclient.execute(httpget);
			httpclient.close();
			return httpResponse;
		}
		//2. Get 请求方法（带请求头信息）
		public CloseableHttpResponse get(String url,HashMap<String,String> headermap) 
				throws ClientProtocolException, IOException{
			//创建一个可关闭的HttpClient对象
			CloseableHttpClient httpclient = HttpClients.createDefault();
			//创建一个HttpGet的请求对象
			HttpGet httpget = new HttpGet(url);
			//加载请求头到httpget对象
			for(Map.Entry<String, String> entry : headermap.entrySet()) {
				httpget.addHeader(entry.getKey(), entry.getValue());
			}
			//执行请求,相当于postman上点击发送按钮，然后赋值给HttpResponse对象接收
			CloseableHttpResponse httpResponse = httpclient.execute(httpget);
			return httpResponse;
		}
		//3. POST方法
		public CloseableHttpResponse post(String url) 
				throws ClientProtocolException, IOException {
			//创建一个可关闭的HttpClient对象
			CloseableHttpClient httpclient = HttpClients.createDefault();
			//创建一个HttpPost的请求对象
			HttpPost httppost = new HttpPost(url);
		
			//发送post请求
			CloseableHttpResponse httpResponse = httpclient.execute(httppost);			
			return httpResponse;
		}
		//4. POST方法
		public CloseableHttpResponse post(String url, String entityString, HashMap<String,String> headermap) 
				throws ClientProtocolException, IOException {
			//创建一个可关闭的HttpClient对象
			CloseableHttpClient httpclient = HttpClients.createDefault();
			//创建一个HttpPost的请求对象
			HttpPost httppost = new HttpPost(url);
			//设置payload
			httppost.setEntity(new StringEntity(entityString,"utf-8"));

			//加载请求头到httppost对象
			for(Map.Entry<String, String> entry : headermap.entrySet()) {
				httppost.addHeader(entry.getKey(), entry.getValue());
			}
			//发送post请求
			CloseableHttpResponse httpResponse = httpclient.execute(httppost);			
			return httpResponse;
		}
		//5.post
		public CloseableHttpResponse post(String url, HashMap<String,String> headermap) 
				throws ClientProtocolException, IOException {
			//创建一个可关闭的HttpClient对象
			CloseableHttpClient httpclient = HttpClients.createDefault();
			//创建一个HttpPost的请求对象
			HttpPost httppost = new HttpPost(url);
			//设置payload
			//httppost.setEntity(new StringEntity(entityString));

			//加载请求头到httppost对象
			for(Map.Entry<String, String> entry : headermap.entrySet()) {
				httppost.addHeader(entry.getKey(), entry.getValue());
			}
			//发送post请求
			CloseableHttpResponse httpResponse = httpclient.execute(httppost);			
			return httpResponse;
		}
		//6. DELETE 请求方法
		public CloseableHttpResponse delete(String url, HashMap<String,String> headermap) 
				throws ClientProtocolException, IOException {
		///创建一个可关闭的HttpClient对象
			CloseableHttpClient httpclient = HttpClients.createDefault();
			//创建一个HttpGet的请求对象
			HttpDelete httpdelete = new HttpDelete(url);
			//加载请求头到httpdelete对象
			for(Map.Entry<String, String> entry : headermap.entrySet()) {
				httpdelete.addHeader(entry.getKey(), entry.getValue());
			}
			//执行请求,相当于postman上点击发送按钮，然后赋值给HttpResponse对象接收
			CloseableHttpResponse httpResponse = httpclient.execute(httpdelete);
			return httpResponse;
		}
		
		//6. put 请求方法
		public CloseableHttpResponse put(String url, String entityString, HashMap<String,String> headermap) 
				throws ClientProtocolException, IOException {
			//创建一个可关闭的HttpClient对象
			CloseableHttpClient httpclient = HttpClients.createDefault();
			//创建一个HttpPost的请求对象
			HttpPut httppost = new HttpPut(url);
			//设置payload
			httppost.setEntity(new StringEntity(entityString,"utf-8"));

			//加载请求头到httppost对象
			for(Map.Entry<String, String> entry : headermap.entrySet()) {
				httppost.addHeader(entry.getKey(), entry.getValue());
			}
			//发送post请求
			CloseableHttpResponse httpResponse = httpclient.execute(httppost);			
			return httpResponse;
		}
		
	}