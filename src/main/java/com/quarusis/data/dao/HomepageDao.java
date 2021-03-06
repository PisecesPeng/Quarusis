package com.quarusis.data.dao;

import com.quarusis.data.entity.Page;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 遍历用户历史评论文章
     * @param
     * @throws
     */
    List<Page> listUserHistory(Integer uin);

    /**
     * 查询homepage中的指定page
     * @param
     * @throws
     */
    List<Page> listSearchHomepage(@Param("uin")String uin, @Param("search")String search);

}
