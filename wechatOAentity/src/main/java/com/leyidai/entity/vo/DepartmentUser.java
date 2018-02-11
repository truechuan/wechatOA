package com.leyidai.entity.vo;

/**
 * @author chenchuan@autohome.com.cn
 * @create 2018-02-11-下午11:10
 * @description
 */
public class DepartmentUser {

    private String userid;

    private String name;

    private Integer[] department;

    private String position;

    private String mobile;

    /**
     * 性别。0表示未定义，1表示男性，2表示女性
     */
    private Integer gender;

    private String genderStr;

    private String email;

    private String weixinid;

    /**
     * 头像url。注：如果要获取小图将url最后的”/0”改成”/64”即可
     */
    private String avatar;

    private String smallAvatar;

    /**
     * 关注状态: 1=已关注，2=已冻结，4=未关注
     */
    private Integer status;

    private String extattr;


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer[] getDepartment() {
        return department;
    }

    public void setDepartment(Integer[] department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeixinid() {
        return weixinid;
    }

    public void setWeixinid(String weixinid) {
        this.weixinid = weixinid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getExtattr() {
        return extattr;
    }

    public void setExtattr(String extattr) {
        this.extattr = extattr;
    }

    public String getGenderStr() {
        switch (gender) {
            case 0:
                return "未定义";
            case 1:
                return "男";
            case 2:
                return "女";
            default:
                return "未定义";
        }
    }

    public void setGenderStr(String genderStr) {
        this.genderStr = genderStr;
    }

    public String getSmallAvatar() {
        String smallAvatar = avatar.substring(0, avatar.length() - 2) + "/64";
        return smallAvatar;
    }

    public void setSmallAvatar(String smallAvatar) {
        this.smallAvatar = smallAvatar;
    }
}
