package com.quarusis.data.dao;

import com.quarusis.data.entity.User;

public interface LoginDao {

    /**
     * 添加用户信息至用户信息表
     * @param
     * @return
     */
    void insertUserInfo(User user);
    void createUserHistory(Integer uin);

    /**
     * 查询用户信息表的信息
     * @param
     * @return
     */
    User queryUserInfoByUin(String uin);
    User queryUserInfoByName(String name);

}
