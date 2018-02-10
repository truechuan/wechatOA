package com.leyidai.web.filter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.leyidai.entity.vo.UserInfo;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CookieInterceptor implements HandlerInterceptor {

    private static final String REDIRECT_URL = "";

    private static Logger log = LoggerFactory.getLogger(CookieInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        log.debug("CookieInterceptor");

        String code = request.getParameter("code");
        Cookie[] cookies = request.getCookies();
        if (null == code || cookies == null) {
            response.sendRedirect(REDIRECT_URL);
            return false;
        }
        for (Cookie cookie : cookies) {
            if ("cfu".equals(cookie.getName())) {
                String userInfoStr = cookie.getValue();
                JSONObject json = new JSONObject(userInfoStr);

                UserInfo userInfo = new UserInfo();
                userInfo.setAvatar(json.getString("avatar"));
                userInfo.setName(json.getString("name"));
                userInfo.setGender(json.getInt("gender"));
                userInfo.setMobile(json.getString("mobile"));
                userInfo.setEmail(json.getString("email"));

                JSONArray array = json.getJSONArray("department");
                JSONArray dep = json.getJSONArray("departmentStr");
                Integer[] department = new Integer[array.length()];
                String[] departmentStr = new String[array.length()];

                for (int i = 0; i < array.length(); i++) {
                    department[i] = array.getInt(i);
                    departmentStr[i] = dep.getString(i);
                }

                userInfo.setDepartment(department);
                userInfo.setDepartmentStr(departmentStr);
                request.getSession().setAttribute("user", userInfo);
                return true;
            }
        }
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub

    }

}
