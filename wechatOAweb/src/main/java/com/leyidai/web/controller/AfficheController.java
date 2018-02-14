package com.leyidai.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author chenchuan@autohome.com.cn
 * @create 2018-02-13-下午1:47
 * @description
 */
@Controller
public class AfficheController {
    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    @RequestMapping(value = "/notice/list")
    public String queryAfficheList() {
        return "wechatOA/noticelist";
    }

    @RequestMapping(value = "/notice/detail/{id}")
    public String queryAfficheDetail(@PathVariable("id") String id) {
        return "wechatOA/noticedetail";
    }

}