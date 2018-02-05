package com.quarusis.service.impl;

import com.quarusis.data.dao.IndexpageDao;
import com.quarusis.data.entity.Page;
import com.quarusis.service.IndexpageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("IndexpageService")
public class IndexpageServiceImpl implements IndexpageService{

    @Resource
    private IndexpageDao indexpageDao;
    @Resource
    private Page page;

    /**
     * 遍历所有用户动态
     * 遍历指定topic的page
     * @param
     * @throws
     */
    public List<Page> listAllPage() throws Exception {
        return indexpageDao.listAllPage();
    }
    public List<Page> listTopicPage(String topic) throws Exception {
        return indexpageDao.listTopicPage(topic);
    }

    /**
     * 遍历十大热门评论page
     * @param
     * @throws
     */
    public List<Page> listPageCommentSum() throws Exception {
        return indexpageDao.listPageCommentSum();
    }

    /**
     * 查询所有page中的指定page
     * 查询指定Topic的指定page
     * @param
     * @throws
     */
    public List<Page> listSearchAllpage(String search) throws Exception {
        return indexpageDao.listSearchAllpage(search);
    }
    public List<Page> listSearchTopicpage(String topic,String search) throws Exception {
        return indexpageDao.listSearchTopicpage(topic, search);
    }

}
