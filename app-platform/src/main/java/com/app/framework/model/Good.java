package com.app.framework.model;

import com.app.framework.auth.model.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "tb_good")
public class Good extends BaseEntity {
    private String name;
    private String code;
    private Long categoryId;
    @Transient
    private String categoryName;
    private BigDecimal price;
    private Long clickCnt;
    private Long collectCnt;
    private Long saleCnt;
    private Integer recommend;
    private Double weight;
    private Integer activityStatus;
    private BigDecimal currentPrice;
    private BigDecimal emsFreight;
    private BigDecimal expressFreight;
    private BigDecimal mailFreight;
}
