package com.quarusis.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class LogoutController {

    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(LogoutController.class);

    /**
     * 用户注销
     * @param req
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest req) {
        logger.info("logout(HttpServletRequest) - start");
        //防止创建Session
        HttpSession session = req.getSession(false);
        if(session == null){
            return "redirect:/login";
        }
        //移除session
        session.removeAttribute("uin");
        session.removeAttribute("name");
        logger.info("logout(HttpServletRequest) - end");
        return "redirect:/login";
    }

}
