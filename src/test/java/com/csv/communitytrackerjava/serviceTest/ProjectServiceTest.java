package com.csv.communitytrackerjava.serviceTest;

import com.csv.communitytrackerjava.exception.RecordNotFoundException;
import com.csv.communitytrackerjava.model.Project;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {
    Project magenic, lorem;
    @Mock
    private ProjectRepository projectRepository;
    @InjectMocks
    private ProjectServiceImpl projectService;


    @BeforeEach
    void setup() {
        magenic = new Project(1, "MagenicPDPBench", "MDP-101", true);
        lorem = new Project(2, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Integer vulputate laoreet lacus, id rhoncus leo ultricies vitae. Nunc et imperdiet ipsum, " +
                "molestie porta justo. Aenean commodo porttitor posuere. Cras ac sapien ac justo lacinia egestas ac a justo. " +
                "Nunc vulputate, turpis volutpat elementum varius, velit neque congue metus, at vulputate tellus urna commodo dolor. " +
                "In at consectetur diam, vel molestie augue. Vivamus non fringilla diam. Duis non sem gravida, posuere est at, consequat libero. " +
                "Quisque id rutrum velit, quis convallis libero. Quisque ac nisi quis orci sodales molestie. Etiam non diam est. " +
                "Mauris fermentum eros ac euismod sagittis el de jojo.", "LOREM-102", true);

    }

    @Test
    public void saveProject() {
        //Arrange
        when(projectRepository.save(any(Project.class))).thenReturn(magenic);
        //Act
        Project newProject = projectService.saveProject(magenic);
        //Assert
        //Newly created project should be not null
        assertNotNull(newProject);

        //Newly created project id should auto generate; it should be not null
        assertEquals(newProject, magenic);
    }

    @Test
    public void saveProjectWithLimitsInProjectCode() {
        when(projectRepository.save(any(Project.class))).thenReturn(lorem);
        //Act
        Project newProject = projectService.saveProject(lorem);
        //Assert
        //Newly created project should be not null
        assertNotNull(newProject);

        //Newly created project id should auto generate; it should be not null
        assertThat(100).isLessThanOrEqualTo(newProject.getProjectCode().length());
        assertThrows(ResponseStatusException.class, () -> projectService.save(lorem));
//        assertThat(newProject.getProjectCode()).isNotNull().hasSize(100);
    }
    @Test
    public void saveProjectWithLimitsInProjectDesc() {
        when(projectRepository.save(any(Project.class))).thenReturn(lorem);
        //Act
        Project newProject = projectService.saveProject(lorem);
        //Assert
        //Newly created project should be not null
        assertNotNull(newProject);

        //Newly created project id should auto generate; it should be not null
        assertThat(100).isLessThan(newProject.getProjectCode().length());
        assertThrows(ResponseStatusException.class, () -> projectService.save(lorem));
//        assertThat(newProject.getProjectCode()).isNotNull().hasSize(100);
    }

    @Test
    public void saveProjectWithNoDuplicates() {
        when(projectRepository.findOne(anyString()))
                .thenReturn(magenic).thenThrow(RecordNotFoundException.class);
        //Act
        Project newProject = projectService.saveProject(magenic);
        //Assert
        //Newly created project should be not null
        assertNotNull(newProject);

        //Newly created project id should auto generate; it should be not null
        assertNotEquals(newProject,magenic);
//        assertThat(newProject.getProjectCode()).isNotNull().hasSize(100);
    }
}
