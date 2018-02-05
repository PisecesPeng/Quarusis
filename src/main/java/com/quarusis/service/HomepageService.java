package com.quarusis.service;

import com.quarusis.data.entity.Page;

import java.util.List;

public interface HomepageService {

    /**
     * 用户上传page
     * @param
     * @throws
     */
    void pageUpload(Page page) throws Exception;

    /**
     * 遍历用户动态
     * @param
     * @throws
     */
    List<Page> listPage(String uin) throws Exception;

    /**
     * 遍历用户历史评论文章
     * @param
     * @throws
     */
    List<Page> listUserHistory(Integer uin) throws Exception;

    /**
     * 查询homepage中的指定page
     * @param
     * @throws
     */
    List<Page> listSearchHomepage(String uin,String search) throws Exception;

}
