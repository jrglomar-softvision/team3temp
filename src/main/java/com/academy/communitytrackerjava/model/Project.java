package com.academy.communitytrackerjava.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "project")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "projectid")
    private Integer projectId;

    @Column(name = "projectdesc", length = 100, nullable = false)
    @NotNull
    private String projectDesc;

    @Column(name = "projectcode", length = 100, unique = true, nullable = false)
    @NotNull
    private String projectCode;

    @Column(name = "isactive", nullable = false)
    @NotNull
    private Boolean isActive;

}