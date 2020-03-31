package com.haizhi.mybatises.dao;

import com.haizhi.mybatises.entity.User;
import com.haizhi.mybatises.annotation.EsMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EsMapper
public interface UserMapper {

    User findOne(int id);

    List<User> matchQuery(String name);

    List<User> findList(int id);
}
