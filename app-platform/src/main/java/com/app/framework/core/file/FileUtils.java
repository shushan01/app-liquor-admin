package com.app.framework.core.file;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by yangyijun on 2018/5/11.
 */
public final class FileUtils {

    public static boolean upload(MultipartFile file, boolean overwrite, String uploadPath) {
        uploadPath = getFileUploadPath(file, uploadPath);
        if (null == uploadPath)
            return false;

        String fileName = file.getOriginalFilename();
        File dest = new File(uploadPath + "/" + fileName);
        if (dest.exists()) {
            if (overwrite) {
                dest.delete();
            } else {
                //文件已经存在
                return false;
            }
        }

        if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static String getFileUploadPath(MultipartFile file, String uploadPath) {
        if (file.isEmpty()) {
            return null;
        }
        if (StringUtils.isBlank(uploadPath))
            uploadPath = getProjectRootPath() + "/upload";
        return uploadPath;
    }


    /**
     * 获取项目跟路径
     * 返回
     *
     * @return
     */
    private static String getProjectRootPath() {
        return System.getProperty("user.dir");
    }

    /**
     * 实现多文件上传
     */
    public static boolean multiUpload(List<MultipartFile> files, boolean overwrite, String uploadPath) {
        for (MultipartFile file : files) {
            upload(file, overwrite, uploadPath);
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));///Users/haizhi/yyj/workspace/app-liquor-admin/app-platform
    }
}
