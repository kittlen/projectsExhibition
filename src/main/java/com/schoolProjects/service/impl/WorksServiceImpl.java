package com.schoolProjects.service.impl;

import com.schoolProjects.entity.User;
import com.schoolProjects.entity.Works;
import com.schoolProjects.mapper.UserDao;
import com.schoolProjects.mapper.WorksDao;
import com.schoolProjects.service.UserService;
import com.schoolProjects.service.WorksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author kittlen
 * @version 1.0
 * @date 2020/8/26 16:33
 */
@Service
public class WorksServiceImpl implements WorksService {

    @Autowired
    private WorksDao worksDao;


    @Override
    public List<Works> selectWorksBy(Works works,int pageNumber,int pageSize) {
        return worksDao.selectWorksBy(works,pageNumber,pageSize);
    }

    @Override
    public void addWorks(Works works) {
        worksDao.addWorks(works);
    }

    @Override
    public int getAllCountBy(Works works) {
        return worksDao.getAllCountBy(works);
    }

    @Override
    public void delete(Works works) {
        worksDao.delete(works);
    }

    @Override
    public List<Works> selectWorksById(String worksId) {
        return worksDao.selectWorksById(worksId);
    }
}
