package com.csv.communitytrackerjava.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class ProjectAddDTO {

    private Integer projectId;

    @Size(max = 100)
    @NotEmpty
    @NotBlank
    private String projectDesc;

    @Size(max = 100)
    @NotEmpty
    @NotBlank
    private String projectCode;

    @NotNull
    private Boolean isActive;
    
}