package com.leyidai.utils.wechatUtil;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.leyidai.entity.vo.Department;
import com.leyidai.entity.vo.UserInfo;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chenchuan@autohome.com.cn
 * @create 2018-02-07-下午9:41
 * @description
 */
public class AuthInterface {
    private static final Logger log = LoggerFactory.getLogger(AuthInterface.class);

    /**
     * https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid={0}&corpsecret={1}
     *
     * @return
     */
    private static String queryAssessToken(String appSecrect) {
        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                    .queryString("corpid", WechatContent.QY_CorpID)
                    .queryString("corpsecret", appSecrect)
                    .asJson();
            JSONObject json = jsonResponse.getBody().getObject();
            return json.getString(WechatContent.ACCESS_TOKEN_KEY);
        } catch (Exception e) {
            log.error(e.getMessage());
            return "";
        }
    }


    /**
     * 缓存session id
     */
    private static LoadingCache<String, String> cache = CacheBuilder.newBuilder()
            .refreshAfterWrite(1, TimeUnit.HOURS)//写入之后一小时过期
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {
                    return queryAssessToken(key);
                }
            });


    public static String getAccessToken(String appSecrect) {
        try {
            return cache.get(appSecrect);
        } catch (ExecutionException e) {
            log.error(e.getMessage());
            return "";
        }
    }


    /**
     * user_ticket 时长2小时
     * https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=ACCESS_TOKEN&code=CODE
     *
     * @return
     */
    public static String getUserTicket(String code) {
        String accessToken = getAccessToken(WechatContent.MYINFO_Secret);
        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.get("https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo")
                    .queryString("access_token", accessToken)
                    .queryString("code", code)
                    .queryString("agentid", WechatContent.MYINFO_AgentId)
                    .asJson();
            JSONObject json = jsonResponse.getBody().getObject();
            log.info(json.toString());
            return json.getString("user_ticket");
        } catch (Exception e) {
            log.error(e.getMessage());
            return "";
        }
    }


    /**
     * Https请求方式：POST
     * <p>
     * https://qyapi.weixin.qq.com/cgi-bin/user/getuserdetail?access_token=ACCESS_TOKEN
     *
     * @param userTicket
     * @return
     */
    public static UserInfo getUserDetail(String userTicket) {
        try {
            JSONObject param = new JSONObject();
            param.put("user_ticket", userTicket);
            HttpResponse<JsonNode> jsonResponse = Unirest.post("https://qyapi.weixin.qq.com/cgi-bin/user/getuserdetail")
                    .queryString("access_token", getAccessToken(WechatContent.MYINFO_Secret))
                    .body(param.toString())
                    .asJson();
            JSONObject json = jsonResponse.getBody().getObject();
            UserInfo userInfo = new UserInfo();
            userInfo.setAvatar(json.getString("avatar"));
            userInfo.setName(json.getString("name"));
            userInfo.setGender(json.getInt("gender"));
            userInfo.setMobile(json.getString("mobile"));
            userInfo.setEmail(json.getString("email"));

            JSONArray array = json.getJSONArray("department");
            Integer[] department = new Integer[array.length()];
            String[] departmentStr = new String[array.length()];

            for (int i = 0; i < array.length(); i++) {
                Department dep = DepartmentInterface.getDepartment(array.getInt(i));
                department[i] = array.getInt(i);
                departmentStr[i] = dep.getName();
            }

            userInfo.setDepartment(department);
            userInfo.setDepartmentStr(departmentStr);

            return userInfo;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }


    public static void main(String[] args) {
        String user_ticket = "-qqgoTy0AWHRXTenZz4nuxl3oxwgNeL6BJDAuHdl3zrRk7cMw_Yayo81UMGtoOBpfXdv4a4TMfppVUWinMb4H1S86i3SwNrrzt1aC2y9ySI";
        getUserDetail(user_ticket);
    }

}
