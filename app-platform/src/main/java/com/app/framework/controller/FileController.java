package com.app.framework.controller;

import com.app.framework.core.file.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by yangyijun on 2018/5/11.
 */
@Controller
public class FileController {
    @Value("${fileUpload.path}")
    private String uploadPath;

    /*
    * 获取file.html页面
    */
    @RequestMapping("file")
    public String file() {
        return "/file";
    }

    /*
     * 获取multifile.html页面
     */
    @RequestMapping("multifile")
    public String multifile() {
        return "/multifile";
    }

    @RequestMapping("/upload")
    @ResponseBody
    public String fileUpload(@RequestParam("fileName") MultipartFile file) {
        FileUtils.upload(file, false, uploadPath);
        return "true";
    }

    /**
     * 实现多文件上传
     * public @ResponseBody String multifileUpload()
     */
    @RequestMapping(value = "/multiUpload", method = RequestMethod.POST)
    @ResponseBody
    public String multiUpload(@RequestParam("fileName") List<MultipartFile> files) {
        FileUtils.multiUpload(files, false, null);
        return "true";
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    @ResponseBody
    public String download(HttpServletResponse response) {
        FileUtils.download(response, "/Users/haizhi/yyj/workspace/app-liquor-admin/app-platform/logs/info-2018-05-10.log");
        return "true";
    }

}
