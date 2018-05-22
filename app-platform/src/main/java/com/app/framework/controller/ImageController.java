package com.app.framework.controller;

import com.app.framework.base.BaseController;
import com.app.framework.core.file.FileUtils;
import com.app.framework.core.utils.Log;
import com.app.framework.core.utils.LoggerFactory;
import com.app.framework.core.utils.PageResponse;
import com.app.framework.core.utils.Response;
import com.app.framework.model.Picture;
import com.app.framework.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/image")
public class ImageController extends BaseController {
    private static final Log logger = LoggerFactory.getLogger(ImageController.class);
    private static final String FOLDER_SEPARATE = "/";

    @Autowired
    private PictureService pictureService;

    @PostMapping("/upload")
    public Response upload(HttpServletRequest request, @RequestParam("file") MultipartFile file, @RequestParam Long ownerId, @RequestParam String type) throws Exception {
        try {
            String url = buildDownloadUrl(request, ownerId, type, file.getOriginalFilename());
            String uploadPath = FOLDER_SEPARATE + type + FOLDER_SEPARATE + ownerId;
            if (!FileUtils.fileExists(file, uploadPath)) {
                FileUtils.upload(file, false, uploadPath);
                Picture picture = new Picture();
                picture.setOwnerId(ownerId);
                picture.setName(file.getOriginalFilename());
                picture.setType(type);
                picture.setCtime(new Date());
                picture.setUrl(url);
                picture.setCreator(getUserId());
                pictureService.save(picture);
            } else {
                throw new Exception("上传图片失败，图片已经存在，不能重复上传图片");
            }
            return PageResponse.success();
        } catch (Exception e) {
            logger.error("上传图片失败!", e);
            throw new Exception("上传图片失败，图片已经存在，不能重复上传图片");
        }
    }

    private String buildDownloadUrl(HttpServletRequest request, Long ownerId, String type, String fileName) {
        StringBuilder sb = new StringBuilder();
        String requestUrl = request.getRequestURL().toString();
        sb.append(requestUrl.substring(0, requestUrl.lastIndexOf("/")));
        sb.append("/get?type=");
        sb.append(type);
        sb.append("&");
        sb.append("ownerId=");
        sb.append(ownerId);
        sb.append("&");
        sb.append("fileName=");
        sb.append(fileName);
        return sb.toString();
    }

    @GetMapping("/get")
    public void load(String fileName, String type, Long ownerId, HttpServletResponse response) {
        ByteArrayOutputStream outputStream = null;
        ServletOutputStream responseOutputStream = null;
        try {
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            String imgFormat = getImageFormat(fileName);
            response.setContentType("image/" + imgFormat);
            String filePath = FileUtils.getUploadRootPath() + FOLDER_SEPARATE + type + FOLDER_SEPARATE + ownerId + FOLDER_SEPARATE + fileName;
            File img = new File(filePath);
            if (!img.exists()) {
                return;
            }
            outputStream = new ByteArrayOutputStream();
            BufferedImage image = ImageIO.read(img);
            ImageIO.write(image, imgFormat, outputStream);
            responseOutputStream = response.getOutputStream();
            responseOutputStream.write(outputStream.toByteArray());
            responseOutputStream.flush();
        } catch (IOException e) {
            logger.error("读取图片失败!", e);
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                logger.error("读取图片失败!", e);
            }
            try {
                responseOutputStream.close();
            } catch (IOException e) {
                logger.error("读取图片失败!", e);
            }
        }
    }

    @GetMapping("/delete")
    public Response delete(String fileName, String type, Long ownerId) {
        try {
            String filePath = FileUtils.getUploadRootPath() + FOLDER_SEPARATE + type + FOLDER_SEPARATE + ownerId + FOLDER_SEPARATE + fileName;
            File img = new File(filePath);
            if (!img.exists()) {
                return Response.error("图片不存在");
            }
            img.delete();
            Picture picture = new Picture();
            picture.setType(type);
            picture.setOwnerId(ownerId);
            picture.setName(fileName);
            pictureService.delete(pictureService.findOneBy(picture));
        } catch (Exception e) {
            logger.error("删除图片失败!", e);
        }
        return Response.success();
    }

    private String getImageFormat(String file) {
        return file.substring(file.lastIndexOf(".") + 1);
    }
}
