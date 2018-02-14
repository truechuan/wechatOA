package com.leyidai.web.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.leyidai.entity.vo.UserInfo;
import com.leyidai.utils.wechatUtil.AuthInterface;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author chenchuan@autohome.com.cn
 * @create 2018-02-07-下午7:11
 * @description
 */
@Controller
public class MyInfoController {
    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    @RequestMapping(value = "/myInfo")
    public String myInfo(HttpServletRequest request, HttpServletResponse response, String code, Model model) {
        log.info("load myAccount start");
        UserInfo userInfo = new UserInfo();
        if (null != request.getSession().getAttribute("user")) {
            userInfo = (UserInfo) request.getSession().getAttribute("user");
        } else {
            String userTicket = AuthInterface.getUserTicket(code);
            userInfo = AuthInterface.getUserDetail(userTicket);
            Cookie cookie = new Cookie("cfu", new JSONObject(userInfo).toString());
            cookie.setMaxAge(1800);//半小时过期
            cookie.setPath("/");
            response.addCookie(cookie);
        }

        model.addAttribute("user", userInfo);
        return "wechatOA/myInfo";
    }
}
