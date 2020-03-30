package com.haizhi.mybatises.dao;

import com.haizhi.mybatises.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    User Sel(int id);
}
