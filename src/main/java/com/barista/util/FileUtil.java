package com.barista.util;

import com.barista.result.Result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

/**
 * 文件上传下载
 *
 * @ClassName FileUtil
 * @Author zhaoth
 * @Date 2019/9/4 11:07
 * @Version 1.0
 */
public class FileUtil {
    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    private static String UPLOAD_DEST_PATH;

    private static String DOWNLOAD_SOURCE_PATH;

    static {
        Properties properties = new Properties();
        try {
            properties.load(FileUtil.class.getClassLoader().getResourceAsStream("file.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        UPLOAD_DEST_PATH = properties.getProperty("file.upload.path");
        DOWNLOAD_SOURCE_PATH = properties.getProperty("file.download.path");
    }

    public static Result checkFileName(String fileName) {
        if (fileName.contains("---")) {
            return Result.fail("文件名不能包含---");
        }
        return Result.success(true);
    }

    public static File upload( MultipartFile file) {
        String fileName = file.getOriginalFilename();
        File path = new File(UPLOAD_DEST_PATH);
        if (!path.exists()) {
            boolean success = path.mkdirs();
            if (!success) {
                return null;
            }
        }

        File dest = new File(path.getPath(), System.currentTimeMillis() + "---" + fileName);
        try {
            file.transferTo(dest);
            logger.info("上传成功：" + dest.getAbsolutePath());
            return dest;
        } catch (IOException e) {
            logger.error(e.toString(), e);
        }
        return null;
    }

    public static boolean download(String fileName, HttpServletResponse response) throws UnsupportedEncodingException {
        //设置文件路径
        File file = new File(DOWNLOAD_SOURCE_PATH, fileName);
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));
        try (//打开本地文件流
             InputStream inputStream = new FileInputStream(file);
             //激活下载操作
             OutputStream os = response.getOutputStream();) {

            //循环写入输出流
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }

            return true;
        } catch (IOException e) {
            logger.error("文件下载失败", e);
        }
        return false;
    }
}
