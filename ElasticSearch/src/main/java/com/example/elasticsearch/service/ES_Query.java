package com.example.elasticsearch.service;

import com.example.elasticsearch.dao.Auth;

import java.util.List;



public interface ES_Query {

    void saveEntity(Auth auth);



    void saveEntity(List<Auth> entityList);



    List<Auth> searchEntity(String searchContent);

}
