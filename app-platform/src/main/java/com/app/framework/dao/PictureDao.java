package com.app.framework.dao;

import com.app.framework.core.utils.BaseDao;
import com.app.framework.model.Picture;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

public interface PictureDao extends BaseDao<Picture> {
    @Delete(value = "delete from tb_picture where owner_id in #{ownerIds}")
    void deleteByOwnerIds(@Param("ownerIds") Long[] ownerIds);
}
