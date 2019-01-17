package com.bitnei.apitest.dataprovider.datasend;

import org.testng.annotations.DataProvider;

public class PlatFormProvider {
	//
    @DataProvider(name="dataprovider1")  
    public static Object[][] dataProvider1(){  
        return new Object[][]{  
        	{"{\"status\":200,\"errorMsg\":null,\"data\":null,\"error\":false}"}
        };
    }
}
