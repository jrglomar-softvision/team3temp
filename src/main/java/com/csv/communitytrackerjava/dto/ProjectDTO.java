package com.csv.communitytrackerjava.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {

    @NotNull
    private Integer projectId;

    @Size(max = 100)
    @NotNull
    private String projectDesc;

    @Size(max = 100)
    @NotNull
    private String projectCode;

}
