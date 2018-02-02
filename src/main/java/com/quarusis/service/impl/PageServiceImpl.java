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
    public List<Comment> listComment(Integer pid) throws Exception {
        return pageDao.listComment(pid);
    }

    /**
     * 遍历用户热门评论top3
     */
    public List<Comment> listHeatComment(Integer pid) throws Exception {
        return pageDao.listHeatComment(pid);
    }

    /**
     * 用户已读新评论
     */
    public void readComment(Integer pid) throws Exception {
        pageDao.readComment(pid);
    }

    /**
     * 用户增加heat
     * 用户查询heat
     */
    public void plusCommentHeat(Integer uin, Integer pid, Integer cid) throws Exception {
        pageDao.plusCommentHeat(pid, cid);
        pageDao.insertHeat(uin, pid, cid);
    }
    public Heat queryHeat(Integer uin, Integer pid, Integer cid) throws Exception {
        return pageDao.queryHeat(uin, pid, cid);
    }

}
