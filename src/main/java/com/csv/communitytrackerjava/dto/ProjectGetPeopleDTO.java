package com.csv.communitytrackerjava.dto;

import com.csv.communitytrackerjava.model.BaseAuditClass;
import com.csv.communitytrackerjava.model.People;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectGetPeopleDTO {
    
    private Integer projectId;

    private String projectDesc;

    private String projectCode;

    private Boolean isActive;

    private List<People> peoples;

}
