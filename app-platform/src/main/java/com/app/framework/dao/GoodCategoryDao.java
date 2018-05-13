package com.app.framework.dao;

import com.app.framework.core.utils.BaseDao;
import com.app.framework.model.GoodCategory;

import java.util.List;
import java.util.Map;

public interface GoodCategoryDao extends BaseDao<GoodCategory> {
    List<GoodCategory> findByName(Map<String, String> map);
}
