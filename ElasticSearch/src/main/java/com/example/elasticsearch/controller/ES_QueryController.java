package com.example.elasticsearch.controller;

import com.example.elasticsearch.dao.Auth;
import com.example.elasticsearch.service.ES_Query;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ES_QueryController {
    @Resource
   private ES_Query es_query;
    @RequestMapping(value="/save", method= RequestMethod.GET)
    public String start(){
        return "inforsave";
    }
    @PostMapping(value = "/save")
    public String inforsave(int id, String name,String age,String id_code) {

        System.out.println("save 接口");

        if(id>0 && StringUtils.isNotEmpty(name)) {

            Auth newAuth = new Auth(id,name,age,id_code);

            List<Auth> addList = new ArrayList<Auth>();

            addList.add(newAuth);

            es_query.saveEntity(addList);

            return "OK";

        }else {

            return "Bad input value";

        }

    }



    @RequestMapping(value="/search", method=RequestMethod.GET)

    public List<Auth> save(String name) {

        List<Auth> entityList = null;

        if(StringUtils.isNotEmpty(name)) {

            entityList = es_query.searchEntity(name);

        }

        return entityList;

    }

}

