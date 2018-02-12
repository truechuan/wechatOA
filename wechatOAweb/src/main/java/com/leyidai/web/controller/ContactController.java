package com.leyidai.web.controller;

import java.util.List;
import java.util.Map;

import com.leyidai.entity.vo.Department;
import com.leyidai.entity.vo.DepartmentUser;
import com.leyidai.web.wechatUtil.DepartmentInterface;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chenchuan@autohome.com.cn
 * @create 2018-02-11-下午10:25
 * @description
 */
@Controller
public class ContactController {
    private static final Logger log = LoggerFactory.getLogger(ContactController.class);

    @RequestMapping(value = "/contact/list")
    public String contact(Model model) {
        log.info("load contact list start");
        Department department = DepartmentInterface.getDepartment(1);
        model.addAttribute("departmentName", department.getName());

        List<Department> departments = DepartmentInterface.getDepartmentByParentId(1);
        List<DepartmentUser> users = DepartmentInterface.getDepartmentUser(1);

        model.addAttribute("departments", departments);
        model.addAttribute("users", users);


        return "wechatOA/contact";
    }

    @RequestMapping(value = "/contact/singleDepartment")
    public @ResponseBody
    Map<String,Object> getSigleDepartment(Integer departmentId){
        List<Department> departments = DepartmentInterface.getDepartmentByParentId(departmentId);
        List<DepartmentUser> users = DepartmentInterface.getDepartmentUser(departmentId);

        Map<String,Object> map = new HashedMap();
        map.put("departments", departments);
        map.put("users", users);
        return map;
    }
}
