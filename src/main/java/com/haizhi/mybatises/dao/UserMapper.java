package com.haizhi.mybatises.dao;

import com.haizhi.mybatises.entity.User;
import com.haizhi.mybatises.annotation.EsMapper;
import org.springframework.stereotype.Repository;

@Repository
@EsMapper
public interface UserMapper {

    User Sel(int id);
}
