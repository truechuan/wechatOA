package com.leyidai.entity.vo;

import org.json.JSONObject;

/**
 * @author chenchuan@autohome.com.cn
 * @create 2018-02-13-上午9:56
 * @description
 */
public class TextMessage extends Message {

    private String msgtype = "text";
    private JSONObject text;
    private Integer safe = 0;

    @Override
    public String getMsgtype() {
        return msgtype;
    }

    @Override
    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public JSONObject getText() {
        return text;
    }

    public void setText(JSONObject text) {
        this.text = text;
    }

    @Override
    public Integer getSafe() {
        return safe;
    }

    @Override
    public void setSafe(Integer safe) {
        this.safe = safe;
    }
}
