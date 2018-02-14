package com.leyidai.entity.vo;

import com.leyidai.utils.wechatUtil.WechatContent;

/**
 * @author chenchuan@autohome.com.cn
 * @create 2018-02-13-上午10:59
 * @description
 */
public class Message {
    private String touser;
    private String toparty;
    private String totag;
    private String msgtype;
    private Integer agentid = WechatContent.MESSAGE_AgentId;
    private Integer safe;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getToparty() {
        return toparty;
    }

    public void setToparty(String toparty) {
        this.toparty = toparty;
    }

    public String getTotag() {
        return totag;
    }

    public void setTotag(String totag) {
        this.totag = totag;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public Integer getAgentid() {
        return agentid;
    }

    public void setAgentid(Integer agentid) {
        this.agentid = agentid;
    }

    public Integer getSafe() {
        return safe;
    }

    public void setSafe(Integer safe) {
        this.safe = safe;
    }
}
