package com.academy.communitytrackerjava.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
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