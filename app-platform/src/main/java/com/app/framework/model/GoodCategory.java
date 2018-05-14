package com.app.framework.model;

import com.app.framework.auth.model.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tb_good_category")
public class GoodCategory extends BaseEntity {
    private Long parentId;
    @Transient
    private String parentName;
    @NotNull(message = "商品类别名称不能为空！")
    private String name;
    private String description;
}