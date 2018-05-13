package com.app.framework.service;

import com.app.framework.auth.service.BaseService;
import com.app.framework.core.utils.PageResult;
import com.app.framework.dao.GoodCategoryDao;
import com.app.framework.model.GoodCategory;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodCategoryService extends BaseService<GoodCategory> {
    private GoodCategoryDao goodCategoryDao;

    @Autowired
    public GoodCategoryService(GoodCategoryDao goodCategoryDao) {
        super(goodCategoryDao);
        this.goodCategoryDao = goodCategoryDao;
    }

    public PageResult<GoodCategory> list(String name, int pageNo, int pageSize) {
        Page<GoodCategory> page = PageHelper.startPage(pageNo, pageSize);
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        List<GoodCategory> list = goodCategoryDao.findByName(map);
        return new PageResult<GoodCategory>(pageNo, pageSize, page.getTotal(), list);
    }
}
