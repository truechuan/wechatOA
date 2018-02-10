package com.leyidai.web.wechatUtil;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.leyidai.entity.vo.Department;
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

    public static void main(String[] args) {
        System.out.println(getDepartment(1).getName());
        System.out.println(getDepartment(2).getName());
    }
}
