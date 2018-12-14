package com.quarusis.controller;

import com.quarusis.annotation.SysLog;
import com.quarusis.data.entity.User;
import com.quarusis.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class LoginController {

    @Resource(name="LoginService")
    private LoginService loginService;
    @Resource
    private User user;

    Process proc = null;
    BufferedReader in = null;
    String uin = null;
    String name = null;

    /**
     * 跳转index页面
     * @param req
     * @return
     */
    @SysLog("跳转index页面")
    @RequestMapping("/login")
    public String jumpLogin(HttpServletRequest req) {
        try {
            //运行python脚本
            proc = Runtime.getRuntime().exec("bash wechat_QRcode.sh");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //读取py运行打印的数据
        in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        System.out.println("python_running.");
        return "login";
    }

    /**
     * 进行二维码扫码登录操作
     * @param req
     * @return
     */
    @SysLog("进行二维码扫码登录操作")
    @RequestMapping("/loginQRcode")
    public @ResponseBody Integer getUserInfo(HttpServletRequest req) {
        try {
            //取得in中的数据
            uin = in.readLine();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("uin:" + uin);
        //python destroy
        proc.destroy();
        System.out.println("python_destroy.");

        try {
            if(!("".equals(loginService.queryUserInfoByUin(uin).getName()))) {
                name = loginService.queryUserInfoByUin(uin).getName();
                //设置session属性
                req.getSession().setAttribute("uin",uin);
                req.getSession().setAttribute("name",name);
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 检查用户名是否已经被占用
     * @param map
     * @return
     */
    @SysLog("检查用户名是否已经被占用")
    @RequestMapping("/checkName")
    public @ResponseBody Integer checkName(@RequestBody Map map) {
        name = (String)map.get("name");
        try {
            if (loginService.queryUserInfoByName(name)) {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    /**
     * 将用户信息持久化
     * @param req
     * @return
     */
    @SysLog("将用户信息持久化并设置session")
    @RequestMapping("/setName.do")
    public String setUserName(HttpServletRequest req) {
        user.setUin(uin);
        user.setName(name);
        try {
            loginService.insertUserInfo(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //设置session属性
        req.getSession().setAttribute("uin",uin);
        req.getSession().setAttribute("name",name);
        return "redirect:/indexpage";
    }
}
