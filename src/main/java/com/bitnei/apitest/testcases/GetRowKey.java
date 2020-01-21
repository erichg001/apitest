package com.bitnei.apitest.testcases;
/** 
* @author 作者 hangang
* @version 创建时间：2020年1月15日 上午11:49:09 
* 类说明 
*/
public class GetRowKey {
	 public static void main(String args[]) { 
		 String vin = "LNBSCC3H0GF303065";
		 int partition = Math.abs(vin.hashCode()%10);
		 long time = Integer.MAX_VALUE - 1573612868000L / 1000;

		 String rowkey = "0"+partition+"|"+vin+"|"+time;
		 System.out.println(rowkey);
	    } 

}
