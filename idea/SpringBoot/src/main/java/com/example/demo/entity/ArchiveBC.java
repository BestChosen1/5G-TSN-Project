package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
//区块链数据转换中间类
@Data

public class ArchiveBC {

    @TableId (type = IdType.AUTO)
    private Integer no;
    private Integer id;
    private String name;
    private String college;
    private String stuClass;
    private String sex;
    private String cardId;
    private String nation;
    private String home;
    private String birth;
    private String joinDate;
    private String memId;
    private String phone;
    private Integer zhtj;
    private Integer volunteer;
    private Integer petition;
    private Integer problem;
    private Integer status;
    private String notes;
    private Integer editor;
    private String updateTime;
//    private Integer isHistory;
}
