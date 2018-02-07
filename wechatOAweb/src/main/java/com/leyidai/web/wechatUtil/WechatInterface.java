package com.leyidai.web.wechatUtil;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.leyidai.entity.vo.UserInfo;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chenchuan@autohome.com.cn
 * @create 2018-02-07-下午9:41
 * @description
 */
public class WechatInterface {
    private static final Logger log = LoggerFactory.getLogger(WechatInterface.class);

    private static final String QY_CorpID = "wweee725c3151ccda7";
    private static final String QY_Secret = "KnX0JIidnY1G-5LKiZdNdPOqIAG03cj5WR_aSqza7D4";
    private static final String APP_AgentId = "1000010";
    private static final String APP_Secret = "Lp34XwkAqy875zkyw6oiGqVZEpklXcztchFaRj3SKok";

    private static final String ACCESS_TOKEN_KEY = "access_token";

    static {
        Unirest.setTimeouts(3000, 10000);
    }

    /**
     * https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid={0}&corpsecret={1}
     *
     * @return
     */
    private static String queryAssessToken(String key) {
        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                    .queryString("corpid", QY_CorpID)
                    .queryString("corpsecret", QY_Secret)
                    .asJson();
            JSONObject json = jsonResponse.getBody().getObject();
            return json.getString(key);
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


    public static String getAccessToken() {
        try {
            return cache.get(ACCESS_TOKEN_KEY);
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
        String accessToken = getAccessToken();
        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.get("https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo")
                    .queryString("access_token", accessToken)
                    .queryString("code", code)
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
            HttpResponse<JsonNode> jsonResponse = Unirest.post("https://qyapi.weixin.qq.com/cgi-bin/user/getuserdetail?access_token")
                    .queryString("access_token", getAccessToken())
                    .field("user_ticket", userTicket)
                    .asJson();
            JSONObject json = jsonResponse.getBody().getObject();
            System.out.println(json);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        System.out.println(WechatInterface.getAccessToken());
        String code = "8-iccoluhTi0ouwylLVqjV6JnHpkQjmUY7yI6eSfW80";
        String userticket = getUserTicket(code);
        System.out.println("====" + userticket);
        getUserDetail(userticket);
    }

}
