package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.commom.Result;
import com.example.demo.entity.NewMem;
import com.example.demo.mapper.NewMemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/newMem")
public class NewMemController {
    @Resource
    NewMemMapper newMemMapper;
    @Autowired
    private ConfigurationPropertiesAutoConfiguration configurationPropertiesAutoConfiguration;

    @PutMapping
    public  Result<?> update(@RequestBody NewMem newMem){
        System.out.println(newMem);
        newMemMapper.updateById(newMem);
        return Result.success();
    }
    @PostMapping("/add")
    public Result<?> add(@RequestBody NewMem newMem){
        NewMem res = newMemMapper.selectOne(Wrappers.<NewMem>lambdaQuery().eq(NewMem::getId,newMem.getId()));
        if(res != null)
        {
            return Result.error("-1","该学号已存在");
        }
        newMemMapper.insert(newMem);
        return Result.success();
    }
    @PostMapping("/deleteBatch")
    public  Result<?> deleteBatch(@RequestBody List<Integer> ids){
        newMemMapper.deleteBatchIds(ids);
        return Result.success();
    }
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id){
        System.out.println(id);
        newMemMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping("/newMemsearch")
    public Result<?> find(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search1,
                               @RequestParam(defaultValue = "") String search2,
                               @RequestParam(defaultValue = "") String search3,
                               @RequestParam(defaultValue = "") String search4
                                 )
    {

        LambdaQueryWrapper<NewMem> wrappers = Wrappers.<NewMem>lambdaQuery();
        System.out.println(123+search1);
        if(StringUtils.isNotBlank(search1)){
            wrappers.eq(NewMem::getCollege,search1);
        }
        if(StringUtils.isNotBlank(search2)){
            wrappers.like(NewMem::getStuClass,search2);
        }
        if(StringUtils.isNotBlank(search3)){
            wrappers.like(NewMem::getId,search3);
        }
        if(StringUtils.isNotBlank(search4)){
            wrappers.like(NewMem::getName,search4);
        }


        Page<NewMem> newMemPage =newMemMapper.selectPage(new Page<>(pageNum,pageSize), wrappers);
//        System.out.println(wrappers.select());
        return Result.success(newMemPage);
    }

}
