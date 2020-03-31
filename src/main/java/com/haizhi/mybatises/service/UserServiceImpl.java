package com.haizhi.mybatises.service;

import com.haizhi.mybatises.dao.UserMapper;
import com.haizhi.mybatises.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User findOne(int id) {
        User user = null;
        try {
            user = userMapper.findOne(id);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return user;
    }

    @Override
    public List<User> matchQuery(String name) {
        List<User> users = null;
        try {
            users = userMapper.matchQuery(name);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return users;
    }

    @Override
    public List<User> findList(int id) {
        List<User> sel = null;
        try {
            sel = userMapper.findList(id);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return sel;
    }
}
