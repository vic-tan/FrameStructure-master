package com.example.demo.frame.bean;

import java.io.Serializable;

/**
 * Created by tanlifei on 15/9/9.
 */
public class TagBean implements Serializable{
    private String my_id;
    private String name;

    public TagBean() {
    }

    public String getMy_id() {
        return my_id;
    }

    public void setMy_id(String my_id) {
        this.my_id = my_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
