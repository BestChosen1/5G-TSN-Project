package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.demo.LoginUser;
import com.example.demo.commom.Result;
import com.example.demo.entity.Archive;
import com.example.demo.entity.Cmt;
import com.example.demo.entity.Nft;
import com.example.demo.entity.User;
import com.example.demo.mapper.NftMapper;
import com.example.demo.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/nftview")
public class NFTViewController {

    @Resource
    private NftMapper nftMapper;

    @GetMapping
    public Result<?> nftviewRecords(){
        QueryWrapper<Nft> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("rating");
        List<Nft> nfts = nftMapper.selectList(queryWrapper);
        return Result.success(nfts);

    }

    @GetMapping("/nftsearch")
    public Result<?> nftsearchRecords(@RequestParam(defaultValue = "") String search, @RequestParam(defaultValue = "") String key){
        QueryWrapper<Nft> queryWrapper = new QueryWrapper<>();
        if (search != null && !search.isEmpty()) {
            queryWrapper.eq("type", search);
        }
        if (key != null && !key.isEmpty()) {
            queryWrapper.like("name", key);
        }
        List<Nft> nfts = nftMapper.selectList(queryWrapper);
        return Result.success(nfts);
    }

    @GetMapping("/one")
    public Result<?> nftviewOne(String tokenid){
        QueryWrapper<Nft> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tokenid", tokenid);
        Nft nft = nftMapper.selectOne(queryWrapper);
        return Result.success(nft);

    }
}
