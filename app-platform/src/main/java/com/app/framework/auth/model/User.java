package com.app.framework.auth.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by yangyijun on 2018/4/13.
 */
@Entity
@Table(name = "ts_user")
@Data
public class User extends BaseEntity {
    private String userName;
    private String name;
    private String password;
    private String email;
    private String phone;
    private Date lastLogin;
}
