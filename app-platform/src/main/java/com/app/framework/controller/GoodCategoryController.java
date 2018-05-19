package com.app.framework.controller;

import com.app.framework.base.BaseController;
import com.app.framework.core.utils.Log;
import com.app.framework.core.utils.LoggerFactory;
import com.app.framework.core.utils.PageResponse;
import com.app.framework.core.utils.Response;
import com.app.framework.model.GoodCategory;
import com.app.framework.service.GoodCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/goodCategory")
public class GoodCategoryController extends BaseController {
    private static final Log logger = LoggerFactory.getLogger(GoodCategoryController.class);
    @Autowired
    private GoodCategoryService goodCategoryService;

    @PostMapping("/save")
    public Response save(@RequestBody @Valid GoodCategory goodCategory) {
        try {
            if (null != goodCategory.getId()) {
                goodCategory.setUtime(new Date());
                goodCategory.setModifier(getUserId());
                goodCategoryService.update(goodCategory);
            } else {
                goodCategory.setCtime(new Date());
                goodCategory.setUtime(new Date());
                goodCategory.setCreator(getUserId());
                goodCategory.setModifier(getUserId());
                goodCategory.setStatus(0);
                goodCategoryService.save(goodCategory);
            }
            Response.success();
        } catch (Exception e) {
            logger.error("保存商品类别信息失败!", e);
        }
        return Response.error(1, "保存商品类别信息失败");
    }

    @GetMapping("/list")
    public PageResponse list(String searchKeyword,
                             @RequestParam(defaultValue = "1") Integer pageNo,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        try {
            return goodCategoryService.list(searchKeyword, pageNo, pageSize);
        } catch (Exception e) {
            logger.error("查询商品类别信息失败!", e);
        }
        return null;
    }

    @GetMapping("/findAll")
    public Response findAll() {
        try {
            return Response.success(goodCategoryService.findAll());
        } catch (Exception e) {
            logger.error("查询商品类别信息失败!", e);
        }
        return Response.error(1, "查询商品类别信息失败");
    }

    @GetMapping("/delete")
    public Response delete(@RequestParam(value = "ids[]") Long[] ids) {
        try {
            goodCategoryService.deleteByIds(ids);
            Response.success();
        } catch (Exception e) {
            logger.error("删除商品类别信息失败!", e);
        }
        return Response.error(1, "删除商品类别信息失败");
    }
}
