package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("cmt")
@Data
public class Cmt {
    @TableId (type = IdType.AUTO)
    private String cmtId;
    private String clgName;
    private String clgFullname;
    private String cmtName;
    private String cmtFullname;
    private Integer member;
    private Integer problem;
}
