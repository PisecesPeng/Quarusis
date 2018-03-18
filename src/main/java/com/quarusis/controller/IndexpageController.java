package com.quarusis.controller;

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

    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(IndexpageController.class);

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
    @RequestMapping("/indexpage")
    public String jumpIndexpage(HttpServletRequest req) {
        logger.info("jumpIndexpage(HttpServletRequest) - start");
        //为InChat传输的数据赋值
        InChatUin = (String) req.getSession().getAttribute("uin");
        InChatName = (String) req.getSession().getAttribute("name");
        try {
            req.setAttribute("pageList", indexpageService.listIndexPage());
            req.setAttribute("heatCommentPageList", indexpageService.listPageCommentSum());
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("jumpIndexpage(HttpServletRequest) - end");
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
        logger.info("jumpTopicPage(HttpServletRequest) - start");
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
        logger.info("jumpTopicPage(HttpServletRequest) - end");
        return "/index_center/topic_page";
    }

    /**
     * 查询所有page中的指定page
     * @param
     * @return
     */
    @RequestMapping("/searchAllpage")
    public @ResponseBody List<Page> searchAllPage(HttpServletRequest req,@RequestBody Map map) {
        logger.info("searchAllPage(HttpServletRequest,Map) - start");
        List<Page> list = null;
        try {
            //从所有Page中选取符合条件的Page
            list = indexpageService.listSearchAllpage((String) map.get("search"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("searchAllPage(HttpServletRequest,Map) - end");
        return list;
    }

    /**
     * 查询指定Topic的指定page
     * @param
     * @return
     */
    @RequestMapping("/searchTopicpage")
    public @ResponseBody List<Page> searchTopicPage(@RequestBody Map map) {
        logger.info("searchTopicPage(Map) - start");
        List<Page> list = null;
        try {
            //从指定topicpage中选取符合条件的Page
            list = indexpageService.listSearchTopicpage(topic,(String) map.get("search"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("searchTopicPage(Map) - end");
        return list;
    }

    /**
     * 遍历余下的indexpage
     * @param
     * @return
     */
    @RequestMapping("/appendIndexpage")
    public @ResponseBody List<Page> appendIndexpage(@RequestBody Map map) {
        logger.info("appendIndexpage(Map) - start");
        List<Page> list = null;
        try {
            list = indexpageService.appendIndexpage(Integer.valueOf((String) map.get("beginpage")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("appendIndexpage(Map) - end");
        return list;
    }
    /**
     * 遍历余下的topicpage
     * @param
     * @return
     */
    @RequestMapping("/appendTopicpage")
    public @ResponseBody List<Page> appendTopicpage(@RequestBody Map map) {
        logger.info("appendTopicpage(Map) - start");
        List<Page> list = null;
        try {
            list = indexpageService.appendTopicpage(topic,Integer.valueOf((String) map.get("beginpage")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("appendTopicpage(Map) - end");
        return list;
    }

    /**
     * InChat,返回用户信息
     * @param
     * @return
     */
    @RequestMapping("/getUserInfo")
    public void getUserInfo(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("getUserInfo(HttpServletRequest,HttpServletResponse) - start");
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
        logger.info("getUserInfo(HttpServletRequest,HttpServletResponse) - end");
    }

}
