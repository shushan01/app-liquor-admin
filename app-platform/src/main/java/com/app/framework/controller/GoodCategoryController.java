package com.app.framework.controller;

import com.app.framework.core.utils.Log;
import com.app.framework.core.utils.LoggerFactory;
import com.app.framework.core.utils.PageResult;
import com.app.framework.core.utils.Response;
import com.app.framework.model.GoodCategory;
import com.app.framework.service.GoodCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/goodCategory")
public class GoodCategoryController {
    private static final Log logger = LoggerFactory.getLogger(GoodCategoryController.class);
    @Autowired
    private GoodCategoryService goodCategoryService;

    @PostMapping("/save")
    public Response save(@RequestBody GoodCategory goodCategory) {
        Response response = new Response();
        try {
            if (null != goodCategory.getId()) {
                goodCategory.setUtime(new Date());
                goodCategory.setModifier(1l);
                goodCategoryService.update(goodCategory);
            } else {
                goodCategory.setCtime(new Date());
                goodCategory.setUtime(new Date());
                goodCategory.setCreator(1l);
                goodCategory.setModifier(1l);
                goodCategory.setStatus(1);
                goodCategoryService.save(goodCategory);
            }
            response.setCode(0);
            response.setMsg("保存商品类别信息成功!");
        } catch (Exception e) {
            logger.error("保存商品类别信息失败!", e);
            response.setCode(1);
            response.setMsg("保存商品类别信息失败!");
        }
        return response;
    }

    @GetMapping("/list")
    public PageResult<GoodCategory> list(String searchKeyword, Integer pageNo, Integer pageSize) {
        try {
            return goodCategoryService.list(searchKeyword, pageNo, pageSize);
        } catch (Exception e) {
            logger.error("查询商品类别信息失败!", e);
        }
        return null;
    }

    @GetMapping("/findAll")
    public List<GoodCategory> findAll() {
        try {
            return goodCategoryService.findAll();
        } catch (Exception e) {
            logger.error("查询商品类别信息失败!", e);
        }
        return null;
    }

    @GetMapping("/delete")
    public Response delete(@RequestParam(value = "ids[]") Long[] ids) {
        Response response = new Response();
        try {
            goodCategoryService.deleteByIds(ids);
            response.setCode(0);
            response.setMsg("删除商品类别信息成功!");
        } catch (Exception e) {
            logger.error("删除商品类别信息失败!", e);
            response.setCode(1);
            response.setMsg("删除商品类别信息失败!");
        }
        return response;
    }
}
