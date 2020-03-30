package com.haizhi.mybatises.controller;

//import com.haizhi.mybatises.conf.MyBatisSqlBean;
import com.haizhi.mybatises.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@Api(description = "mybatis demo控制层")
@RequestMapping("/testBoot")
public class UserController {

    @Autowired
    private UserService userService;

//    @Autowired
//    private MyBatisSqlBean myBatisSqlBean;

    @GetMapping("getUser/{id}")
    @ApiOperation(value = "根据id进行查询")
    public String GetUser(@PathVariable int id) {
        if (userService.Sel(id) !=null){
            return userService.Sel(id).toString();
        }
        return "null";
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
