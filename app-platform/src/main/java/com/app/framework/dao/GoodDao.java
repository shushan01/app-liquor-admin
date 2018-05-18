package com.app.framework.dao;

import com.app.framework.core.utils.BaseDao;
import com.app.framework.model.Good;

import java.util.List;
import java.util.Map;

public interface GoodDao extends BaseDao<Good> {
    List<Good> findByName(Map<String, String> map);
}
