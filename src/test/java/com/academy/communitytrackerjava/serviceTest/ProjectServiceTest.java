package com.academy.communitytrackerjava.serviceTest;

import com.academy.communitytrackerjava.exception.IsActiveNotZeroOrOneException;
import com.academy.communitytrackerjava.model.ProjectDTO;
import com.academy.communitytrackerjava.repository.ProjectRepository;
import com.academy.communitytrackerjava.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {
    ProjectDTO magenic, magenic2, camelCase;
    @Spy
    ModelMapper modelMapper;

    @Mock
    private ProjectRepository projectRepository;
    @InjectMocks
    private ProjectService projectService;

    @BeforeEach
    void setup() {
        magenic = new ProjectDTO(1, "MagenicPDPBench", "MDP-101", 1);
        magenic2 = new ProjectDTO(1, "MagenicPDPBench", "MDP-101", 2);
        camelCase = new ProjectDTO(1, "Magenic Philippines", "MP-101", 1);
    }

    @Test()
    public void saveProject() {

        //Act
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ProjectDTO newProject = projectService.saveProject(magenic); //first instance
        //Assert
        //Newly created project should be not null
        assertNotNull(newProject);


        //Newly created project id should auto generate; it should be not null
        assertEquals(magenic, newProject);
    }

    @Test()
    public void saveProjectWithThrowsException() {
        //Assert
        assertThrows(IsActiveNotZeroOrOneException.class, () -> projectService.saveProject(magenic2));
    }

    @Test()
    public void saveProjectToCamelCase() {
        //Arrange

        //Act
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ProjectDTO newProject = projectService.saveProject(camelCase);
        //Assert
        assertNotNull(camelCase);
        assertEquals(newProject.getProjectDesc(), "MagenicPhilippines");
    }

}