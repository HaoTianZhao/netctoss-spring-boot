package com.barista;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @ClassName JSPApp
 * @Author zhaoth
 * @Date 2019/9/3 14:11
 * @Version 1.0
 */

@Controller
public class JSPController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

}
