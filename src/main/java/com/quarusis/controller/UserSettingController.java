package com.quarusis.controller;

import com.quarusis.annotation.SysLog;
import com.quarusis.data.entity.User;
import com.quarusis.service.UserSettingService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/")
public class UserSettingController {

    @Resource
    private User user;
    @Resource(name="UserSettingService")
    private UserSettingService userSettingService;

    /**
     * 跳转UserSettingpage页面
     * @param req
     * @return
     */
    @SysLog("跳转UserSettingpage页面")
    @RequestMapping("/UserSetting")
    public String jumpUserSetting(HttpServletRequest req) {
        return "/home_center/user_setting";
    }

    /**
     * 将用户信息持久化
     * @param req
     * @return
     */
    @SysLog("将用户更新信息持久化")
    @RequestMapping("/updateName.do")
    public void updateUserName(HttpServletRequest req,@RequestBody Map map) {
        String name = (String) map.get("name");
        String uin = (String) req.getSession().getAttribute("uin");
        user.setUin(uin);
        user.setName(name);
        try {
            userSettingService.UpdateUsername(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //设置session属性
        req.getSession().setAttribute("name",name);
    }

}
