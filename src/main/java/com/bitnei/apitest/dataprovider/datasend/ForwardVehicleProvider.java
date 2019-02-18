package com.bitnei.apitest.dataprovider.datasend;

import org.testng.annotations.DataProvider;

public class ForwardVehicleProvider {
	
	 @DataProvider(name="dataprovider1")  
	    public static Object[][] dataProvider1(){  
	        return new Object[][]{  
	        	{"{\"status\":200,\"errorMsg\":null,\"data\":{\"total\":1,\"list\":[{\"id\":381966,\"taskId\":22,\"vehicleId\":\"732527\",\"vin\":\"LSCAB23E8JG117597\",\"licensePlate\":null,\"vehType\":null,\"vehModelNo\":\"SC1027SJA5\",\"vendor\":null,\"operatingCompany\":null,\"communicationProtocol\":\"1\",\"city\":null,\"taskName\":\"任务1\",\"platformName\":\"合肥长安国家平台\",\"platformId\":\"3328f1f3-d8e6-11e8-a918-6c92bf645824\",\"protocolId\":\"6d8b1c1e-d7f7-11e8-a918-6c92bf645824\",\"protocolName\":\"国标32960\",\"monitorPlatform\":\"1\"}],\"pageNum\":1,\"pageSize\":10,\"size\":1,\"startRow\":1,\"endRow\":1,\"pages\":1,\"prePage\":0,\"nextPage\":0,\"isFirstPage\":true,\"isLastPage\":true,\"hasPreviousPage\":false,\"hasNextPage\":false,\"navigatePages\":8,\"navigatepageNums\":[1],\"navigateFirstPage\":1,\"navigateLastPage\":1,\"firstPage\":1,\"lastPage\":1},\"error\":false}"}
	        };
	    }
	 
	 @DataProvider(name="dataprovider2")  
	    public static Object[][] dataProvider2(){  
	        return new Object[][]{  
	        	{"{\"status\":200,\"errorMsg\":null,\"data\":{\"total\":1,\"list\":[{\"id\":353859,\"taskId\":22,\"vehicleId\":\"458750\",\"vin\":\"LS5A3DBE6HA014855\",\"licensePlate\":\"新QLR007\",\"vehType\":\"私家车\",\"vehModelNo\":\"SC7169G5\",\"vendor\":\"重庆长安汽车有限公司\",\"operatingCompany\":\"长安\",\"communicationProtocol\":\"1\",\"city\":\"北京市\",\"taskName\":\"任务1\",\"platformName\":\"合肥长安国家平台\",\"platformId\":\"3328f1f3-d8e6-11e8-a918-6c92bf645824\",\"protocolId\":\"6d8b1c1e-d7f7-11e8-a918-6c92bf645824\",\"protocolName\":\"国标32960\",\"monitorPlatform\":\"2\"}],\"pageNum\":1,\"pageSize\":10,\"size\":1,\"startRow\":1,\"endRow\":1,\"pages\":1,\"prePage\":0,\"nextPage\":0,\"isFirstPage\":true,\"isLastPage\":true,\"hasPreviousPage\":false,\"hasNextPage\":false,\"navigatePages\":8,\"navigatepageNums\":[1],\"navigateFirstPage\":1,\"navigateLastPage\":1,\"firstPage\":1,\"lastPage\":1},\"error\":false}"}
	        };
	    }
	 
	 @DataProvider(name="dataprovider3")  
	    public static Object[][] dataProvider3(){  
	        return new Object[][]{  
	        	{"{\"status\":200,\"errorMsg\":null,\"data\":{\"total\":1,\"list\":[{\"id\":\"786457\",\"vin\":\"LS5A2AJX5GA002716\",\"licensePlate\":\"黑P7700E\",\"vehType\":null,\"vehModelNo\":\"SC7155ABBH5\",\"vendor\":null,\"operatingCompany\":null,\"communicationProtocol\":\"HU_CHANGAN_V2\",\"city\":null,\"monitorPlatform\":\"1\",\"vehProperty\":null,\"vehSeriesNo\":\"S201\"}],\"pageNum\":1,\"pageSize\":10,\"size\":1,\"startRow\":1,\"endRow\":1,\"pages\":1,\"prePage\":0,\"nextPage\":0,\"isFirstPage\":true,\"isLastPage\":true,\"hasPreviousPage\":false,\"hasNextPage\":false,\"navigatePages\":8,\"navigatepageNums\":[1],\"navigateFirstPage\":1,\"navigateLastPage\":1,\"firstPage\":1,\"lastPage\":1},\"error\":false}"}
	        };
	    }
	 
	 @DataProvider(name="dataprovider4")  
	    public static Object[][] dataProvider4(){  
	        return new Object[][]{  
	        	{"{\"status\":200,\"errorMsg\":null,\"data\":null,\"error\":false}"}
	        };
	    }
	 
}
