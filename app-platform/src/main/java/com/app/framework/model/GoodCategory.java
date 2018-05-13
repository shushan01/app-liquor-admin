package com.app.framework.model;

import com.app.framework.auth.model.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tb_good_category")
public class GoodCategory extends BaseEntity {
    private Long parentId;
    private String name;
    private String description;
}