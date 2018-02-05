package com.quarusis.data.dao;

import com.quarusis.data.entity.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IndexpageDao {

    /**
     * 遍历所有的page
     * @param
     * @return
     */
    List<Page> listAllPage();

    /**
     * 遍历指定topic的page
     * @param
     * @return
     */
    List<Page> listTopicPage(String topic);

    /**
     * 遍历十大热门评论page
     * @param
     * @throws
     */
    List<Page> listPageCommentSum();

    /**
     * 查询所有page中的指定page
     * 查询指定Topic的指定page
     * @param
     * @throws
     */
    List<Page> listSearchAllpage(String search);
    List<Page> listSearchTopicpage(@Param("topic")String topic,@Param("search")String search);

}
