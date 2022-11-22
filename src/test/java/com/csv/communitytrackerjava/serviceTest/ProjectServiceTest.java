package com.csv.communitytrackerjava.serviceTest;

import com.csv.communitytrackerjava.model.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {
    Project magenic;
    @Mock
    private ProjectRepository projectRepository;
    @InjectMocks
    private ProjectServiceImpl projectService;


    @BeforeEach
    void setup() {
        magenic = new Project(1, "MagenicPDPBench", "MDP-101", true);

    }

    @Test
    public void saveProject() {
        //Arrange
        when(projectRepository.findAll()).thenReturn(List.of(magenic));
        //Act
        Project newProject = projectService.saveProject(magenic);
        //Assert
        //Newly created project should be not null
        assertNotNull(newProject);

        //Newly created project id should auto generate; it should be not null
        assertThat(newProject.getProjectCode()).isEqualTo("MDP-101");
    }
}
