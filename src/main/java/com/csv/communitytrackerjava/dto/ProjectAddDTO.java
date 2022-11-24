package com.csv.communitytrackerjava.dto;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProjectAddDTO {

    private Integer projectId;

    @Size(max = 100)
    @NotNull
    private String projectDesc;

    @Size(max = 100)
    @NotNull
    private String projectCode;

    @NotNull
    private Boolean isActive;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}