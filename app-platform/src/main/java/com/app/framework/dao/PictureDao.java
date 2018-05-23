package com.app.framework.dao;

import com.app.framework.core.utils.BaseDao;
import com.app.framework.model.Picture;
import org.apache.ibatis.annotations.Delete;

public interface PictureDao extends BaseDao<Picture> {
    @Delete(value = "delete from tb_picture where owner_id in #{ownerIds}")
    void deleteByOwnerIds(Long[] ownerIds);
}
