package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("userkey")
@Data
public class Userkey {
    @TableId (type = IdType.AUTO)
    private Integer id;
    private String publickey;
    private String privatekey;
    private String countaddress;
    // private String cmtId;

}
