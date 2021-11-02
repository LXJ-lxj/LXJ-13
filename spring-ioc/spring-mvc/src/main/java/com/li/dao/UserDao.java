package com.li.dao;

import com.hu.entity.User;

import com.hu.redis.RedisOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据处理
 */

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RedisOperation redisOperation;

    public User findByUserId(String id) {
        final User user = new User();

        String sql = "SELECT * FROM user WHERE id=?";
        jdbcTemplate.query(sql, new Object[]{id}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setId(resultSet.getString(1));
                user.setName(resultSet.getString(2));
            }
        });

        return user;
    }


    public User findByName(String name) {
        final User user = new User();

        String sql = "SELECT * FROM user WHERE name=?";
        jdbcTemplate.query(sql, new Object[]{name}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setId(resultSet.getString(1));
                user.setName(resultSet.getString(2));
            }
        });

        return user;
    }

    public User findByNameAndPassword(String name, String password) {

        final User user = new User();

        redisOperation.init();
        boolean select = redisOperation.select(name, password);

        if (select) {
            user.setName(name);
            user.setPassword(password);
        } else {
            String sql = "SELECT * FROM user WHERE name=?AND password=?";
            jdbcTemplate.query(sql, new Object[]{name, password}, new RowCallbackHandler() {
                @Override
                public void processRow(ResultSet resultSet) throws SQLException {
                    user.setId(resultSet.getString(1));
                    user.setName(resultSet.getString(2));
                    user.setPassword(resultSet.getString(3));
                }
            });

            //只有当传进来的用户名和密码能与数据库中的用户名和密码一致时,才把信息加入缓存中
            if (name.equals(user.getName()) && password.equals(user.getPassword())) {
                redisOperation.insert(name, password);
            }
        }

        return user;
    }

    public int insertUser(User user) {
        String sql = "INSERT INTO user (id,name,password) VALUES (?,?,?)";
        return jdbcTemplate.update(sql, user.getId(), user.getName(), user.getPassword());
    }

    public int updatePassword(User user) {

        redisOperation.init();
        boolean select = redisOperation.select(user.getName());

        if (select) {
            redisOperation.delete(user.getName());
        }

        String sql = "UPDATE user SET password=? WHERE name=?";
        return jdbcTemplate.update(sql, user.getPassword(), user.getName());
    }

}
