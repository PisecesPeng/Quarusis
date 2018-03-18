package com.quarusis.service.impl;

import com.quarusis.data.dao.HomepageDao;
import com.quarusis.data.entity.Page;
import com.quarusis.service.HomepageService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("HomepageService")
public class HomepageServiceImpl implements HomepageService {

    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(HomepageServiceImpl.class);

    @Resource
    private HomepageDao homepageDao;
    @Resource
    private Page page;

    /**
     * 用户上传page
     * @param
     * @throws
     */
    public void pageUpload(Page page) throws Exception {
        logger.info("pageUpload(Page) - start");
        homepageDao.pageUpload(page);
        page = homepageDao.queryPageByUin(Integer.valueOf(page.getUin()));
        if (page.getWhetherComment() == 1) {
            homepageDao.createPageComment(page.getId());
        }
        logger.info("pageUpload(Page) - end");
    }

    /**
     * 遍历用户动态
     * @param
     * @throws
     */
    public List<Page> listPage(String uin) throws Exception {
        logger.info("listPage(String) - start");
        logger.info("listPage(String) - end");
        //System.out.println("遍历用户动态");
        return homepageDao.listPage(uin);
    }

    /**
     * 遍历用户历史评论文章
     * @param
     * @throws
     */
    public List<Page> listUserHistory(Integer uin) throws Exception {
        logger.info("listUserHistory(Integer) - start");
        logger.info("listUserHistory(Integer) - end");
        return homepageDao.listUserHistory(uin);
    }

    /**
     * 查询homepage中的指定page
     * @param
     * @throws
     */
    public List<Page> listSearchHomepage(String uin,String search) throws Exception {
        logger.info("listSearchHomepage(String,String) - start");
        logger.info("listSearchHomepage(String,String) - end");
        return homepageDao.listSearchHomepage(uin,search);
    }

}
