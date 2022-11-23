package com.academy.communitytrackerjava.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "project")
@Data
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "projectid")
    private Integer projectId;

    @Column(name = "projectdesc", length = 100, nullable = false)
    @Size(max = 100)
    @NotNull
    private String projectDesc;

    @Column(name = "projectcode", length = 100, unique = true, nullable = false)
    @Size(max = 100)
    @NotNull
    private String projectCode;

    @Column(name = "isactive", nullable = false)
    @NotNull
    private Boolean isActive;

}