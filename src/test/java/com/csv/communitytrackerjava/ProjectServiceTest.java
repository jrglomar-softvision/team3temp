package com.csv.communitytrackerjava;

import com.csv.communitytrackerjava.dto.ProjectDTO;
import com.csv.communitytrackerjava.exception.RecordNotFoundException;
import com.csv.communitytrackerjava.model.Project;
import com.csv.communitytrackerjava.repository.ProjectRepository;
import com.csv.communitytrackerjava.service.ProjectService;
import com.csv.communitytrackerjava.service.ProjectServiceImpl;
import com.googlecode.catchexception.ThrowingCallable;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import javax.persistence.EntityExistsException;
import java.util.Optional;

import static com.googlecode.catchexception.apis.BDDCatchException.thenThrown;
import static com.googlecode.catchexception.apis.BDDCatchException.when;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.BDDAssertions.then;
import static com.googlecode.catchexception.CatchException.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {
    @Mock
    ProjectRepository projectRepository;

    @InjectMocks
    ProjectService projectService = new ProjectServiceImpl();

    Project Sample = new Project(1, "projectCode", "description", true);

    @Test
    public void testUpdateDesc() throws RecordNotFoundException {
        Mockito.when(projectRepository.findById(1))
                .thenReturn(Optional.ofNullable(Sample));
        Project newSample = new Project();
        newSample.setProjectCode("newProjectCode");
        newSample.setProjectDesc("new description");

        ProjectDTO update = projectService.updateProject(newSample, 1);

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

//            Optional<Project> update = Optional.ofNullable(projectService.updateProject(NewSample2, 1));
//            Optional.ofNullable(projectService.updateProject(NewSample2, 1));
//            catchException((ThrowingCallable) projectService.updateProject(newSample, 1));
//            assert caughtException()instanceof EntityExistsException;

//            when((ThrowingCallable) );
//            then(Optional.ofNullable(caughtException()))
//                    .isInstanceOf(EntityExistsException.class);
//                assertThatExceptionOfType(EntityExistsException.class)
//                        .isThrownBy((ThrowableAssert.ThrowingCallable) projectService.updateProject(NewSample2, 1))
//                        .withMessage("Code already existing");
//        assertTrue(false);

        Throwable throwable = catchThrowable(()->Optional.ofNullable(projectService.updateProject(newSample, 1)));
        assertThat(throwable).isInstanceOf(EntityExistsException.class)
                .hasMessageContaining("Code already existing");
    }
}
