package com.csv.communitytrackerjava.service;

import com.csv.communitytrackerjava.dto.ProjectGetPeopleDTO;
import com.csv.communitytrackerjava.dto.ProjectResponseDTO;
import com.csv.communitytrackerjava.exception.RecordNotFoundException;
import com.csv.communitytrackerjava.mapper.ProjectMapper;
import com.csv.communitytrackerjava.mapper.ProjectMapperImpl;
import com.csv.communitytrackerjava.model.People;
import com.csv.communitytrackerjava.model.Project;
import com.csv.communitytrackerjava.repository.ProjectRepository;
import jdk.jfr.Name;
import net.bytebuddy.implementation.bytecode.Throw;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProjectServiceImplTest {
    @Mock
    ProjectRepository projectRepository;

    @InjectMocks
    ProjectService projectService = new ProjectServiceImpl();

    @Spy
    ProjectMapper projectMapper = new ProjectMapperImpl();

    
    People peopleOne;
    Project projectTestOne;
    Pageable pageable;
    List<Project> projectList;
    
    @BeforeEach
    void setup() {
        peopleOne = new People(1, 1, 1, "John Raven", "Tamang", "Glomar", "Job Level Desc", "CSV", "TestProjectDesc1", true);
        projectTestOne = new Project(1, "TestProjectDesc1", "TPC1", true, List.of(peopleOne));
        pageable = PageRequest.of(0, 1);
        projectList = List.of(projectTestOne);
    }

    @Test
    @DisplayName("Find people by project id with pagination test")
    void findPeopleByProjectId() throws Exception {
       
        Mockito.when(projectRepository.findByProjectId(Mockito.anyInt())).thenReturn(projectList);

        PageImpl<ProjectGetPeopleDTO> result = projectService.findPeopleByProjectId(pageable, 1);
        
        Mockito.verify(projectRepository).findByProjectId(1);
        assertEquals(1, result.getContent().size());
        assertEquals("TPC1", result.getContent().get(0).getProjectCode());
        assertEquals("Glomar", result.getContent().get(0).getPeoples().get(0).getLastName());
    }
    
    @Test
    @DisplayName("Find people by project id with record not found test")
    void findPeoplByProjectId() throws Exception {
        Pageable pageable = PageRequest.of(0, 1);

        Throwable exception = assertThrows(RecordNotFoundException.class, () -> projectService.findPeopleByProjectId(pageable, 1));
        
        Mockito.verify(projectRepository).findByProjectId(1);
        assertEquals("Project doesn't exist", exception.getMessage());
    }
}