package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.annotations.Update;

import java.util.Map;

@TableName("nft")
@Data
public class Nft {
    private String tokenid;
    private String name;
    private Integer userid;
    private String username;
    private String hash;
    private Integer value;
    private Integer slot;
    private String info;
    private String type;
    private Double price;
    private String picture;
    private Double rating;
    private String supervision;
    private String authority;
    private String qtype;

}