package com.quarusis.service;

import com.quarusis.data.entity.User;

public interface LoginService {

    /**
     * 添加用户信息至用户信息表
     */
    void insertUserInfo(User user) throws Exception;

    /**
     * 查询用户信息表的信息
     */
    User queryUserInfoByUin(String uin) throws Exception;
    Boolean queryUserInfoByName(String name) throws Exception;

}
