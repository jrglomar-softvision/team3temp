package com.csv.communitytrackerjava.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class ProjectUpdateDTO {

    @Size(max = 100)
    private String projectDesc;

    @Size(max = 100)
    private String projectCode;

}
