package com.csv.communitytrackerjava.controllerTest;

import com.csv.communitytrackerjava.exception.RecordNotFoundException;
import com.csv.communitytrackerjava.model.Project;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class ProjectControllerTest {
    Project magenic, lorem;
    @MockBean
    private ProjectService projectService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

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
    @Test
    void saveProjectWithNoDuplicates() throws Exception {
        //Arrange
        when(projectService.findById(anyLong()))
                .thenReturn(lorem);

        //Act
        mockMvc.perform(post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(lorem)))
                .andExpect(jsonPath("$.projectCode", CoreMatchers.is(lorem.getProjectCode())))
                .andExpect(jsonPath("$.projectDesc", CoreMatchers.is(lorem.getProjectDesc())))
                .andExpect(jsonPath("$.type", CoreMatchers.is(lorem.getIsActive())))
                .andExpect(status().isNotAcceptable());

    }
}
