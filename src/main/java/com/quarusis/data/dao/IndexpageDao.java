package com.quarusis.data.dao;

import com.quarusis.data.entity.Page;

import java.util.List;

public interface IndexpageDao {

    /**
     * 遍历所有的page
     * @param
     * @return
     */
    List<Page> listAllPage();

    /**
     * 遍历十大热门评论page
     * @param
     * @throws
     */
    List<Page> listPageCommentSum();

}
