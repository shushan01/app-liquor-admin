package com.app.framework.auth.model;

import com.app.framework.base.BaseObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by yangyijun on 2018/4/13.
 */
@MappedSuperclass
@Data
public class BaseEntity extends BaseObject implements Serializable {
    private static final long serialVersionUID = -1101683279680617696L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected Long creator;
    /**
     * 更新时间  用户可以点击更新，保存最新更新的时间。
     **/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    protected Date ctime;
    protected Long modifier;
    /**
     * 更新时间  用户可以点击更新，保存最新更新的时间。
     **/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    protected Date utime;
    private Integer status;
}
