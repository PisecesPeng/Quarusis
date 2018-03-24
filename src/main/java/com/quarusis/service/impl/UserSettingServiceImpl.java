package com.quarusis.service.impl;

import com.quarusis.data.dao.UserSettingDao;
import com.quarusis.data.entity.User;
import com.quarusis.service.UserSettingService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("UserSettingService")
public class UserSettingServiceImpl implements UserSettingService {

    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(UserSettingServiceImpl.class);

    @Resource
    private UserSettingDao userSettingDao;

    /**
     * 修改用户名称
     * @param
     * @return
     */
    public void UpdateUsername(User user) throws Exception {
        logger.info("UpdateUsername(User) - start");
        userSettingDao.UpdateUsername(user);
        logger.info("UpdateUsername(User) - end");
    }

}
