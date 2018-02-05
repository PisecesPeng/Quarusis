package com.quarusis.service;

import com.quarusis.data.entity.Page;

import java.util.List;

public interface IndexpageService {

    /**
     * 遍历所有用户动态
     * 遍历指定topic的page
     * @param
     * @throws
     */
    List<Page> listAllPage() throws Exception;
    List<Page> listTopicPage(String topic) throws Exception;

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
