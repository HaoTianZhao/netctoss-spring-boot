package com.barista.config;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.barista.result.Result;
import com.barista.result.ResultCode;

import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.lang.annotation.Annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 错误处理
 *
 * @ClassName ExceptionConfig
 * @Author zhaoth
 * @Date 2019/9/3 17:39
 * @Version 1.0
 */
@ControllerAdvice//此注解本质是对controller做切面,只捕获controller抛出的异常
public class ExceptionConfig implements HandlerExceptionResolver {
    private Logger logger = LoggerFactory.getLogger(ExceptionConfig.class);

    @ExceptionHandler(value = DuplicateKeyException.class)
    @ResponseBody
    public Result duplicateKeyException(DuplicateKeyException e) {
        logger.info(e.getMostSpecificCause().toString(), e);
        return Result.fail(ResultCode.DUPLICATE_INDEX);
    }

    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public Result errorPage(NullPointerException e) {
        logger.debug(e.getMessage());
        return Result.fail(ResultCode.SERVER_ERROR);
    }

    @ExceptionHandler(value = Throwable.class)
    @ResponseBody
    public Result errorPage(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler, Throwable e) {
        //request,response,handler都能拿到
        logger.debug(e.getMessage());
        return Result.fail(ResultCode.SERVER_ERROR);
    }

    // 可以捕获拦截器,不能捕获子线程.
    // 优先级比@ExceptionHandler高
    @Override
    @ResponseBody
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logger.debug(ex.getMessage());
        ModelAndView modelAndView = new ModelAndView(new FastJsonJsonView());
        modelAndView.addObject("model", Result.fail(ResultCode.SERVER_ERROR));
        return modelAndView;
    }
}
