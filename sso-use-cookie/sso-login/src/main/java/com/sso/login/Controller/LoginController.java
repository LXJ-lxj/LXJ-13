package com.sso.login.Controller;

import com.sso.login.enity.User;
import com.sso.login.utils.LoginCache;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/login")
public class LoginController {
    private static Set<User>dbUser;
    static {
        dbUser = new HashSet<>();
        dbUser.add(new User(0,"zhangsan","12345"));
        dbUser.add(new User(1,"lisi","123456"));
        dbUser.add(new User(2,"wangwu","1234567"));
    }
    /*
    * 跳转到登录页面
    * */
    @PostMapping
    public String doLogin(User user, HttpSession session, HttpServletResponse response){
        String target= (String) session.getAttribute("target");

        Optional<User> first = dbUser.stream().filter(dbUser -> dbUser.getUsername().equals(user.getUsername()) &&
                dbUser.getPassword().equals(user.getPassword())).findFirst();
//        判断用户是否登录
        if (first.isPresent()){
//            保存用户登录信息
            String token = UUID.randomUUID().toString();
            Cookie cookie=new Cookie("TOKEN",token);
            cookie.setDomain("codeshop.com");
            response.addCookie(cookie);
            LoginCache.loginUser.put(token,first.get());
        }else{
            //登录失败
            session.setAttribute("msg","用户名或密码错误");
            return "login";
        }


//        重定向到target地址
        return "redirect:"+target;
    }

    @GetMapping("info")
    @ResponseBody
    public ResponseEntity<User> getUserInfo(String token){
        if (!StringUtils.isEmpty(token)){
            User user = LoginCache.loginUser.get(token);
            return ResponseEntity.ok(user);
        }else
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
