package com.csv.communitytrackerjava.controllerTest;

import com.csv.communitytrackerjava.model.Project;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class ProjectControllerTest {
    Project magenic;
    @MockBean
    private ProjectService projectService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        magenic = new Project(1, "MagenicPDPBench", "MDP-101", true);
    }

    @Test
    void saveProject() throws Exception {
        //Arrange
        when(projectService.saveProject(any(Project.class)))
                .thenReturn(magenic);

        //Act
        mockMvc.perform(post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(magenic)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.projectCode", CoreMatchers.is(magenic.getProjectCode())))
                .andExpect(jsonPath("$.projectDesc", CoreMatchers.is(magenic.getProjectDesc())))
                .andExpect(jsonPath("$.type", CoreMatchers.is(magenic.getIsActive())));

    }
}
