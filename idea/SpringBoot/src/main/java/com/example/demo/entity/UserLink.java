package com.example.demo.entity;

import lombok.Data;

@Data
public class UserLink {
    private Integer id;
    private String username;
    private Integer role;
    private String publickeyLink;
    private String privatekeyLink;
}
