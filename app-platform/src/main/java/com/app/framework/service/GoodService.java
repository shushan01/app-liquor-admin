package com.app.framework.service;

import com.app.framework.auth.service.BaseService;
import com.app.framework.core.utils.PageResponse;
import com.app.framework.dao.GoodDao;
import com.app.framework.model.Good;
import com.app.framework.model.GoodCategory;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodService extends BaseService<Good> {
    private GoodDao goodDao;

    @Autowired
    public GoodService(GoodDao goodDao) {
        super(goodDao);
        this.goodDao = goodDao;
    }

    public PageResponse list(String name, int pageNo, int pageSize) {
        Page<GoodCategory> page = PageHelper.startPage(pageNo, pageSize);
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        List<Good> list = goodDao.findByName(map);
        return PageResponse.success(list, page.getTotal());
    }

    public Good detailById(Long id) {
        return goodDao.detailById(id);
    }
}
