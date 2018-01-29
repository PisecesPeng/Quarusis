package com.quarusis.data.entity;

import org.springframework.stereotype.Component;

@Component
public class Heat {
    private String uin;
    private int pid;
    private int cid;

    public String getUin() {
        return uin;
    }

    public int getPid() {
        return pid;
    }

    public int getCid() {
        return cid;
    }
}
