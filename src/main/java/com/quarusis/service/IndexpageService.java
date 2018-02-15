package com.quarusis.service;

import com.quarusis.data.entity.Page;

import java.util.List;

public interface IndexpageService {

    /**
     * 开始遍历indexpage
     * 遍历指定topic的page
     * @param
     * @throws
     */
    List<Page> listIndexPage() throws Exception;
    List<Page> listTopicPage(String topic) throws Exception;

    /**
     * 追加余下的indexpage
     * 追加余下的topicpage
     * @param
     * @throws
     */
    List<Page> appendIndexpage(Integer beginpage) throws Exception;
    List<Page> appendTopicpage(String topic,Integer beginpage) throws Exception;

    /**
     * 遍历十大热门评论page
     * @param
     * @throws
     */
    List<Page> listPageCommentSum() throws Exception;

    /**
     * 查询所有page中的指定page
     * 查询指定Topic的指定page
     * @param
     * @throws
     */
    List<Page> listSearchAllpage(String search) throws Exception;
    List<Page> listSearchTopicpage(String topic,String search) throws Exception;

}
