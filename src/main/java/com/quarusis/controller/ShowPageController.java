package com.quarusis.controller;

import com.quarusis.data.entity.Comment;
import com.quarusis.data.entity.Page;
import com.quarusis.service.PageService;
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
        try {
            req.setAttribute("page", pageService.showPage(Integer.valueOf(pid)));
            if (pageService.showPage(Integer.valueOf(pid)).getWhetherComment() == 1) {
                    req.setAttribute("commentList", pageService.listComment(Integer.valueOf(pid)));
                    req.setAttribute("heatCommentList", pageService.listHeatComment(Integer.valueOf(pid)));
                if (pageService.showPage(Integer.valueOf(pid)).getUin().equals(req.getSession().getAttribute("uin"))) {
                    pageService.readComment(Integer.valueOf(pid));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/page_center/page";
    }

    /**
     * 跳转homepage页面
     * @param
     * @return
     */
    @RequestMapping("/page/{pid}/plusCommentHeat.do")
    public @ResponseBody Integer plusCommentHeat(HttpServletRequest req, @PathVariable("pid") String pid, @RequestBody Map map) {
        Integer uin = Integer.valueOf((String) req.getSession().getAttribute("uin"));
        Integer cid = Integer.valueOf((String)map.get("cid"));
        Integer pageid = Integer.valueOf(pid);

        try {
            if (pageService.queryHeat(uin, pageid, cid) == null) {
                pageService.plusCommentHeat(uin, pageid, cid);
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;

    }

    /**
     * 跳转homepage页面
     * @param
     * @return
     */
    @RequestMapping("/page/{pid}/uploadComment.do")
    public String uploadComment(HttpServletRequest req,@PathVariable("pid") String pid) {
        comment.setUin((String) req.getSession().getAttribute("uin"));
        comment.setText(req.getParameter("comment"));
        try {
            pageService.commentUpload(Integer.valueOf(pid), comment);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/page/{pid}";
    }
}
