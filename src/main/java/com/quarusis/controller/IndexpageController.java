package com.quarusis.controller;

import com.quarusis.data.entity.Page;
import com.quarusis.service.IndexpageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

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
    @RequestMapping("/indexpage")
    public String jumpIndexpage(HttpServletRequest req) {
        try {
                req.setAttribute("pageList", indexpageService.listAllPage());
                req.setAttribute("heatCommentPageList", indexpageService.listPageCommentSum());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/index_center/index_page";
    }

    /**
     * 跳转homepage页面
     * @param
     * @return
     */
    String topic = null;
    @RequestMapping("/topicpage")
    public String jumpTopicPage(HttpServletRequest req) {
        try {
            String str = req.getParameter("topic");
            if ("".equals(str)||str == null) {
                req.setAttribute("heatCommentPageList", indexpageService.listPageCommentSum());
            } else {
                topic = URLDecoder.decode(str,"utf-8");
                req.setAttribute("pageList", indexpageService.listTopicPage(topic));
                req.setAttribute("heatCommentPageList", indexpageService.listPageCommentSum());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/index_center/topic_page";
    }

    /**
     * 查询所有page中的指定page
     * @param
     * @return
     */
    @RequestMapping("/searchAllpage")
    public @ResponseBody List<Page> searchAllPage(HttpServletRequest req,@RequestBody Map map) {
        List<Page> list = null;
        try {
            //从所有Page中选取符合条件的Page
            list = indexpageService.listSearchAllpage((String) map.get("search"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 查询指定Topic的指定page
     * @param
     * @return
     */
    @RequestMapping("/searchTopicpage")
    public @ResponseBody List<Page> searchTopicPage(@RequestBody Map map) {
        List<Page> list = null;
        try {
            //从指定topicpage中选取符合条件的Page
            list = indexpageService.listSearchTopicpage(topic,(String) map.get("search"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
