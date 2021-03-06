package com.quarusis.service.impl;

import com.quarusis.data.dao.PageDao;
import com.quarusis.data.entity.Comment;
import com.quarusis.data.entity.Heat;
import com.quarusis.data.entity.Page;
import com.quarusis.service.PageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("PageService")
public class PageServiceImpl implements PageService{

    @Resource
    private PageDao pageDao;

    /**
     * 通过page_id查询page
     */
    public Page showPage(Integer id) throws Exception {
        return pageDao.showPage(id);
    }

    /**
     * 添加用户评论
     */
    @Transactional
    public void commentUpload(Integer pid, Comment comment) throws Exception {
        pageDao.commentUpload(pid, comment);
        pageDao.plusPageCommentSum(pid);
        //这里先暂时不区分是否为用户自己的page，一律unread(只需要判断page_uin与session_uin相同即可不操作此步)
        pageDao.newComment(pid);
        if(null != pageDao.queryCommentHistoryByPid(Integer.valueOf(comment.getUin()), pid)) {
            pageDao.deleteCommentHistory(Integer.valueOf(comment.getUin()), pid);
        }
        pageDao.insertCommentHistory(Integer.valueOf(comment.getUin()), pid);
    }

    /**
     * 遍历page评论
     */
    public List<Comment> listNormalComment(Integer pid) throws Exception {
        return pageDao.listNormalComment(pid);
    }

    /**
     * 遍历用户热门评论top3
     */
    public List<Comment> listFireComment(Integer pid) throws Exception {
        return pageDao.listFireComment(pid);
    }

    /**
     * 用户已读新评论
     */
    public void readComment(Integer pid) throws Exception {
        pageDao.readComment(pid);
    }

    /**
     * 用户增加heat
     * 用户减少heat
     * 用户查询heat
     */
    public void plusCommentHeat(Integer uin, Integer pid, Integer cid) throws Exception {
        pageDao.plusCommentHeat(pid, cid);
        pageDao.insertHeat(uin, pid, cid);
    }
    public void subCommentHeat(Integer uin, Integer pid, Integer cid) throws Exception {
        pageDao.subCommentHeat(pid, cid);
        pageDao.removeHeat(uin, pid, cid);
    }
    public Heat queryHeat(Integer uin, Integer pid, Integer cid) throws Exception {
        return pageDao.queryHeat(uin, pid, cid);
    }

    /**
     * 删除Page操作
     */
    public void removePage(Integer pid) throws Exception {
        pageDao.removePage(pid);
    }

    /**
     * 评论其他人评论的功能(可以替代原评论功能commentUpload方法)
     * 只是一个新想法，暂时只写service层
     */
//    @Transactional
//    public void commentaryReview(Comment comment) throws Exception {

        // 略去增加评论总数(plusPageCommentSum())、刷新评论历史(deleteCommentHistory()、insertCommentHistory())等步骤

//        uin 评论人自己的uin
//        toUin 被评论的人的uin
//        id 子评论id(递增)
//        toId 母评论(若为0 则为该一系列评论对话的第一条评论，剩余评论若要加入评论对话，必须将toId设置为母评论的id)
//        以下的数据都可get
        /*
         先判断 toId 是否为 0
         若 为0
            则使用 id 作为 新行的toId
            插入新行
                toComment_id = this.id
                uin = this.uin
                toUin = this.toUin
                comment_text = this.comment_text
         若 非0
            则使用 toId
            插入新行
                toComment_id = this.toId
                uin = this.uin
                toUin = this.toUin
                comment_text = this.comment_text
          */

//    }

    /**
     * 评论其他人评论的功能(可以替代原评论功能commentUpload方法)
     * 只是一个新想法，暂时只写service层
     */
//    public List<Comment> commentaryDialogue(Comment comment) throws Exception {

//        uin 评论人自己的uin
//        toUin 被评论的人的uin
//        id 子评论id(递增)
//        toId 母评论(若为0 则为该一系列评论对话的第一条评论，剩余评论若要加入评论对话，必须将toId设置为母评论的id)
//        以下的数据都可get
//
        // 返回的List 为母评论与子评论拼装得来
        /*
         先判断 toId 是否为 0
         若 为0
            则此评论为顶级母评论，将其添加入List
            将 id 作为 toId 查询剩余 子评论(子评论的 toId == 母评论的 id)，添加入List
         若 非0
            则将 toId 作为 id 查询母评论，将其添加入List
            将 toId 作为 toId 查询所有子评论，将其添加入List
          */

//    }

}
