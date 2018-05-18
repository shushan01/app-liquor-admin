package com.app.framework.controller;

import com.app.framework.core.utils.*;
import com.app.framework.dao.GoodDao;
import com.app.framework.model.Good;
import com.app.framework.model.GoodCategory;
import com.app.framework.service.GoodCategoryService;
import com.app.framework.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/good")
public class GoodController {
    private static final Log logger = LoggerFactory.getLogger(GoodController.class);
    @Autowired
    private GoodService goodService;

    @PostMapping("/save")
    public Response save(@RequestBody @Valid Good good) {
        buildGood(good);
        goodService.save(good);
        return Response.success(good);
    }

    private void buildGood(Good good) {
        good.setCode(CodeUtils.generate());
        good.setCreator(1l);
        good.setCtime(new Date());
        good.setModifier(1l);
        good.setUtime(new Date());
        good.setStatus(0);
        good.setActivityStatus(0);
        good.setClickCnt(0l);
        good.setSaleCnt(0l);
        good.setCollectCnt(0l);
        good.setRecommend(0);
        good.setCurrentPrice(new BigDecimal(0));
    }

    @PostMapping("/uploadPicture")
    public Response uploadPicture(@RequestParam("file") List<MultipartFile> files) {
        Response response = new Response();
        return response;
    }

    @GetMapping("/list")
    public PageResult<Good> list(String searchKeyword,
                                 @RequestParam(defaultValue = "1") Integer pageNo,
                                 @RequestParam(defaultValue = "10") Integer pageSize) {
        return goodService.list(searchKeyword, pageNo, pageSize);
    }

    @GetMapping("/delete")
    public Response delete(@RequestParam(value = "ids[]") Long[] ids) {
        try {
            goodService.deleteByIds(ids);
            return Response.success();
        } catch (Exception e) {
            logger.error("删除商品类别信息失败!", e);
        }
        return Response.error();
    }
}

