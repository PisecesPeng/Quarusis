package com.quarusis.service.impl;

import com.quarusis.data.dao.IndexpageDao;
import com.quarusis.data.entity.Page;
import com.quarusis.service.IndexpageService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("IndexpageService")
public class IndexpageServiceImpl implements IndexpageService{

    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(IndexpageServiceImpl.class);

    @Resource
    private IndexpageDao indexpageDao;
    @Resource
    private Page page;

    /**
     * 开始遍历indexpage
     * 遍历指定topic的page
     * @param
     * @throws
     */
    public List<Page> listIndexPage() throws Exception {
        logger.info("listIndexPage() - start");
        logger.info("listIndexPage() - end");
        return indexpageDao.listIndexPage();
    }
    public List<Page> listTopicPage(String topic) throws Exception {
        logger.info("listTopicPage(String) - start");
        logger.info("listTopicPage(String) - end");
        return indexpageDao.listTopicPage(topic);
    }

    /**
     * 追加余下的indexpage
     * 追加余下的topicpage
     * @param
     * @throws
     */
    public List<Page> appendIndexpage(Integer beginpage) throws Exception {
        logger.info("appendIndexpage(Integer) - start");
        logger.info("appendIndexpage(Integer) - end");
        return indexpageDao.appendIndexpage(beginpage);
    }
    public List<Page> appendTopicpage(String topic,Integer beginpage) throws Exception {
        logger.info("appendTopicpage(String,Integer) - start");
        logger.info("appendTopicpage(String,Integer) - end");
        return indexpageDao.appendTopicpage(topic,beginpage);
    }

    /**
     * 遍历十大热门评论page
     * @param
     * @throws
     */
    public List<Page> listPageCommentSum() throws Exception {
        logger.info("listPageCommentSum() - start");
        logger.info("listPageCommentSum() - end");
        return indexpageDao.listPageCommentSum();
    }

    /**
     * 查询所有page中的指定page
     * 查询指定Topic的指定page
     * @param
     * @throws
     */
    public List<Page> listSearchAllpage(String search) throws Exception {
        logger.info("listSearchAllpage(String) - start");
        logger.info("listSearchAllpage(String) - end");
        return indexpageDao.listSearchAllpage(search);
    }
    public List<Page> listSearchTopicpage(String topic,String search) throws Exception {
        logger.info("listSearchTopicpage(String,String) - start");
        logger.info("listSearchTopicpage(String,String) - end");
        return indexpageDao.listSearchTopicpage(topic, search);
    }

}
