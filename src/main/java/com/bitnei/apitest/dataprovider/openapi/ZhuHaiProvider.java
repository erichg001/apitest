package com.bitnei.apitest.dataprovider.openapi;

import org.testng.annotations.DataProvider;

public class ZhuHaiProvider {
	@DataProvider(name="dataprovider1")  
    public static Object[][] dataProvider1(){  
        return new Object[][]{  
        	{"{\"status\":200,\"errorMsg\":null,\"data\":[{\"startElectricity\":124.25,\"endElectricity\":150.5,\"startTime\":\"2019-03-01 09:19:34\",\"endTime\":\"2019-03-01 09:42:34\"}],\"error\":false}"}
        };
    }
	
	@DataProvider(name="dataprovider2")  
    public static Object[][] dataProvider2(){  
        return new Object[][]{  
        	{"{\"status\":400,\"errorMsg\":\"VALID\",\"data\":\"参数 from  不符合规则：yyyy-MM-dd HH:mm:ss\",\"error\":true}"}
        };
    }
	
	@DataProvider(name="dataprovider3")  
    public static Object[][] dataProvider3(){  
        return new Object[][]{  
        	{"{\"status\":400,\"errorMsg\":\"VALID\",\"data\":\"参数 to  不符合规则：yyyy-MM-dd HH:mm:ss\",\"error\":true}"}
        };
    }
	
	@DataProvider(name="dataprovider4")  
    public static Object[][] dataProvider4(){  
        return new Object[][]{  
        	{"{\"status\":400,\"errorMsg\":\"VALID\",\"data\":\"选择时间范围不得超过一个自然日\",\"error\":true}"}
        };
    }
	
	@DataProvider(name="dataprovider5")  
    public static Object[][] dataProvider5(){  
        return new Object[][]{  
        	{"{\"status\":400,\"errorMsg\":\"VALID\",\"data\":\"参数 from 2019-03-03 10:49:21 不能大于 to 2019-03-02 10:49:21\",\"error\":true}"}
        };
    }
	
	@DataProvider(name="dataprovider6")  
    public static Object[][] dataProvider6(){  
        return new Object[][]{  
        	{"{\"status\":200,\"errorMsg\":null,\"data\":0.5072,\"error\":false}"}
        };
    }
	
	@DataProvider(name="dataprovider7")  
    public static Object[][] dataProvider7(){  
        return new Object[][]{  
        	{"{\"status\":400,\"errorMsg\":\"VALID\",\"data\":\"选择时间范围不得超过3个月\",\"error\":true}"}
        };
    }
	
	@DataProvider(name="dataprovider8")  
    public static Object[][] dataProvider8(){  
        return new Object[][]{  
        	{"{\"status\":400,\"errorMsg\":\"VALID\",\"data\":\"参数 from 2019-05-10 不能大于 to 2019-04-13\",\"error\":true}"}
        };
    }
	
	@DataProvider(name="dataprovider9")  
    public static Object[][] dataProvider9(){  
        return new Object[][]{  
        	{"{\"status\":400,\"errorMsg\":\"VALID\",\"data\":\"参数 from 2019.05.10 不符合规则：yyyy-MM-dd\",\"error\":true}"}
        };
    }
	
	@DataProvider(name="dataprovider10")  
    public static Object[][] dataProvider10(){  
        return new Object[][]{  
        	{"{\"status\":200,\"errorMsg\":null,\"data\":[{\"startSoc\":93.0,\"endSoc\":87.0,\"startLocation\":\"广东省珠海市金湾区\",\"endLocation\":\"广东省珠海市金湾区\",\"runTime\":2520,\"rumMileage\":19.0,\"startTime\":\"2019-03-01 12:06:26\",\"endTime\":\"2019-03-01 12:48:26\"},{\"startSoc\":87.0,\"endSoc\":81.0,\"startLocation\":\"广东省珠海市金湾区\",\"endLocation\":\"广东省珠海市金湾区\",\"runTime\":2610,\"rumMileage\":22.0,\"startTime\":\"2019-03-01 13:08:19\",\"endTime\":\"2019-03-01 13:51:49\"}],\"error\":false}"}
        };
    }
	
	@DataProvider(name="dataprovider11")  
    public static Object[][] dataProvider11(){  
        return new Object[][]{  
        	{"{\"status\":400,\"errorMsg\":\"VALID\",\"data\":\"参数 from 2019.05.10 不符合规则：yyyy-MM-dd\",\"error\":true}"}
        };
    }

}
