package com.bitnei.apitest.dataprovider.saas;

import org.testng.annotations.DataProvider;

public class UserProvider {
	@DataProvider(name="dataprovider1")  
    public static Object[][] dataProvider1(){  
        return new Object[][]{  
        	{"{\"status\":200,\"errorMsg\":null,\"data\":[{\"id\":\"5a51de0cc97e48ea8580d46796724a82\",\"username\":\"xiejunjun@bitnei.cn\",\"nickName\":\"xiejunjun\",\"email\":\"xiejunjun@bitnei.cn\",\"mobile\":\"13122223333\",\"password\":null,\"title\":\"\",\"status\":\"ACTIVE\",\"createBy\":\"yaomeng@bitnei.cn\",\"createTime\":\"2019-02-22 06:25:52\",\"lastUpdateTime\":\"2019-03-08 10:29:02\",\"tenant\":\"bitnei-cn\",\"origin\":\"INVITE\",\"admin\":false}],\"error\":false}"}
        };
    }
	
	@DataProvider(name="dataprovider2")  
    public static Object[][] dataProvider2(){  
        return new Object[][]{  
        	{"{\"status\":200,\"errorMsg\":null,\"data\":{\"user\":{\"typeName\":\"UserAdmin\",\"id\":\"782783112a42461298fbea5e0b5942c3\",\"username\":\"zhanghailong@bitnei.cn\",\"nickName\":\"张海龙\",\"email\":\"zhanghailong@bitnei.cn\",\"mobile\":\"181****4813\",\"password\":\"N/A\",\"title\":null,\"status\":\"ACTIVE\",\"createBy\":\"zhanghailong@bitnei.cn\",\"createTime\":\"2019-07-12 15:18:48\",\"lastUpdateTime\":\"2019-07-18 17:48:46\",\"tenant\":\"bitnei-cn\",\"origin\":\"REGISTER\",\"admin\":true,\"super\":true},\"tenant\":{\"id\":\"9c178ab462874ed28e92d245310e719f\",\"tenant\":\"bitnei-cn\",\"domain\":\"bitnei-cn\",\"organization\":\"新源科技\",\"status\":\"ACTIVE\",\"origin\":\"REGISTER_FREE\",\"createBy\":\"zhanghailong@bitnei.cn\",\"createTime\":\"2019-07-12 15:18:48\",\"lastUpdateTime\":\"2019-07-12 15:18:48\"}},\"error\":false}"}
        };
    }
}
