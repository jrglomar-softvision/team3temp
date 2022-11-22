package com.csv.communitytrackerjava.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project extends BaseAuditClass {

    @Id
    @Column
    private Integer projectId;
    
    @Size(max=100)
    @Column(length = 100, unique = true)
    private String projectCode;

    @Size(max=100)
    @Column(length = 100)
    private String projectDesc;

    @Column
    private Boolean isActive = true;

}
