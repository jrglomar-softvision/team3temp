package com.academy.communitytrackerjava.model;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class ProjectDTO {

    private Integer projectId;

    @Size(max = 100)
    @NotNull
    private String projectDesc;

    @Size(max = 100)
    @NotNull
    private String projectCode;

    @NotNull
    @Min(value = 0)
    @Max(value = 1)
    private Integer isActive;

}