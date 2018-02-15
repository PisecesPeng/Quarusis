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
     * 开始遍历indexpage
     * 遍历指定topic的page
     * @param
     * @throws
     */
    public List<Page> listIndexPage() throws Exception {
        return indexpageDao.listIndexPage();
    }
    public List<Page> listTopicPage(String topic) throws Exception {
        return indexpageDao.listTopicPage(topic);
    }

    /**
     * 追加余下的indexpage
     * 追加余下的topicpage
     * @param
     * @throws
     */
    public List<Page> appendIndexpage(Integer beginpage) throws Exception {
        return indexpageDao.appendIndexpage(beginpage);
    }
    public List<Page> appendTopicpage(String topic,Integer beginpage) throws Exception {
        return indexpageDao.appendTopicpage(topic,beginpage);
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
