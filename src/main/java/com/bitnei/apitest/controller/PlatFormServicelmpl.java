package com.bitnei.apitest.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.testng.annotations.Test;

import com.bitnei.apitest.dao.impl.PlatFormDao;
import com.bitnei.apitest.pro.Method;
import com.bitnei.apitest.pro.datasendpro.PlatFormPro;
import com.bitnei.apitest.sql.C;

@Repository
@Test
public class PlatFormServicelmpl {
	
    @Resource
    private PlatFormDao platformdao;

	 public List<PlatFormPro> listUser(String name){
	        return platformdao.list(Method.where("unitName", C.LIKE, name));
	    }
	 
}
