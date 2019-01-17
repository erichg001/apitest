package com.bitnei.apitest.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.bitnei.apitest.pro.WherePrams;
import com.bitnei.apitest.pro.datasendpro.PlatFormPro;

@Resource(name = "PlatFormDao")
public class PlatFormDao extends DaoImpl<PlatFormPro, Integer> {
	 public List<PlatFormPro> list(WherePrams where){
	        return super.list(where);
	    }

}
