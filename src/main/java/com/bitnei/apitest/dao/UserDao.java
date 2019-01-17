package com.bitnei.apitest.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bitnei.apitest.dao.impl.DaoImpl;
import com.bitnei.apitest.pro.WherePrams;
import com.bitnei.apitest.pro.datasendpro.PlatFormPro;

/**
* 一个基于公用Dao的UserDao
*/
@Repository
public class UserDao extends DaoImpl<PlatFormPro, Integer> {

    //在这里，这个UserDao本身拥有了Dao的一些常用的增删改查方法。也可以重写或者增加一些特殊方法。
//    public List<PlatFormPro> listGirl(){
//
//        //获取用户中的小姑娘，瞎写的。。
//    }

    //再比如说我要重写一下list()这个方法
    public List<PlatFormPro> list(WherePrams where){

        return super.list(where);
    }
}
