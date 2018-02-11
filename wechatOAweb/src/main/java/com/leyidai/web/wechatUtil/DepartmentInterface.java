package com.leyidai.web.wechatUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.leyidai.entity.vo.Department;
import com.leyidai.entity.vo.DepartmentUser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chenchuan@autohome.com.cn
 * @create 2018-02-10-下午2:27
 * @description
 */
public class DepartmentInterface {
    private static final Logger log = LoggerFactory.getLogger(DepartmentInterface.class);

    private static Department queryDepartment(Integer id) {
        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                    .queryString("access_token", AuthInterface.getAccessToken())
                    .queryString("id", id)
                    .asJson();
            JSONObject json = jsonResponse.getBody().getObject();
            JSONArray array = json.getJSONArray("department");
            for (int i = 0; i < array.length(); i++) {
                Department department = new Department();
                JSONObject depJson = array.getJSONObject(i);
                department.setId(depJson.getInt("id"));
                department.setName(depJson.getString("name"));
                department.setParentid(depJson.getInt("parentid"));
                cache.put(depJson.getInt("id"), department);
            }
            return cache.get(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * 缓存部门信息
     */
    private static LoadingCache<Integer, Department> cache = CacheBuilder.newBuilder()
            .refreshAfterWrite(1, TimeUnit.HOURS)//写入之后一小时过期
            .build(new CacheLoader<Integer, Department>() {
                @Override
                public Department load(Integer key) throws Exception {
                    return queryDepartment(key);
                }
            });

    public static Department getDepartment(Integer id) {
        try {
            return cache.get(id);
        } catch (ExecutionException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public static List<Department> getDepartmentByParentId(Integer parentId) {
        queryDepartment(parentId);
        Map<Integer, Department> map = cache.asMap();
        List<Department> list = new ArrayList<Department>();
        for (Map.Entry<Integer, Department> entry : map.entrySet()) {
            Department dep = entry.getValue();
            if (parentId.compareTo(dep.getParentid()) == 0) {
                list.add(dep);
            }
        }
        return list;
    }

    public static List<DepartmentUser> getDepartmentUser(Integer departmentId) {
        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.get("https://qyapi.weixin.qq.com/cgi-bin/user/list")
                    .queryString("access_token", AuthInterface.getAccessToken())
                    .queryString("department_id", departmentId)
                    .queryString("fetch_child", 0)
                    .queryString("status", 0)
                    .asJson();
            JSONObject json = jsonResponse.getBody().getObject();
            JSONArray array = json.getJSONArray("userlist");
            List<DepartmentUser> list = new ArrayList<DepartmentUser>();
            for (int i = 0; i < array.length(); i++) {
                DepartmentUser user = new DepartmentUser();
                JSONObject userJson = array.getJSONObject(i);
                user.setUserid(userJson.getString("userid"));
                user.setName(userJson.getString("name"));

                JSONArray departments = userJson.getJSONArray("department");
                Integer[] dep = new Integer[departments.length()];
                for (int j = 0; j < departments.length(); j++) {
                    dep[j] = departments.getInt(j);
                }
                user.setDepartment(dep);
                user.setPosition(userJson.getString("position"));
                user.setMobile(userJson.getString("mobile"));
                user.setGender(userJson.getInt("gender"));
                user.setEmail(userJson.getString("email"));
//                user.setWeixinid(userJson.getString("weixinid"));
                user.setAvatar(userJson.getString("avatar"));
                user.setStatus(userJson.getInt("status"));
                user.setExtattr(userJson.getJSONObject("extattr").toString());
                list.add(user);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return Collections.emptyList();
        }
    }


    public static void main(String[] args) {
        List<DepartmentUser> list = getDepartmentUser(1);
        System.out.println(AuthInterface.getAccessToken());
    }
}
