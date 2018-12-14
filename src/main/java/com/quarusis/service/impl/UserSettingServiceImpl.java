package com.quarusis.service.impl;

import com.quarusis.data.dao.UserSettingDao;
import com.quarusis.data.entity.User;
import com.quarusis.service.UserSettingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("UserSettingService")
public class UserSettingServiceImpl implements UserSettingService {

    @Resource
    private UserSettingDao userSettingDao;

    /**
     * 修改用户名称
     * @param
     * @return
     */
    public void UpdateUsername(User user) throws Exception {
        userSettingDao.UpdateUsername(user);
    }

}
