package com.csv.communitytrackerjava.TestController;


import com.csv.communitytrackerjava.Services.ProjectService;
import com.csv.communitytrackerjava.model.Project;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class TestProjectController {
    Project jojo, raven, r, lyoyd, baqui;

    @MockBean
    private ProjectService projectService;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        jojo = new Project(1, "projectCode", "projectDesc", true);
        raven = new Project(2, "projectCode2", "projectDesc2", true);
        r = new Project(3, "projectCode3", "projectDesc3", true);
        lyoyd = new Project(4, "projectCode4", "projectDesc4", true);
        baqui = new Project(5, "projectCode5", "projectDesc5", true);

    }

    @Test
    void updateProject() throws Exception {
        //Arrange
        jojo.setProjectDesc("BAGONG_PROJECT");
        when(projectService.updateProject(any(Project.class), anyInt())).thenReturn(jojo);

        //ACT
        mockMvc.perform(put("/projects/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(jojo)))
                .andExpect(jsonPath("$.projectDesc", CoreMatchers.is(jojo.getProjectDesc())))
                .andExpect(status().isOk());


    }
}
