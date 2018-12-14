package com.quarusis.controller;

import com.quarusis.annotation.SysLog;
import com.quarusis.data.entity.Page;
import com.quarusis.data.entity.User;
import com.quarusis.service.IndexpageService;
import org.apache.log4j.Logger;
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
    //为InChat传输的数据
    private String InChatUin;
    private String InChatName;

    /**
     * 跳转index页面
     * @param
     * @return
     */
    @SysLog("跳转index页面")
    @RequestMapping("/indexpage")
    public String jumpIndexpage(HttpServletRequest req) {
        //为InChat传输的数据赋值
        InChatUin = (String) req.getSession().getAttribute("uin");
        InChatName = (String) req.getSession().getAttribute("name");
        try {
            req.setAttribute("pageList", indexpageService.listIndexPage());
            req.setAttribute("heatCommentPageList", indexpageService.listPageCommentSum());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/index_center/index_page";
    }

    /**
     * 跳转topicpage页面
     * @param
     * @return
     */
    String topic = null;
    @SysLog("跳转topicpage页面")
    @RequestMapping("/topicpage")
    public String jumpTopicPage(HttpServletRequest req) {
        try {
            String str = req.getParameter("topic");
            //判断是否存在此topic的page,若无则仅仅传输heatCommentPageList
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
    @SysLog("查询所有page中的指定page")
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
    @SysLog("查询指定Topic的指定page")
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
     * 遍历余下的indexpage
     * @param
     * @return
     */
    @SysLog("遍历余下的indexpage")
    @RequestMapping("/appendIndexpage")
    public @ResponseBody List<Page> appendIndexpage(@RequestBody Map map) {
        List<Page> list = null;
        try {
            list = indexpageService.appendIndexpage(Integer.valueOf((String) map.get("beginpage")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    /**
     * 遍历余下的topicpage
     * @param
     * @return
     */
    @SysLog("遍历余下的topicpage")
    @RequestMapping("/appendTopicpage")
    public @ResponseBody List<Page> appendTopicpage(@RequestBody Map map) {
        List<Page> list = null;
        try {
            list = indexpageService.appendTopicpage(topic,Integer.valueOf((String) map.get("beginpage")));
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
    @SysLog("InChat聊天室,返回用户信息")
    @RequestMapping("/getUserInfo")
    public void getUserInfo(HttpServletRequest req, HttpServletResponse resp) {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        try {
            //将信息传入map中
            Map<String,String> map = new HashMap<String,String>();
            map.put("uin", InChatUin);
            map.put("name", InChatName);
            PrintWriter out = resp.getWriter();
            //根据需要拼装json
            JSONObject resultJSON = JSONObject.fromObject(map);
            //客户端请求参数
            String jsonpCallback = req.getParameter("jsonpCallback");
            //返回jsonp格式数据
            out.println(jsonpCallback+"("+resultJSON.toString()+")");
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
