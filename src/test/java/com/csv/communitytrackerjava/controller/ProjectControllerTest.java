package com.csv.communitytrackerjava.controller;

import com.csv.communitytrackerjava.dto.ProjectGetPeopleDTO;
import com.csv.communitytrackerjava.dto.ProjectPayloadDTO;
import com.csv.communitytrackerjava.dto.ProjectResponseDTO;
import com.csv.communitytrackerjava.dto.ProjectUpdateDTO;
import com.csv.communitytrackerjava.exception.RecordNotFoundException;
import com.csv.communitytrackerjava.model.People;
import com.csv.communitytrackerjava.model.Project;
import com.csv.communitytrackerjava.service.ExceptionService;
import com.csv.communitytrackerjava.service.ProjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class ProjectControllerTest {
    @MockBean
    private ProjectService projectService;

    @MockBean
    private ExceptionService exceptionService;

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;


    People peopleOne;
    ProjectGetPeopleDTO projectGetPeopleDTO;
    Pageable pageable;
    List<ProjectGetPeopleDTO> projectList;
    PageImpl<ProjectGetPeopleDTO> pageProjectList;
    
    @BeforeEach
    void setup() {
        peopleOne = new People(1, 1, 1, "John Raven", "Tamang", "Glomar", "Job Level Desc", "CSV", "TestProjectDesc1", true);
        projectGetPeopleDTO = new ProjectGetPeopleDTO(1, "TestProjectDesc1", "TPC1", true, List.of(peopleOne));
        pageable = PageRequest.of(0, 1);
        projectList = List.of(projectGetPeopleDTO);
        pageProjectList = new PageImpl<>(projectList, pageable, projectList.size());
    }


    @Test
    void findPeopleByProjectId() throws Exception{
        Mockito.when(projectService.findPeopleByProjectId(Mockito.any(), Mockito.any())).thenReturn(pageProjectList);
        
        mockMvc.perform(get("/projects/people?projectId=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pageProjectList)))
                .andExpect(jsonPath("$.size", CoreMatchers.is(pageProjectList.getSize())))
                .andExpect(jsonPath("$.content[0].projectId", CoreMatchers.is(pageProjectList.getContent().get(0).getProjectId())))
                .andExpect(jsonPath("$.content[0].peoples[0].projectId", CoreMatchers.is(pageProjectList.getContent().get(0).getPeoples().get(0).getProjectId())))
                .andExpect(status().isAccepted());
    }

    @Test
    @DisplayName("Find people by project id with pagination test")
    void findPeopleByProjectIdRecordNotFound() throws Exception {


        Mockito.when(projectService.findPeopleByProjectId(Mockito.any(), Mockito.any()))
                .thenThrow(new RecordNotFoundException("Project doesn't exist."));

        mockMvc.perform(get("/projects/people?projectId=100")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pageProjectList)))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof RecordNotFoundException))
                .andExpect(result -> assertEquals("Project doesn't exist.", result.getResolvedException().getMessage()))
                .andExpect(status().isBadRequest());

    }

}