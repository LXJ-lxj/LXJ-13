package com.sso.login.Controller;

import com.sso.login.enity.User;
import com.sso.login.utils.LoginCache;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

/*
*
* 页面跳转逻辑
* */
@Controller
@RequestMapping("/view")
public class ViewController {
    /*
     * 跳转到登录页面
     * */
    @GetMapping("/login")
    public String toLogin(@RequestParam(required = false,defaultValue = "") String target,
                          HttpSession session, @CookieValue(required = false,value = "TOKEN") Cookie cookie){
        if (StringUtils.isEmpty(target)){
            target="http://www.codeshop.com:9010";
        }
        if (cookie!=null){
            //        如果是已经登录的用户再次访问登录系统时，就要重定向
            String value = cookie.getValue();
            User user = LoginCache.loginUser.get(value);
            if (user!=null)
            {
                return "redirect:"+target;
            }
        }

        //否则重定向
        session.setAttribute("target",target);
        return "login";
    }
}
