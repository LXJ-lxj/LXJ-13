package com.ease.architecture.service;

import com.ease.architecture.dao.UserDao;
import com.ease.architecture.entity.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
//业务层
@Service
public class UserServiceImpl {

    @Autowired//因为UserDao已经被spring管理起来了，所以直接用标注的方式
    private UserDao userDao;

    public User findUserById(String id) {
        return userDao.findByUserId(id);
    }

    public User findUserByName(String username) {
        return userDao.findByName(username);
    }

    public User findUserByNameAndPassword(String username, String password) {
        return userDao.findByNameAndPassword(username, password);
    }

//    public User login(String username, String password) {
//        return findUserByNameAndPassword(username, password);
//    }

    public boolean register(User user) {
        User userByName = findUserByName(user.getName());
        if (userByName.getName() != null && userByName.getName().equals(user.getName())) {
            return true;
        }
        if (user.getId() == null || user.getId().equals("")) {
            user.setId(UUID.randomUUID().toString());
        }
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        return userDao.insertUser(user) != 0;
    }
}
