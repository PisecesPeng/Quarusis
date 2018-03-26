package com.quarusis.controller;

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

    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(UserSettingController.class);

    @Resource
    private User user;
    @Resource(name="UserSettingService")
    private UserSettingService userSettingService;

    /**
     * 跳转UserSettingpage页面
     * @param req
     * @return
     */
    @RequestMapping("/UserSetting")
    public String jumpUserSetting(HttpServletRequest req) {
        logger.info("jumpUserSetting(HttpServletRequest) - start");
        logger.info("jumpUserSetting(HttpServletRequest) - end");
        return "/home_center/user_setting";
    }

    /**
     * 将用户信息持久化
     * @param req
     * @return
     */
    @RequestMapping("/updateName.do")
    public void updateUserName(HttpServletRequest req,@RequestBody Map map) {
        logger.info("updateUserName(HttpServletRequest,Map) - start");
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
        logger.info("updateUserName(HttpServletRequest,Map) - end");
    }

}
