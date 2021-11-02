package com.li.controller;

import com.hu.entity.User;
import com.hu.service.UserServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "test", method = RequestMethod.GET)
    @ResponseBody //返回字符串信息
    public String test() {
        return "test aaaaa";
    }

    @RequestMapping(value = "registerPage", method = RequestMethod.GET)
    public String registerPage() {
        return "register";
    }

    @RequestMapping(value = "updatePage", method = RequestMethod.GET)
    public String updatePage() {
        return "update";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST,produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String login(String name, String password) {
        User user = userService.findUserByNameAndPassword(name, DigestUtils.md5Hex(password));
        if (user == null || user.getName() == null) {
            return "用户不存在或用户名、密码错误";
        }
        return "hello," + user.getName();
    }

    @RequestMapping(value = "register", method = RequestMethod.POST,produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String register(User user) {
        boolean register = userService.register(user);
        if (register) {
            return "hello," + user.getName();
        }
        return "注册失败!";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST,produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String updatePassword(User user) {
        boolean update = userService.update(user);
        if (update) {
            return "密码修改成功";
        }
        return "密码修改失败!";
    }

}
