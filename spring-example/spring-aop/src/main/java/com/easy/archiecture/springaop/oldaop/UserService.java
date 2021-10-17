package com.easy.archiecture.springaop.oldaop;


public interface UserService {
    UserTest createUser(String firstName, String lastName, int age);

    UserTest queryUser();
}
