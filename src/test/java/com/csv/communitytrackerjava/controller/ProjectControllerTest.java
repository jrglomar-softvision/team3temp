package com.csv.communitytrackerjava.controller;

import com.csv.communitytrackerjava.dto.ProjectResponseDTO;
import com.csv.communitytrackerjava.model.Project;
import com.csv.communitytrackerjava.service.ExceptionService;
import com.csv.communitytrackerjava.service.ProjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class ProjectControllerTest {
    Project delete, save;
    @MockBean
    ProjectService projectService;
    @Autowired
    ExceptionService exceptionService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        save = new Project(1, "Description desc", "Code123", true);
        delete = new Project(1, "Description desc", "Code123", false);

    }

    @Test
    public void delete() throws Exception {
//Arrange

        ProjectResponseDTO projectResponseDTO = new ProjectResponseDTO();
        projectResponseDTO.setMessage("Project was already deleted.");
        Mockito.when(projectService.deleteProject(delete.getProjectId())).thenReturn(projectResponseDTO);

        //Act
        mockMvc.perform(put("/projects/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(delete)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isAc tive", CoreMatchers.is(delete.getIsActive())));
    }
}