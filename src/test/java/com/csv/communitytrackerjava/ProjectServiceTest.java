package com.csv.communitytrackerjava;

import com.csv.communitytrackerjava.exception.RecordNotFoundException;
import com.csv.communitytrackerjava.model.Project;
import com.csv.communitytrackerjava.repository.ProjectRepository;
import com.csv.communitytrackerjava.service.ProjectService;
import com.csv.communitytrackerjava.service.ProjectServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {
    @Mock
    ProjectRepository projectRepository;

    @InjectMocks
    ProjectService projectService = new ProjectServiceImpl();

    Project Sample = new Project(1,"projectCode", "description", true);
    @Test
    public void testUpdateDesc() throws RecordNotFoundException {
        Mockito.when(projectRepository.findById(1))
                .thenReturn(Optional.ofNullable(Sample));
        Project NewSample = new Project(1,"projectCode", "new description", true);

        Project update = projectService.updateProject(NewSample, 1);

        assertEquals("NewDescription", update.getProjectDesc());
    }
}
