package com.quarusis.service.impl;

import com.quarusis.data.dao.HomepageDao;
import com.quarusis.data.entity.Page;
import com.quarusis.service.HomepageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("HomepageService")
public class HomepageServiceImpl implements HomepageService {

    @Resource
    private HomepageDao homepageDao;
    @Resource
    private Page page;

    /**
     * 用户上传page
     * @param
     * @throws
     */
    public void pageUpload(Page page) throws Exception {
        homepageDao.pageUpload(page);
        page = homepageDao.queryPageByUin(Integer.valueOf(page.getUin()));
        if (page.getWhetherComment() == 1) {
            homepageDao.createPageComment(page.getId());
        }
    }

    /**
     * 遍历用户动态
     * @param
     * @throws
     */
    public List<Page> listPage(String uin) throws Exception {
        System.out.println("遍历用户动态");
        return homepageDao.listPage(uin);
    }

}
