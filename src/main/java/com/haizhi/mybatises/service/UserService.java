package com.haizhi.mybatises.service;


import com.haizhi.mybatises.entity.User;

import java.util.List;

public interface UserService {

    User findOne(int id);

    List<User> matchQuery(String name);

    List<User> findList(int id);
}

