package com.quarusis.controller;

import com.quarusis.data.entity.Comment;
import com.quarusis.data.entity.Page;
import com.quarusis.service.PageService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/")
public class ShowPageController {

    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(ShowPageController.class);

    @Resource
    private Page page;
    @Resource
    private Comment comment;
    @Resource(name="PageService")
    private PageService pageService;

    /**
     * 跳转homepage页面
     * @param
     * @return
     */
    @RequestMapping("/page/{pid}")
    public String jumpPage(HttpServletRequest req,@PathVariable("pid") String pid) {
        logger.info("jumpPage(HttpServletRequest,String) - start");
        try {
            req.setAttribute("page", pageService.showPage(Integer.valueOf(pid)));
            //此page是否允许评论
            if (pageService.showPage(Integer.valueOf(pid)).getWhetherComment() == 1) {
                    req.setAttribute("normalCommentList", pageService.listNormalComment(Integer.valueOf(pid)));
                    req.setAttribute("fireCommentList", pageService.listFireComment(Integer.valueOf(pid)));
                if (pageService.showPage(Integer.valueOf(pid)).getUin().equals(req.getSession().getAttribute("uin"))) {
                    pageService.readComment(Integer.valueOf(pid));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("jumpPage(HttpServletRequest,String) - end");
        return "/page_center/page";
    }

    /**
     * 操作评论热度操作
     * @param
     * @return
     */
    @RequestMapping("/page/{pid}/operatCommentHeat.do")
    public @ResponseBody Integer operatCommentHeat(HttpServletRequest req, @PathVariable("pid") String pid, @RequestBody Map map) {
        logger.info("operatCommentHeat(HttpServletRequest,String,Map) - start");
        Integer uin = Integer.valueOf((String) req.getSession().getAttribute("uin"));
        Integer cid = Integer.valueOf((String) map.get("cid"));
        Integer pageid = Integer.valueOf(pid);
        try {
            if (pageService.queryHeat(uin, pageid, cid) == null) {
                // 增加评论热度操作
                pageService.plusCommentHeat(uin, pageid, cid);
                return 1;
            } else {
                // 减少评论热度操作
                pageService.subCommentHeat(uin, pageid, cid);
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("operatCommentHeat(HttpServletRequest,String,Map) - end");
        return -1;
    }

    /**
     * 跳转homepage页面
     * @param
     * @return
     */
    @RequestMapping("/page/{pid}/uploadComment.do")
    public String uploadComment(HttpServletRequest req,@PathVariable("pid") String pid) {
        logger.info("uploadComment(HttpServletRequest,String) - start");
        comment.setUin((String) req.getSession().getAttribute("uin"));
        comment.setText(req.getParameter("comment"));
        try {
            pageService.commentUpload(Integer.valueOf(pid), comment);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("uploadComment(HttpServletRequest,String) - end");
        return "redirect:/page/{pid}";
    }

    /**
     * 用户移除Page操作
     * @param req
     * @return
     */
    @RequestMapping("/removePage.do")
    public String removePage(HttpServletRequest req) {
        logger.info("removePage(HttpServletRequest) - start");
        try {
            //从request中获得属性
            Integer pid = Integer.valueOf(req.getParameter("pid"));
            pageService.removePage(pid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("removePage(HttpServletRequest) - end");
        return "redirect:/homepage";
    }

}
