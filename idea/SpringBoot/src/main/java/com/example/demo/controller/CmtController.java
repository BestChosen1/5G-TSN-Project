package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.LoginUser;
import com.example.demo.commom.Result;
import com.example.demo.entity.Cmt;
import com.example.demo.entity.Nft;
import com.example.demo.entity.User;
import com.example.demo.mapper.CmtMapper;
import com.example.demo.mapper.NftMapper;
import com.example.demo.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/cmt")
public class CmtController {
    @Resource
    CmtMapper cmtMapper;
    @Resource
    NftMapper nftMapper;


    @PutMapping
    public  Result<?> update(@RequestBody Nft nft){
        System.out.println(nft);
        nftMapper.updateById(nft);
        return Result.success();
    }
    @PostMapping("/add")
    public Result<?> add(@RequestBody Nft nft){
        Nft res = nftMapper.selectOne(Wrappers.<Nft>lambdaQuery().eq(Nft::getTokenid,nft.getTokenid()));
        if(res != null)
        {
            return Result.error("-1","数据资产已存在");
        }
        nftMapper.insert(nft);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id){
        System.out.println(id);
        cmtMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping("/cmtsearch")
    public Result<?> findPage2(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search1,
                               @RequestParam(defaultValue = "") String search2,
                               @RequestParam(defaultValue = "") String search3,
                               @RequestParam(defaultValue = "") String search4){
        LambdaQueryWrapper<Nft> wrappers = Wrappers.<Nft>lambdaQuery();
        if(StringUtils.isNotBlank(search1)){
            wrappers.like(Nft::getTokenid,search1);
        }
        if(StringUtils.isNotBlank(search2)){
            wrappers.like(Nft::getType,search2);
        }
        if(StringUtils.isNotBlank(search3)){
            wrappers.like(Nft::getUsername,search3);
        }
        if(StringUtils.isNotBlank(search4)){
            wrappers.like(Nft::getName,search4);
        }

        Page<Nft> nftPage =nftMapper.selectPage(new Page<>(pageNum,pageSize), wrappers);
        return Result.success(nftPage);
    }
}
