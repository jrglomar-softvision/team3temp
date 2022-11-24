package com.academy.communitytrackerjava.controllerTest;

import com.academy.communitytrackerjava.controller.ExceptionController;
import com.academy.communitytrackerjava.controller.ProjectController;
import com.academy.communitytrackerjava.model.Project;
import com.academy.communitytrackerjava.model.ProjectDTO;
import com.academy.communitytrackerjava.service.ProjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
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
    ProjectDTO magenic, lorem;
    @MockBean
    private ProjectService projectService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Spy
    ModelMapper modelMapper;
    @Autowired
    ExceptionController exceptionController;
    String desc = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
            "Integer vulputate laoreet lacus, id rhoncus leo ultricies vitae. Nunc et imperdiet ipsum, " +
            "molestie porta justo. Aenean commodo porttitor posuere. Cras ac sapien ac justo lacinia egestas ac a justo. " +
            "Nunc vulputate, turpis volutpat elementum varius, velit neque congue metus, at vulputate tellus urna commodo dolor. " +
            "In at consectetur diam, vel molestie augue. Vivamus non fringilla diam. Duis non sem gravida, posuere est at, consequat libero. " +
            "Quisque id rutrum velit, quis convallis libero. Quisque ac nisi quis orci sodales molestie. Etiam non diam est. " +
            "Mauris fermentum eros ac euismod sagittis el de jojo.";
    @BeforeEach
    void setup() {
        magenic = new ProjectDTO(1, "MagenicPDPBench", "MDP-101", 1);
        lorem = new ProjectDTO(2, desc, "LOREM-102", 1);
    }

    @Test
    void saveProject() throws Exception {
        //Arrange
        when(projectService.saveProject(any(ProjectDTO.class)))
                .thenReturn(lorem);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        //Act
        mockMvc.perform(post("/project")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(magenic)))
                .andExpect(jsonPath("$.projectDesc", CoreMatchers.is(magenic.getProjectDesc())))
                .andExpect(jsonPath("$.projectCode", CoreMatchers.is(magenic.getProjectCode())))
                .andExpect(jsonPath("$.isActive", CoreMatchers.is(magenic.getIsActive())))
                .andExpect(status().isOk());

    }
    @Test()
    void saveProjectWithMoreThan100() throws Exception {
        //Arrange
        when(projectService.saveProject(any(ProjectDTO.class)))
                .thenReturn(lorem);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        //Act
        mockMvc.perform(post("/project")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(lorem)))
                .andExpect(jsonPath("$.projectDesc", CoreMatchers.is(lorem.getProjectDesc())))
                .andExpect(jsonPath("$.projectCode", CoreMatchers.is(lorem.getProjectCode())))
                .andExpect(jsonPath("$.isActive", CoreMatchers.is(lorem.getIsActive())))
                .andExpect(status().isBadRequest());
    }

}
