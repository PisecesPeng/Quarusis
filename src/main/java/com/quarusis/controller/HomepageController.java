package com.quarusis.controller;

import com.quarusis.annotation.SysLog;
import com.quarusis.data.entity.Page;
import com.quarusis.service.HomepageService;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class HomepageController {

    @Resource
    private Page page;
    @Resource(name="HomepageService")
    private HomepageService homepageService;

    /**
     * 跳转homepage页面
     * @param req
     * @return
     */
    @SysLog("跳转homepage页面")
    @RequestMapping("/homepage")
    public String jumpHomepage(HttpServletRequest req) {
        //通过account查询用户动态列表
        try {
            req.setAttribute("pageList", homepageService.listPage((String) req.getSession().getAttribute("uin")));
            req.setAttribute("historyList", homepageService.listUserHistory(Integer.valueOf((String) req.getSession().getAttribute("uin"))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/home_center/home_page";
    }

    /**
     * 用户上传page
     * @param req
     * @return
     */
    @SysLog("用户上传page")
    @RequestMapping("/pageUpload.do")
    public String uploadPage(HttpServletRequest req,@RequestParam("pagePicture") MultipartFile picture) {
        try {
            //设置编码
            req.setCharacterEncoding("UTF-8");
            //操作用户上传的图片与文本
            String uin = (String) req.getSession().getAttribute("uin");
            String pageTopic = req.getParameter("pageTopic");
            String pageTitle = req.getParameter("pageTitle");
            String pageText = req.getParameter("pageWord");
            Integer whetherComment;
            if(req.getParameter("pageComment") == null) {
                whetherComment = 0;
            } else {
                whetherComment = 1;
            }
            //处理图片数据
            String pictureUrl = System.currentTimeMillis()+ picture.getOriginalFilename();
            FileUtils.copyInputStreamToFile(picture.getInputStream(), new File(File.separator + "home" + File.separator + "piseces" + File.separator + "Development" + File.separator + "Quarusis" + File.separator + "upload" + File.separator, pictureUrl));

            //set处理后的属性
            page.setUin(uin);
            page.setTopic(pageTopic);
            page.setTitle(pageTitle);
            page.setText(pageText);
            page.setUrl(pictureUrl);
            page.setWhetherComment(whetherComment);

            //上传action操作
            homepageService.pageUpload(page);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/homepage";
    }

//    /**
//     * 跳转页面
//     * ps.该页面是为了 其他页面拿其页面中的值
//     * @param req
//     * @return
//     */
//    @RequestMapping("/pagelist")
//    public String pagelist(HttpServletRequest req) {
//        return "/home_center/websocket";
//    }

    /**
     * 查询指定Topic的指定page
     * @param
     * @return
     */
    @SysLog("查询指定Topic的指定page")
    @RequestMapping("/searchHomepage")
    public @ResponseBody List<Page> searchHomePage(HttpServletRequest req,@RequestBody Map map) {
        List<Page> list = null;
        try {
            //从指定topicpage中选取符合条件的Page
            list = homepageService.listSearchHomepage((String) req.getSession().getAttribute("uin"),(String) map.get("search"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
