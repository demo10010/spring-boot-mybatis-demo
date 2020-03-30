package com.haizhi.mybatises.entity;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {
    private Integer id;
    private String userName;
    private String passWord;
    private String realName;
}
