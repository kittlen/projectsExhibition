package com.schoolProjects.entity;

/**
 * @author kittlen
 * @version 1.0
 * @date 2020/8/26 16:33
 */
public class WorksType {

    private String typeId;//类型id
    private String typeName;//类型名
    private String createdDate;//创建时间

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
