package com.academy.communitytrackerjava.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {


    private Integer projectId;

    @Column(name = "projectdesc", nullable = false)
    @Size(max = 100)
    @NotNull
    private String projectDesc;

    @Column(name = "projectcode", unique = true, nullable = false)
    @Size(max = 100)
    @NotNull
    private String projectCode;

    @Column(name = "isactive", nullable = false)
    @NotNull
    private Integer isActive;

}