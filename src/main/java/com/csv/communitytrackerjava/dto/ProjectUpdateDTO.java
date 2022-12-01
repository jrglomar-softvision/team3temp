package com.csv.communitytrackerjava.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectUpdateDTO {

    @Size(max = 100)
    private String projectDesc;

    @Size(max = 100)
    private String projectCode;

}
