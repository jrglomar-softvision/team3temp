package com.csv.communitytrackerjava.dto;

import javax.validation.constraints.Size;

public class ProjectUpdateDTO {

    @Size(max = 100)
    private String projectDesc;

    @Size(max = 100)
    private String projectCode;

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
}
