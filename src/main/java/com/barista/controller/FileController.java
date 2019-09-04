package com.barista.controller;

import com.barista.result.Result;
import com.barista.util.FileUtil;
import com.barista.util.MailUtil;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Objects;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 文件上传下载
 *
 * @ClassName FileController
 * @Author zhaoth
 * @Date 2019/9/4 9:48
 * @Version 1.0
 */
@Controller
@RequestMapping("/file")
public class FileController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    @PostMapping("/upload")
    @ResponseBody
    public Result upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.fail("上传失败，请选择文件");
        }
        Result checkName = FileUtil.checkFileName(file.getOriginalFilename());
        if (!checkName.isSuccess()) {
            return checkName;
        }

        File result = FileUtil.upload(file);
        if (Objects.nonNull(result)) {
            return Result.success("上传成功");
        } else {
            return Result.fail("上传失败");
        }
    }

    @GetMapping("/download")
    public void download(String fileName, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        if (StringUtils.isBlank(fileName)) {
            return;
        }

        FileUtil.download(fileName, response);
    }

    @PostMapping("/email")
    @ResponseBody
    public Result sendEmail(String to, String title, String content, MultipartFile file) throws IOException, MessagingException {
        File tempFile = FileUtil.upload(file);
        if (Objects.nonNull(file)) {
            MailUtil.send(to, null, null, title, content, tempFile.getPath(), "UTF-8");
            tempFile.delete();
        } else {
            MailUtil.send(to, title, content, "UTF-8");
        }
        return Result.success("邮件已发送");
    }
}

