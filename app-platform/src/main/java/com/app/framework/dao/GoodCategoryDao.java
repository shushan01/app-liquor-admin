package com.app.framework.dao;

import com.app.framework.core.utils.BaseDao;
import com.app.framework.model.GoodCategory;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GoodCategoryDao extends BaseDao<GoodCategory> {
    @Select("select id,name,description,status,utime,ctime,creator,modifer from tb_good_category where name like '%#{name}%'")
    List<GoodCategory> findByName(@Param("name") String name);
}
