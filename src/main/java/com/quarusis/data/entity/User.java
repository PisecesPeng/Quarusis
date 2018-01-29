package com.quarusis.data.entity;

import org.springframework.stereotype.Component;

@Component
public class User {

    public User() {}

    private String uin;
    private String name;

    public String getUin() {
        return uin;
    }

    public void setUin(String uin) {
        this.uin = uin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "uin='" + uin + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
