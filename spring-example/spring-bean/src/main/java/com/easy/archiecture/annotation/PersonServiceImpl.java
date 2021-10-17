package com.easy.archiecture.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
//@Named
//@Controller
//@Repository
public class PersonServiceImpl {
//
//    @Autowired
//    private Company company;


    public void printName() {
        System.out.println("abc");
    }
}
