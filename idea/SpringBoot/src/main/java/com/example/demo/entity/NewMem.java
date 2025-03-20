package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@TableName("new_mem")
@Data

public class NewMem {

    @TableId (type = IdType.AUTO)
    private Integer id;

    private String name;
    private String college;
    private String stuClass;
    private String sex;
    private String phone;
    @JsonFormat(locale="zh",timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date birth;
    @JsonFormat(locale="zh",timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date petition;
    @JsonFormat(locale="zh",timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date active;
    @JsonFormat(locale="zh",timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date dev;
    @JsonFormat(locale="zh",timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date talk;
    @JsonFormat(locale="zh",timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date joinDate;
    private String notes;
    private Integer editor;
    @JsonFormat(locale="zh",timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
