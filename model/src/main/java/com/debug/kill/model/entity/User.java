package com.debug.kill.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
/**
 * 用户信息表
 */
public class User {

    private Integer id;
    //用户名
    private String userName;
    //密码
    private String password;
    //手机号
    private String phone;
    //邮箱
    private String email;
    //是否有效(1=是；0=否)
    private Byte isActive;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
}