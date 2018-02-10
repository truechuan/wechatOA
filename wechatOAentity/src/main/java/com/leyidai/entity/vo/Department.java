package com.leyidai.entity.vo;

/**
 * @author chenchuan@autohome.com.cn
 * @create 2018-02-10-下午2:25
 * @description
 */
public class Department {
    private Integer id;
    private String name;
    private Integer parentid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }
}
