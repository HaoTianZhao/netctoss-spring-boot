package com.barista.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * 转换器
 *
 * @ClassName ConvertsConfig
 * @Author zhaoth
 * @Date 2019/9/3 10:58
 * @Version 1.0
 */
@Configuration
public class ConvertersConfig {

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        List<MediaType> mediaTypeList = new ArrayList<>();
        mediaTypeList.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(mediaTypeList);

        HttpMessageConverter converter = fastConverter;
        return new HttpMessageConverters(converter);
    }
}
