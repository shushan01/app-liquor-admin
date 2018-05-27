package com.app.framework.vo;

import com.app.framework.model.Good;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GoodDetailVo {
    private Good good;
    private List<PictureVo> carouselPictures = new ArrayList<>();
    private List<PictureVo> goodListPictures = new ArrayList<>();
    private List<PictureVo> goodDetailPictures = new ArrayList<>();

    @Data
    public static class PictureVo {
        private String name;
        private String url;
    }
}
