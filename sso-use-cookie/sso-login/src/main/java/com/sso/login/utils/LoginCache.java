package com.sso.login.utils;

import com.sso.login.enity.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class LoginCache {
    public static Map<String, User> loginUser=new HashMap<>();
}
