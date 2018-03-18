package com.quarusis.service.impl;

import com.quarusis.data.dao.LoginDao;
import com.quarusis.data.entity.User;
import com.quarusis.service.LoginService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service("LoginService")
public class LoginServiceImpl implements LoginService{

    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(LoginServiceImpl.class);

    @Resource
    private LoginDao loginDao;
    @Resource
    private User user;


    /**
     * 添加用户信息至用户信息表
     */
    @Transactional
    public void insertUserInfo(User user) throws Exception {
        logger.info("insertUserInfo(User) - start");
        loginDao.insertUserInfo(user);
        loginDao.createUserHistory(Integer.valueOf(user.getUin()));
        logger.info("insertUserInfo(User) - end");
    }

    /**
     * 查询用户信息表的信息
     */
    public User queryUserInfoByUin(String uin) throws Exception {
        logger.info("queryUserInfoByUin(String) - start");
        logger.info("queryUserInfoByUin(String) - end");
        return loginDao.queryUserInfoByUin(uin);
    }
    public Boolean queryUserInfoByName(String name) throws Exception {
        logger.info("queryUserInfoByName(String) - start");
        user = loginDao.queryUserInfoByName(name);
        //判断是否有数据返回
        if("".equals(user.getName())) {
            logger.info("queryUserInfoByName(String) - end");
            return true;
        }
        logger.info("queryUserInfoByName(String) - end");
        return false;
    }
}
