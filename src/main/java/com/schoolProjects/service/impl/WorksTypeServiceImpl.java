package com.schoolProjects.service.impl;

import com.schoolProjects.entity.WorksType;
import com.schoolProjects.mapper.WorksTypeDao;
import com.schoolProjects.service.WorksTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author kittlen
 * @version 1.0
 * @date 2020/8/26 16:33
 */
@Service
public class WorksTypeServiceImpl implements WorksTypeService {

    @Autowired
    private WorksTypeDao worksTypeDao;


    @Override
    public List<WorksType> selectAll() {
        return worksTypeDao.selectAll();
    }

    @Override
    public void addType(WorksType worksType) {
        worksTypeDao.addType(worksType);
    }

    @Override
    public void deleteType(WorksType worksType) {
        worksTypeDao.deleteType(worksType);
    }
}
