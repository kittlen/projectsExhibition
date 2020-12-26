package com.schoolProjects.service;


import com.schoolProjects.entity.Works;

import java.util.List;

/**
 * @author kittlen
 * @version 1.0
 * @date 2020/8/26 16:33
 */
public interface WorksService {

    //根据条件查询项目信息
    List<Works> selectWorksBy(Works works,int pageNumber,int pageSize);
    //添加项目
    void addWorks(Works works);
    //查询项目的条数
    int getAllCountBy(Works works);
    //删除项目
    void delete(Works works);

    //查询具体项目信息
    List<Works> selectWorksById(String worksId);

}
