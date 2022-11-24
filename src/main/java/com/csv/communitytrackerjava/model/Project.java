package com.csv.communitytrackerjava.model;
import lombok.*;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="projects")
public class Project extends BaseAuditClass{

    @Id
    @Column(updatable = false, nullable = false)
    private Integer projectId;

    @Size(max = 100)
    @Column(length = 100, unique = true, nullable = false)
    private String projectCode;

    @Size(max = 100)
    @Column(length = 100, nullable = false)
    private String projectDesc;

    @Column(nullable = false)
    private Boolean isActive;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
