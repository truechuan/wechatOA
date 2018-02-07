package com.leyidai.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author chenchuan@autohome.com.cn
 * @create 2018-02-07-下午7:11
 * @description
 */
@Controller
public class MyInfoController {
    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    @RequestMapping(value="/myInfo")
    public String myInfo(String code){
        log.info("load myAccount start");

        return "wechatOA/myInfo";
    }
}
