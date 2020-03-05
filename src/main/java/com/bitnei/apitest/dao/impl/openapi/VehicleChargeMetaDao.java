package com.bitnei.apitest.dao.impl.openapi;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bitnei.apitest.annotation.pro.DataSource;
import com.bitnei.apitest.dao.impl.DaoImpl;
import com.bitnei.apitest.pro.WherePrams;
import com.bitnei.apitest.pro.datasendpro.TaskPro;
import com.bitnei.apitest.pro.openapi.VehicleChargeMetaPro;

/** 
* @author 作者 hangang
* @version 创建时间：2020年3月5日 上午10:49:35 
* 类说明 
*/
@Repository(value="vehiclechargemetadao")
@DataSource("apiDataSource")
public class VehicleChargeMetaDao extends DaoImpl<VehicleChargeMetaPro, Integer>{
	public List<VehicleChargeMetaPro> list(WherePrams where){
        return super.list(where);
    }
 
	public boolean isExist(WherePrams where){
        return super.isExist(where);
    }	
 
	public int addLocal(VehicleChargeMetaPro pro){
     return super.addLocal(pro);
 }	
 
	public int del(WherePrams where) {
	 return super.del(where);
 }

}
