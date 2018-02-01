package com.quarusis.service.impl;

import com.quarusis.data.dao.IndexpageDao;
import com.quarusis.data.entity.Page;
import com.quarusis.service.IndexpageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("IndexpageService")
public class IndexpageServiceImpl implements IndexpageService{

    @Resource
    private IndexpageDao indexpageDao;
    @Resource
    private Page page;

    /**
     * 遍历所有用户动态
     * @param
     * @throws
     */
    public List<Page> listAllPage() throws Exception {
        return indexpageDao.listAllPage();
    }


}
