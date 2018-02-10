package com.quarusis.controller;

import com.quarusis.data.entity.Page;
import com.quarusis.data.entity.User;
import com.quarusis.service.IndexpageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONObject;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class IndexpageController {

    @Resource(name="IndexpageService")
    private IndexpageService indexpageService;
    private String InChatUin;
    private String InChatName;

    /**
     * 跳转index页面
     * @param req
     * @return
     */
    @RequestMapping("/indexpage")
    public String jumpIndexpage(HttpServletRequest req) {
        InChatUin = (String) req.getSession().getAttribute("uin");
        InChatName = (String) req.getSession().getAttribute("name");
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

    /**
     * InChat,返回用户信息
     * @param
     * @return
     */
    @RequestMapping("/getUserInfo")
    public void getUserInfo(HttpServletRequest req, HttpServletResponse resp) {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        try {
            Map<String,String> map = new HashMap<String,String>();
            map.put("uin", InChatUin);
            map.put("name", InChatName);
            PrintWriter out = resp.getWriter();
            JSONObject resultJSON = JSONObject.fromObject(map); //根据需要拼装json
            String jsonpCallback = req.getParameter("jsonpCallback");//客户端请求参数
            out.println(jsonpCallback+"("+resultJSON.toString()+")");//返回jsonp格式数据
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
