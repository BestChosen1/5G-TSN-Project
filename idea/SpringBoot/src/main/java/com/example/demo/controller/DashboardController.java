package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.LoginUser;
import com.example.demo.commom.Result;
import com.example.demo.entity.Archive;
import com.example.demo.entity.Cmt;

import com.example.demo.entity.User;
import com.example.demo.mapper.ArchiveMapper;
import com.example.demo.mapper.CmtMapper;
import com.example.demo.mapper.UserMapper;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
    @Resource
    private UserMapper userMapper;
    @Resource
    private CmtMapper cmtMapper;
    @Resource
    private ArchiveMapper archiveMapper;
    @GetMapping
    public  Result<?> dashboardrecords(){
        int visitCount = LoginUser.getVisitCount();
        QueryWrapper<User> queryWrapper1=new QueryWrapper();
        int userCount = userMapper.selectCount(queryWrapper1);

        QueryWrapper<Cmt> queryWrapper2=new QueryWrapper();
        int cmtMapperCount = cmtMapper.selectCount(queryWrapper2);

        QueryWrapper<Archive> queryWrapper3=new QueryWrapper();
        queryWrapper3.eq("is_history", 1);
        int archiveCount = archiveMapper.selectCount(queryWrapper3);

        Map<String, Object> map = new HashMap<>();//键值对形式

        map.put("visitCount", visitCount);//放置visitCount到map中
        map.put("userCount", userCount);
        map.put("cmtCount", cmtMapperCount);
        map.put("archiveCount", archiveCount);
        return Result.success(map);
    }



}
