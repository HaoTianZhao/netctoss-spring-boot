package com.barista.config;

import com.barista.result.Result;
import com.barista.result.ResultCode;

import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;

/**
 * 错误处理
 *
 * @ClassName ExceptionConfig
 * @Author zhaoth
 * @Date 2019/9/3 17:39
 * @Version 1.0
 */
@Configuration
public class ExceptionConfig {
    private Logger logger = LoggerFactory.getLogger(ExceptionConfig.class);

    @ExceptionHandler(value = DuplicateKeyException.class)
    @ResponseBody
    public Result duplicateKeyException(DuplicateKeyException e) {
        logger.info(e.getMostSpecificCause().toString(), e);
        return Result.fail(ResultCode.DUPLICATE_INDEX);
    }


    @ExceptionHandler(value = Throwable.class)
    public Result errorPage(Exception e) {
        logger.debug(e.getMessage());
        return Result.fail(ResultCode.SERVER_ERROR);
    }
}
