package com.quarusis.controller;

import com.quarusis.service.IndexpageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class IndexpageController {

    @Resource(name="IndexpageService")
    private IndexpageService indexpageService;

    /**
     * 跳转index页面
     * @param req
     * @return
     */
    @RequestMapping("/index_page")
    public String jumpIndexpage(HttpServletRequest req) {
        try {
            req.setAttribute("allPageList", indexpageService.listAllPage());
            req.setAttribute("HeatCommentPageList", indexpageService.listPageCommentSum());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/index_center/index_page";
    }

}
