package com.app.framework.core.file;

import com.app.framework.core.utils.Log;
import com.app.framework.core.utils.LoggerFactory;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangyijun on 2018/5/11.
 */
public final class FileUtils {
    private static final Log logger = LoggerFactory.getLogger(FileUtils.class);

    /**
     * 返回上传文件的全路径
     *
     * @param file
     * @param overwrite
     * @param uploadPath
     * @return
     */
    public static Map<String, String> upload(MultipartFile file, boolean overwrite, String uploadPath) {
        uploadPath = getFileUploadPath(file, uploadPath);
        if (null == uploadPath)
            return null;

        String fileName = file.getOriginalFilename();
        String uploadFilePath = uploadPath + "/" + fileName;
        File dest = new File(uploadFilePath);
        if (dest.exists()) {
            if (overwrite) {
                dest.delete();
            } else {
                //文件已经存在
                logger.error("文件已经存在");
                return null;
            }
        }

        File parentFile = dest.getParentFile();
        if (!parentFile.exists()) { //判断文件父目录是否存在
            parentFile.mkdirs();
        }
        try {
            file.transferTo(dest); //保存文件
            Map<String, String> result = new HashMap<>();
            result.put(fileName, uploadFilePath);
            return result;
        } catch (IllegalStateException e) {
            logger.error("上传文件【{0}】异常", e, fileName);
        } catch (IOException e) {
            logger.error("上传文件【{0}】异常", e, fileName);
        }
        return null;
    }

    public static boolean fileExists(MultipartFile file, String uploadPath) {
        uploadPath = getFileUploadPath(file, uploadPath);
        String fileName = file.getOriginalFilename();
        String uploadFilePath = uploadPath + "/" + fileName;
        File dest = new File(uploadFilePath);
        if (dest.exists()) {
            return true;
        } else {
            return false;
        }
    }

    private static String getFileUploadPath(MultipartFile file, String uploadPath) {
        if (file.isEmpty()) {
            logger.warn("无效的文件");
            return null;
        }
        if (StringUtils.isBlank(uploadPath)) {
            uploadPath = getProjectRootPath() + "/upload";
        } else {
            uploadPath = getProjectRootPath() + "/upload" + uploadPath;
        }
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
    public static Map<String, String> multiUpload(List<MultipartFile> files, boolean overwrite, String uploadPath) {
        Map<String, String> result = new HashMap<>();
        for (MultipartFile file : files) {
            result.putAll(upload(file, overwrite, uploadPath));
        }
        return result;
    }

    public static String download(HttpServletResponse response, String filePath) {
        File file = new File(filePath);
        if (file.exists()) { //判断文件父目录是否存在
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" + file.getName());

            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                logger.error("下载文件【{}】异常", e, filePath);
            } finally {
                try {
                    bis.close();
                } catch (IOException e) {
                    logger.error("下载文件【{}】关闭文件流异常", e, filePath);
                }
                try {
                    fis.close();
                } catch (IOException e) {
                    logger.error("下载文件【{}】关闭文件流异常", e, filePath);
                }
            }
        }
        return null;
    }

    public static String channelDownload(String filePath, String savePath) {
        File file = new File(filePath);
        if (file.exists()) { //判断文件父目录是否存在
            FileInputStream fis = null;
            FileOutputStream fos = null;
            FileChannel inChannel = null;
            FileChannel outChannel = null;
            try {
                fis = new FileInputStream(filePath);
                fos = new FileOutputStream(savePath);
                inChannel = fis.getChannel();
                outChannel = fos.getChannel();
                outChannel.transferFrom(inChannel, 0, file.length());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    inChannel.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                try {
                    outChannel.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                try {
                    fis.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                try {
                    fos.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));///Users/haizhi/yyj/workspace/app-liquor-admin/app-platform
    }
}
