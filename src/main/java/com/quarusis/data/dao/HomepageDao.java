package com.quarusis.data.dao;

import com.quarusis.data.entity.Page;

import java.util.List;

public interface HomepageDao {

    /**
     * 用户上传page
     * @param
     * @return
     */
    void pageUpload(Page page);

    /**
     * 通过uin查询其最新page
     * @param
     * @return
     */
    Page queryPageByUin(Integer uin);

    /**
     * 添加page的评论表
     * @param
     * @return
     */
    void createPageComment(Integer pid);

    /**
     * 遍历用户的page
     * @param
     * @return
     */
    List<Page> listPage(String uin);

}
