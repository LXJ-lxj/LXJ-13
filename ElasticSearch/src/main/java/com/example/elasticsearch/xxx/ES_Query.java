package com.example.elasticsearch.xxx;


import sun.com.didi.entity.Auth;

import java.util.List;



public interface ES_Query {

    void saveEntity(Auth auth);



    void saveEntity(List<Auth> entityList);



    List<Auth> searchEntity(String searchContent);

}
