package com.example.demo.frame;

/**
 * Created by tanlifei on 15/9/11.
 */
public class MainBean {

    private String activityPath;//跳转的activity路径
    private String name;//显示的名字
    private String desc;//描述

    public String getActivityPath() {
        return activityPath;
    }

    public void setActivityPath(String activityPath) {
        this.activityPath = activityPath;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
