package com.quarusis.data.entity;

import org.springframework.stereotype.Component;

@Component
public class Comment {
    private int id;
    private String uin;
    private String name;
    private String text;
    private int heat;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getUin() {
        return uin;
    }

    public String getText() {
        return text;
    }

    public int getHeat() {
        return heat;
    }

    public void setUin(String uin) {
        this.uin = uin;
    }

    public void setText(String text) {
        this.text = text;
    }
}
