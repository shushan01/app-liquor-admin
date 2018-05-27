package com.app.framework.controller;

import com.app.framework.base.BaseController;
import com.app.framework.core.file.FileUtils;
import com.app.framework.core.utils.*;
import com.app.framework.model.Good;
import com.app.framework.model.Picture;
import com.app.framework.service.GoodService;
import com.app.framework.service.PictureService;
import com.app.framework.vo.GoodDetailVo;
import com.app.framework.vo.GoodDetailVo.PictureVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/good")
public class GoodController extends BaseController {
    private static final Log logger = LoggerFactory.getLogger(GoodController.class);
    private static final String FOLDER_SEPARATE = "/";
    @Autowired
    private GoodService goodService;
    @Autowired
    private PictureService pictureService;

    @GetMapping("/save")
    public Response save(@Valid Good good) {
        try {
            buildGood(good);
            goodService.save(good);
            return Response.success(good.getId());
        } catch (Exception e) {
            logger.error("保存商品信息失败!", e);
        }
        return Response.error();

    }

    @GetMapping("/update")
    public Response update(@Valid Good good) {
        try {
            good.setUtime(new Date());
            good.setModifier(getUserId());
            goodService.update(good);
            return Response.success(good.getId());
        } catch (Exception e) {
            logger.error("更新商品信息失败!", e);
        }
        return Response.error();

    }

    @GetMapping("/recommend")
    public Response recommend(@RequestParam(value = "ids[]") Long[] ids) {
        try {
            updateRecommend(ids, 1);
            return Response.success();
        } catch (Exception e) {
            logger.error("推荐商品信息失败!", e);
        }
        return Response.error();

    }

    @GetMapping("/cancelRecommend")
    public Response cancelRecommend(@RequestParam(value = "ids[]") Long[] ids) {
        try {
            updateRecommend(ids, 0);
            return Response.success();
        } catch (Exception e) {
            logger.error("推荐商品信息失败!", e);
        }
        return Response.error();

    }

    private void updateRecommend(@RequestParam(value = "ids[]") Long[] ids, int i) {
        for (Long id : ids) {
            Good good = new Good();
            good.setId(id);
            good.setRecommend(i);
            goodService.update(good);
        }
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
            for (Long id : ids) {
                Picture picture = new Picture();
                picture.setOwnerId(id);
                picture.setType("good");
                pictureService.delete(picture);
                String filePath = FileUtils.getUploadRootPath() + FOLDER_SEPARATE + "good" + FOLDER_SEPARATE + id + FOLDER_SEPARATE;
                File dir = new File(filePath);
                if (!dir.exists()) {
                    return Response.error("路径不存在");
                }
                File[] positionDir = dir.listFiles();
                for (File position : positionDir) {
                    File[] imgs = position.listFiles();
                    for (File img : imgs) {
                        img.delete();
                    }
                    position.delete();
                }
                dir.delete();
            }
            return Response.success();
        } catch (Exception e) {
            logger.error("删除商品类别信息失败!", e);
        }
        return Response.error();
    }

    @GetMapping("/detail")
    public Response detail(@RequestParam Long id) {
        try {
            Good good = goodService.detailById(id);
            Picture picture = new Picture();
            picture.setOwnerId(id);
            picture.setType("good");
            List<Picture> pictureList = pictureService.findBy(picture);
            List<PictureVo> carouselPictures = new ArrayList<>();
            List<PictureVo> goodListPictures = new ArrayList<>();
            List<PictureVo> goodDetailPictures = new ArrayList<>();

            for (Picture p : pictureList) {
                PictureVo pictureVo = new PictureVo();
                pictureVo.setName(p.getName());
                pictureVo.setUrl(p.getUrl());
                if (p.getPosition() == 1) {
                    carouselPictures.add(pictureVo);
                } else if (p.getPosition() == 2) {
                    goodListPictures.add(pictureVo);
                } else {
                    goodDetailPictures.add(pictureVo);
                }
            }
            GoodDetailVo goodDetailVo = new GoodDetailVo();
            goodDetailVo.setGood(good);
            goodDetailVo.setCarouselPictures(carouselPictures);
            goodDetailVo.setGoodListPictures(goodListPictures);
            goodDetailVo.setGoodDetailPictures(goodDetailPictures);
            return Response.success(goodDetailVo);
        } catch (Exception e) {
            logger.error("查看商品详细信息失败!", e);
        }
        return Response.error();
    }
}

