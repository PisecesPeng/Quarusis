package com.quarusis.data.dao;

import com.quarusis.data.entity.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IndexpageDao {

    /**
     * 开始遍历indexpage
     * 遍历余下indexpage
     * @param
     * @return
     */
    List<Page> listIndexPage();
    List<Page> appendIndexpage(Integer beginpage);

    /**
     * 遍历指定topic的page
     * 遍历余下topic的page
     * @param
     * @return
     */
    List<Page> listTopicPage(String topic);
    List<Page> appendTopicpage(@Param("topic")String topic,@Param("beginpage")Integer beginpage);

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
