package com.haizhi.mybatises.service;

import com.haizhi.mybatises.dao.UserMapper;
import com.haizhi.mybatises.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    public User Sel(int id){
        User sel = null;
        try {
             sel = userMapper.Sel(id);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return sel;
    }
}
