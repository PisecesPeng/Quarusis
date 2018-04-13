package com.quarusis.data.entity;

import org.springframework.stereotype.Component;

@Component
public class Comment {
    private int id;
    private int toId;
    private String uin;
    private String toUin;
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

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public String getToUin() {
        return toUin;
    }

    public void setToUin(String toUin) {
        this.toUin = toUin;
    }
}
