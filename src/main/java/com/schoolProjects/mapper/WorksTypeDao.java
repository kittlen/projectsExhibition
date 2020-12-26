package com.schoolProjects.mapper;

import com.schoolProjects.entity.WorksType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author kittlen
 * @version 1.0
 * @date 2020/8/26 17:36
 */
@Repository
public interface WorksTypeDao {
    //查询所有的项目类型
    List<WorksType> selectAll();

    //添加项目类型
    void addType(WorksType worksType);

    //删除项目类型
    void deleteType(WorksType worksType);
}
