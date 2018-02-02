package com.quarusis.service;

import com.quarusis.data.entity.Page;

import java.util.List;

public interface IndexpageService {

    /**
     * 遍历所有用户动态
     * @param
     * @throws
     */
    List<Page> listAllPage() throws Exception;

    /**
     * 遍历十大热门评论page
     * @param
     * @throws
     */
    List<Page> listPageCommentSum() throws Exception;

}
