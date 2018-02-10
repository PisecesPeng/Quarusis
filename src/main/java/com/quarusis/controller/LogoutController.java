package com.quarusis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class LogoutController {

    /**
     * 用户注销
     * @param req
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest req) {
        //防止创建Session
        HttpSession session = req.getSession(false);
        if(session == null){
            return "redirect:/login";
        }
        //移除session
        session.removeAttribute("uin");
        session.removeAttribute("name");
        return "redirect:/login";
    }

}
