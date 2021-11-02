package com.li.service;

import com.hu.dao.UserDao;
import com.hu.entity.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 业务处理
 *
 * @see UserDao
 */

@Service
public class UserServiceImpl {

    @Autowired
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
        if (userByName.getName() != null && userByName.getName().equals(user.getName())) { //重名
            return false;
        }
        if (user.getId() == null || "".equals(user.getId())) {
            user.setId(UUID.randomUUID().toString()); //给id设置随机值
        }
        user.setPassword(DigestUtils.md5Hex(user.getPassword())); //MD5加密
        return userDao.insertUser(user) != 0; //是否注册成功  注册成功为true
    }

    public boolean update(User user) {

        User userByName = findUserByName(user.getName());
        //校验 用户名是否存在
        if (userByName.getName() == null) {
            return false;
        }

        user.setPassword(DigestUtils.md5Hex(user.getPassword())); //MD5加密
        return userDao.updatePassword(user) != 0; //是否修改成功
    }

}
