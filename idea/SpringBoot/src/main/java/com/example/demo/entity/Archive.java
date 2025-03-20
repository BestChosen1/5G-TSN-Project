package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@TableName("archive")
@Data

public class Archive {

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
    @JsonFormat(locale="zh",timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date birth;
    @JsonFormat(locale="zh",timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date joinDate;

    private String memId;
    private String phone;

    private Integer zhtj;
    private Integer volunteer;
    private Integer petition;
    private Integer problem;
    private Integer status;

    private String notes;

    private Integer editor;
    @JsonFormat(locale="zh",timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private Integer isHistory;

//    @Override
    public String myString() {

        if (this.birth == null){
            this.birth = Date.from(LocalDate.parse("1970-1-1", DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        if (this.joinDate == null){
            this.joinDate = Date.from(LocalDate.parse("1970-1-1", DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay(ZoneId.systemDefault()).toInstant());
        }

        return "{'no':" + this.no
                + ",'id':" + this.id
                + ",'name':" + this.name
                + ",'college':" + this.college
                + ",'stuClass':" + this.stuClass
                + ",'sex':" + this.sex
                + ",'cardId':" + this.cardId
                + ",'nation':" + this.nation
                + ",'home':" + this.home
                + ",'birth':" + new java.text.SimpleDateFormat("yyyy-MM-dd").format(this.birth)

                + ",'joinDate':" + new java.text.SimpleDateFormat("yyyy-MM-dd").format(this.joinDate)
                + ",'memId':" + this.memId
                + ",'phone':" + this.phone
                + ",'zhtj':" + this.zhtj
                + ",'volunteer':" + this.volunteer
                + ",'petition':" + this.petition
                + ",'problem':" + this.problem
                + ",'status':" + this.status
                + ",'notes':" + this.notes
                + ",'editor':" + this.editor
                + ",'updateTime':'" + new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.updateTime)
                +"'}";

    }

}
