package com.csv.communitytrackerjava.dto;


import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class ProjectUpdateDTO {

    @Size(max = 100)
    private String projectDesc;

    @Size(max = 100)
    private String projectCode;

}
