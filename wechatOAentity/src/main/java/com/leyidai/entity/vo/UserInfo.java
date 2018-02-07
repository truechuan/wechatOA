package com.leyidai.entity.vo;

/**
 * @author chenchuan@autohome.com.cn
 * @create 2018-02-07-下午7:30
 * @description
 */
public class UserInfo {

    private String userid;
    private String name;
    private Integer[] department;
    private String position;
    private String mobile;
    /**
     * 性别。0表示未定义，1表示男性，2表示女性
     */
    private Integer gender;
    private String email;
    /**
     * 头像url。注：如果要获取小图将url最后的”/0”改成”/64”即可
     */
    private String avatar;

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}

