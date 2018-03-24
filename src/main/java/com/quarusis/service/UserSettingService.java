package com.quarusis.service;

import com.quarusis.data.entity.User;

public interface UserSettingService {

    /**
     * 修改用户名称
     * @param
     * @return
     */
    void UpdateUsername(User user) throws Exception;

}
