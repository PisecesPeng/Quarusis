package com.quarusis.service.impl;

import com.quarusis.data.dao.LoginDao;
import com.quarusis.data.entity.User;
import com.quarusis.service.LoginService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service("LoginService")
public class LoginServiceImpl implements LoginService{

    @Resource
    private LoginDao loginDao;
    @Resource
    private User user;

    /**
     * 添加用户信息至用户信息表
     */
    @Transactional
    public void insertUserInfo(User user) throws Exception {
        loginDao.insertUserInfo(user);
        loginDao.createUserHistory(Integer.valueOf(user.getUin()));
    }

    /**
     * 查询用户信息表的信息
     */
    public User queryUserInfoByUin(String uin) throws Exception {
        return loginDao.queryUserInfoByUin(uin);
    }
    public Boolean queryUserInfoByName(String name) throws Exception {
        user = loginDao.queryUserInfoByName(name);
        //判断是否有数据返回
        if("".equals(user.getName())) {
            return true;
        }
        return false;
    }
}
