package com.schoolProjects.mapper;

import com.schoolProjects.entity.Works;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author kittlen
 * @version 1.0
 * @date 2020/8/26 17:36
 */
@Repository
public interface WorksDao {
    //根据条件查询项目信息
    List<Works> selectWorksBy(@Param("works")Works works, @Param("pageNumber")int pageNumber, @Param("pageSize")int pageSize);
    //添加项目
    void addWorks(Works works);
    //查询项目的条数
    int getAllCountBy(Works works);
    //删除项目
    void delete(Works works);

    //查询具体项目信息
    List<Works> selectWorksById(String worksId);
}
