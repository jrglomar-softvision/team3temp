package com.csv.communitytrackerjava.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "project")
public class Project extends BaseAuditClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Integer projectId;

    @Column(name = "projectdesc", length = 100, nullable = false)
    private String projectDesc;

    @Column(name="projectcode", length = 100, unique = true, nullable = false)
    private String projectCode;

    @Column(name="isactive", nullable = false)
    private Boolean isActive;

}
