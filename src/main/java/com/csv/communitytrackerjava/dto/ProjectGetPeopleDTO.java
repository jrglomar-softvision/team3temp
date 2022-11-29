package com.csv.communitytrackerjava.dto;

import com.csv.communitytrackerjava.model.People;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectGetPeopleDTO {
    
}
