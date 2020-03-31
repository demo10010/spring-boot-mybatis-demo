package com.haizhi.mybatises.controller;

//import com.haizhi.mybatises.conf.MyBatisSqlBean;
import com.haizhi.mybatises.entity.User;
import com.haizhi.mybatises.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
@Api(description = "mybatis demo控制层")
@RequestMapping("/testBoot")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("getUser/{id}")
    @ApiOperation(value = "根据id进行查询")
    public String getUser(@PathVariable int id) {
        if (userService.findOne(id) !=null){
            return userService.findOne(id).toString();
        }
        return "";
    }

    @GetMapping("getUsers/{id}")
    @ApiOperation(value = "根据id进行查询(多个)")
    public String getUsers(@PathVariable int id) {
        if (userService.findList(id) !=null){
            return userService.findOne(id).toString();
        }
        return Collections.EMPTY_LIST.toString();
    }

    @GetMapping("matchQuery/{name}")
    @ApiOperation(value = "根据id进行查询(多个)")// TODO  暂不支持
    public String matchQuery(@PathVariable String name ) {
        List<User> users = userService.matchQuery(name);
        if (users !=null){
            return users.toString();
        }
        return Collections.EMPTY_LIST.toString();
    }


    @GetMapping("test")
    @ApiOperation(value = "根据id进行查询")
    public String test() {
//        try {
//            myBatisSqlBean.mybatisStep();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return "";
    }
}
