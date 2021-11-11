package com.example.elasticsearch.dao;

public class Auth {
    private int id;
    private String name;
    private String age;
    private String id_code;
    public static final String INDEX_NAME = "index_entity";
    public static final String TYPE = "tstype";

    public Auth(int id, String name, String age, String id_code) {
        this.id=id;
        this.name=name;
        this.age=age;
        this.id_code=id_code;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getId_code() {
        return id_code;
    }

    public void setId_code(String id_code) {
        this.id_code = id_code;
    }
}
