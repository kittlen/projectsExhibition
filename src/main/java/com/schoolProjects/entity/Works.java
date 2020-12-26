package com.schoolProjects.entity;

/**
 * @author kittlen
 * @version 1.0
 * @date 2020/8/26 16:40
 */
public class Works {
    private String worksId;//作品id
    private User user;//作者id
    private String worksTitle;//作品标题
    private WorksType worksType;//作品类型
    private String worksIntroduction;//作品介绍
    private String worksUris;//作品地址
    private String worksStyle;//作品状态
    private String worksCover;//作品封面
    private String createdDate;//创建时间

    public String getWorksId() {
        return worksId;
    }

    public void setWorksId(String worksId) {
        this.worksId = worksId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getWorksTitle() {
        return worksTitle;
    }

    public void setWorksTitle(String worksTitle) {
        this.worksTitle = worksTitle;
    }

    public WorksType getWorksType() {
        return worksType;
    }

    public void setWorksType(WorksType worksType) {
        this.worksType = worksType;
    }

    public String getWorksIntroduction() {
        return worksIntroduction;
    }

    public void setWorksIntroduction(String worksIntroduction) {
        this.worksIntroduction = worksIntroduction;
    }

    public String getWorksUris() {
        return worksUris;
    }

    public void setWorksUris(String worksUris) {
        this.worksUris = worksUris;
    }

    public String getWorksStyle() {
        return worksStyle;
    }

    public void setWorksStyle(String worksStyle) {
        this.worksStyle = worksStyle;
    }

    public String getWorksCover() {
        return worksCover;
    }

    public void setWorksCover(String worksCover) {
        this.worksCover = worksCover;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
