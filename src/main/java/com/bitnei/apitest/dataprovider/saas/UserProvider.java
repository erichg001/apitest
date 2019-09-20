package com.bitnei.apitest.dataprovider.saas;

import org.testng.annotations.DataProvider;

public class UserProvider {
	@DataProvider(name="dataprovider1")  
    public static Object[][] dataProvider1(){  
        return new Object[][]{  
        	{"{\"status\":200,\"errorMsg\":null,\"data\":[{\"id\":\"c2da7bb67b354e32b140fa4676310de1\",\"username\":\"hangang@bitnei.cn\",\"nickName\":\"hangang\",\"email\":\"hangang@bitnei.cn\",\"mobile\":\"133****2222\",\"password\":\"N/A\",\"title\":\"test\",\"status\":\"ACTIVE\",\"createBy\":\"hangang@bitnei.cn\",\"createTime\":\"2019-09-16 13:50:38\",\"tenant\":\"bitnei-cn\",\"origin\":\"APPLY\",\"admin\":false,\"super\":false}],\"error\":false}"}
        };
    }
	
	@DataProvider(name="dataprovider2")  
    public static Object[][] dataProvider2(){  
        return new Object[][]{  
        	{"{\"status\":200,\"errorMsg\":null,\"data\":{\"user\":{\"typeName\":\"UserAdmin\",\"id\":\"782783112a42461298fbea5e0b5942c3\",\"username\":\"zhanghailong@bitnei.cn\",\"nickName\":\"张海龙\",\"email\":\"zhanghailong@bitnei.cn\",\"mobile\":\"181****4813\",\"password\":\"N/A\",\"title\":null,\"status\":\"ACTIVE\",\"createBy\":\"zhanghailong@bitnei.cn\",\"createTime\":\"2019-07-12 15:18:48\",\"lastUpdateTime\":\"2019-07-18 17:48:46\",\"tenant\":\"bitnei-cn\",\"origin\":\"REGISTER\",\"admin\":true,\"super\":true},\"tenant\":{\"id\":\"9c178ab462874ed28e92d245310e719f\",\"tenant\":\"bitnei-cn\",\"domain\":\"bitnei-cn\",\"organization\":\"新源科技\",\"status\":\"ACTIVE\",\"origin\":\"REGISTER_FREE\",\"createBy\":\"zhanghailong@bitnei.cn\",\"createTime\":\"2019-07-12 15:18:48\",\"lastUpdateTime\":\"2019-07-12 15:18:48\"}},\"error\":false}"}
        };
    }
}
