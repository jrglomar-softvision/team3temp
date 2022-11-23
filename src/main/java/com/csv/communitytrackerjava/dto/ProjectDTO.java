package com.csv.communitytrackerjava.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {
    private Integer projectId;

    private String projectCode;

    private String projectDesc;

    private Boolean isActive;
}
