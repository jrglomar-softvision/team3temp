package com.csv.communitytrackerjava.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "project")
public class Project extends BaseAuditClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="projectid", updatable = false, nullable = false)
    private Integer projectId;

    @Column(name = "projectdesc", length = 100, nullable = false)
    private String projectDesc;

    @Column(name="projectcode", length = 100, unique = true, nullable = false)
    private String projectCode;

    @Column(name="isactive", nullable = false)
    private Boolean isActive;

    @OneToMany(mappedBy = "projectId", fetch = FetchType.LAZY)
    private List<People> peoples;

    public Project(Integer projectId, String projectDesc, String projectCode, Boolean isActive) {
        this.projectId = projectId;
        this.projectDesc = projectDesc;
        this.projectCode = projectCode;
        this.isActive = isActive;
    }
}
