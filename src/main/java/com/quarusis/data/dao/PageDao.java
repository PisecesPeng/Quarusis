package com.quarusis.data.dao;

import com.quarusis.data.entity.Comment;
import com.quarusis.data.entity.Heat;
import com.quarusis.data.entity.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PageDao {

    /**
     * 通过page_id查询page
     * @param
     * @return
     */
    Page showPage(Integer id);

    /**
     * 遍历用户的page
     * @param
     * @return
     */
    List<Comment> listComment(Integer pid);

    /**
     * 添加用户评论
     * @param
     * @return
     */
    void commentUpload(@Param("pid")Integer pid, @Param("comment")Comment comment);

    /**
     * 增加page的评论数量
     * @param
     * @return
     */
    void plusPageCommentSum(Integer pid);

    /**
     * 添加用户评论历史
     * 删除用户评论历史
     * 查询用户评论历史
     * @param
     * @return
     */
    void insertCommentHistory(@Param("uin")Integer uin, @Param("pid")Integer pid);
    void deleteCommentHistory(@Param("uin")Integer uin, @Param("pid")Integer pid);
    Page queryCommentHistoryByPid(@Param("uin")Integer uin, @Param("pid")Integer pid);

    /**
     * 遍历用户热门评论top3
     * @param
     * @return
     */
    List<Comment> listHeatComment(Integer pid);

    /**
     * 增加heat热度
     * 写入heat表
     * 查询heat表
     * @param
     * @return
     */
    void plusCommentHeat(@Param("pid")Integer pid, @Param("cid")Integer cid);
    void insertHeat(@Param("uin")Integer uin, @Param("pid")Integer pid, @Param("cid")Integer cid);
    Heat queryHeat(@Param("uin")Integer uin, @Param("pid")Integer pid, @Param("cid")Integer cid);

    /**
     * page有未阅读的新评论
     * page所有新评论已阅读
     */
    void newComment(Integer pid);
    void readComment(Integer pid);
}
