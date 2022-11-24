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

import javax.persistence.EntityExistsException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {
    @Mock
    ProjectRepository projectRepository;

    @InjectMocks
    ProjectService projectService = new ProjectServiceImpl();

    Project Sample = new Project(1, "projectCode", "description", true);

    @Test
    public void testUpdateDesc() throws RecordNotFoundException {
        Mockito.when(projectRepository.findById(anyInt()))
                .thenReturn(Optional.ofNullable(Sample));
        Project newSample = new Project();
        newSample.setProjectCode("newProjectCode");
        newSample.setProjectDesc("new description");

        Project update = projectService.updateProject(newSample, 1);

        assertEquals(1, update.getProjectId());
        assertEquals("NewDescription", update.getProjectDesc());
        assertEquals("newProjectCode", update.getProjectCode());
    }

    @Test
    public void testUpdateExistingCodeValidation() throws RecordNotFoundException {
        Mockito.when(projectRepository.findByProjectCode("projectCode"))
                .thenReturn(Sample);
        Mockito.when(projectRepository.findById(1))
                .thenReturn(Optional.ofNullable(Sample));
        Project newSample = new Project();
        newSample.setProjectCode("projectCode");
        newSample.setProjectDesc("new description");

        Throwable throwable = catchThrowable(()->Optional.ofNullable(projectService.updateProject(newSample, 1)));
        assertThat(throwable).isInstanceOf(EntityExistsException.class)
                .hasMessageContaining("Code already existing");
    }
}
