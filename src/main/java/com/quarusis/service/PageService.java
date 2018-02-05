package com.quarusis.service;

import com.quarusis.data.entity.Comment;
import com.quarusis.data.entity.Heat;
import com.quarusis.data.entity.Page;

import java.util.List;

public interface PageService {

    /**
     * 通过page_id查询page
     */
    Page showPage(Integer id) throws Exception;

    /**
     * 添加用户评论
     */
    void commentUpload(Integer pid, Comment comment) throws Exception;

    /**
     * 遍历page评论
     */
    List<Comment> listComment(Integer pid) throws Exception;

    /**
     * 遍历用户热门评论top3
     * @param
     * @return
     */
    List<Comment> listHeatComment(Integer pid) throws Exception;

    /**
     * 用户已读新评论
     */
    void readComment(Integer pid) throws Exception;

    /**
     * 用户增加heat
     * 用户查询heat
     */
    void plusCommentHeat(Integer uin, Integer pid, Integer cid) throws Exception;
    Heat queryHeat(Integer uin, Integer pid, Integer cid) throws Exception;

    /**
     * 删除Page操作
     */
    void removePage(Integer pid) throws Exception;

}
