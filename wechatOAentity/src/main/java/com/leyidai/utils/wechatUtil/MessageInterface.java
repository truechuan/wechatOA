package com.leyidai.utils.wechatUtil;

import com.leyidai.entity.vo.Message;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chenchuan@autohome.com.cn
 * @create 2018-02-13-上午9:56
 * @description
 */
public class MessageInterface {
    private static final Logger log = LoggerFactory.getLogger(MessageInterface.class);

    public static JSONObject sendMessage(Message message) {
        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.post("https://qyapi.weixin.qq.com/cgi-bin/message/send")
                    .queryString("access_token", AuthInterface.getAccessToken(WechatContent.MESSAGE_Secret))
                    .body(new JSONObject(message).toString())
                    .asJson();
            JSONObject json = jsonResponse.getBody().getObject();

            return json;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;

    }


}
