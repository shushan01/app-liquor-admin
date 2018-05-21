package com.app.framework.controller;

import com.app.framework.base.BaseController;
import com.app.framework.core.file.FileUtils;
import com.app.framework.core.utils.*;
import com.app.framework.model.Good;
import com.app.framework.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/good")
public class GoodController extends BaseController {
    private static final Log logger = LoggerFactory.getLogger(GoodController.class);
    @Autowired
    private GoodService goodService;

    @GetMapping("/save")
    public Response save(@Valid Good good) {
        try {
            buildGood(good);
            goodService.save(good);
            return Response.success(good.getCode());
        } catch (Exception e) {
            logger.error("保存商品信息失败!", e);
        }
        return Response.error();

    }

    private void buildGood(Good good) {
        good.setCode(CodeUtils.generate());
        good.setCreator(getUserId());
        good.setCtime(new Date());
        good.setModifier(getUserId());
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
    public Response uploadPicture(@RequestParam("file") List<MultipartFile> files, @RequestParam String goodCode) throws Exception {
        try {
            FileUtils.multiUpload(files, true, "/good/" + goodCode);
            return PageResponse.success();
        } catch (Exception e) {
            logger.error("上传图片失败!", e);
        }
        return PageResponse.error();
    }

    @GetMapping("/list")
    public Response list(String searchKeyword,
                         @RequestParam(defaultValue = "1") Integer pageNo,
                         @RequestParam(defaultValue = "10") Integer pageSize) {
        try {
            return goodService.list(searchKeyword, pageNo, pageSize);
        } catch (Exception e) {
            logger.error("查询商品信息失败!", e);
        }
        return PageResponse.error();
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

