package com.csv.communitytrackerjava.TestController;


import com.csv.communitytrackerjava.dto.ProjectPayloadDTO;
import com.csv.communitytrackerjava.dto.ProjectResponseDTO;
import com.csv.communitytrackerjava.dto.ProjectUpdateDTO;
import com.csv.communitytrackerjava.service.ProjectService;
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
import org.springframework.util.Assert;

import javax.validation.Payload;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class TestProjectController {
    Project jojoProj, raven, r, lyoyd, baqui;

    @MockBean
    private ProjectService projectService;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        jojoProj = new Project(1, "projectCode", "projectDesc", true);
        raven = new Project(2, "projectCode2", "projectDesc2", true);
        r = new Project(3, "projectCode3", "projectDesc3", true);
        lyoyd = new Project(4, "projectCode4", "projectDesc4", true);
        baqui = new Project(5, "projectCode5", "projectDesc5", true);


    }

    @Test
    void updateProject() throws Exception {
        //Arrange
        jojoProj.setProjectDesc("Change Project Desc");
        jojoProj.setProjectCode("Change Project Code");
        

        ProjectResponseDTO projectResponseDTO = new ProjectResponseDTO();
        ProjectPayloadDTO projectPayloadDTO = new ProjectPayloadDTO();
        projectResponseDTO.setErrors(null);
        projectResponseDTO.setMessage("Successfully fetch all projects.");
        projectPayloadDTO.setAdditionalProperty("projects", jojoProj);
        projectResponseDTO.setPayload(projectPayloadDTO);

        when(projectService.updateProject(any(ProjectUpdateDTO.class), anyInt())).thenReturn(projectResponseDTO);

        
        //ACT
        mockMvc.perform(patch("/projects/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(jojoProj)))
                .andExpect(jsonPath("$.projectDesc", CoreMatchers.is(jojoProj.getProjectDesc())))
                .andExpect(jsonPath("$.projectCode", CoreMatchers.is(jojoProj.getProjectCode())))
                .andExpect(status().isAccepted());


    }

    //    updateProjectCharacterLimitLessThan100
    @Test
    void updateProjectCharacterLimitGreaterThan100() throws Exception {
        //Arrange
        raven.setProjectDesc("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur auctor sem sed tortor laoreet, in venenatis sem malesuada. Sed sem libero, eleifend sed semper vitae, convallis in dui. Morbi ut leo felis. Morbi vitae erat eu lorem sodales hendrerit vitae nec libero. Vivamus elementum mauris eget augue ultricies pellentesque. Donec ac libero et erat pulvinar gravida. Sed luctus enim nulla, nec dapibus eros venenatis nec. Ut quis nisl at orci placerat facilisis. Curabitur luctus auctor lacus vitae rutrum. Etiam non pulvinar sapien, sit amet lacinia mauris. Donec facilisis ipsum ligula, ut pulvinar nibh cursus sed." +
                "Nunc malesuada justo eu consectetur eleifend. Nam consequat ornare ipsum eget porta. Proin vel dignissim tortor, vel accumsan ipsum. Cras id ipsum eget tortor semper volutpat. Quisque blandit, nunc sit amet ultricies accumsan, est nibh auctor dolor, eget mattis orci nisi vitae est. Cras feugiat laoreet eros, a aliquet nibh rutrum a. Vivamus aliquam venenatis mauris sed elementum. Morbi posuere urna a mi vulputate maximus. Pellentesque odio felis, tincidunt vel laoreet ut, finibus et ligula. Vivamus ornare in nibh vel scelerisque.\n" +
                "Etiam vitae varius velit. Nunc sed erat aliquet, facilisis arcu a, auctor erat. Suspendisse laoreet diam et tincidunt pellentesque. Sed lorem lorem, tempus ut diam id, tempus porttitor leo. Vestibulum vel egestas nisl. Vivamus viverra ultrices risus id semper. Sed ac urna ac turpis aliquam congue. Sed sagittis posuere scelerisque. Mauris faucibus a tortor ut finibus. Duis viverra lectus sed felis pulvinar lobortis. Aliquam erat volutpat. Vestibulum ornare eget urna pharetra efficitur. Donec nec hendrerit ante, ac scelerisque nulla. Praesent quis consectetur neque. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae;" +
                "Sed at auctor dolor. Suspendisse cursus, neque nec sollicitudin tempus, tellus risus lobortis velit, sit amet finibus elit ipsum nec est. Fusce consequat quis quam sed tincidunt. Nulla facilisi. Praesent porttitor, enim id mollis eleifend, eros sapien pretium tortor, id sollicitudin enim nulla ut neque. Cras vitae bibendum eros, vel auctor massa. Cras maximus vestibulum mi, eu viverra risus vulputate id. Sed tempor enim ac elementum ornare. Donec porttitor, felis vitae fermentum maximus, nibh est vulputate mi, vitae volutpat dui erat eu nisl. Suspendisse sit amet risus nibh. In hac habitasse platea dictumst. Duis faucibus facilisis bibendum. Aliquam ornare odio ac ligula cursus, nec feugiat velit laoreet. Aenean sit amet leo nunc. Aliquam at hendrerit ante.\n"
        );
        raven.setProjectCode("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur auctor sem sed tortor laoreet, in venenatis sem malesuada. Sed sem libero, eleifend sed semper vitae, convallis in dui. Morbi ut leo felis. Morbi vitae erat eu lorem sodales hendrerit vitae nec libero. Vivamus elementum mauris eget augue ultricies pellentesque. Donec ac libero et erat pulvinar gravida. Sed luctus enim nulla, nec dapibus eros venenatis nec. Ut quis nisl at orci placerat facilisis. Curabitur luctus auctor lacus vitae rutrum. Etiam non pulvinar sapien, sit amet lacinia mauris. Donec facilisis ipsum ligula, ut pulvinar nibh cursus sed." +
                "Nunc malesuada justo eu consectetur eleifend. Nam consequat ornare ipsum eget porta. Proin vel dignissim tortor, vel accumsan ipsum. Cras id ipsum eget tortor semper volutpat. Quisque blandit, nunc sit amet ultricies accumsan, est nibh auctor dolor, eget mattis orci nisi vitae est. Cras feugiat laoreet eros, a aliquet nibh rutrum a. Vivamus aliquam venenatis mauris sed elementum. Morbi posuere urna a mi vulputate maximus. Pellentesque odio felis, tincidunt vel laoreet ut, finibus et ligula. Vivamus ornare in nibh vel scelerisque.\n" +
                "Etiam vitae varius velit. Nunc sed erat aliquet, facilisis arcu a, auctor erat. Suspendisse laoreet diam et tincidunt pellentesque. Sed lorem lorem, tempus ut diam id, tempus porttitor leo. Vestibulum vel egestas nisl. Vivamus viverra ultrices risus id semper. Sed ac urna ac turpis aliquam congue. Sed sagittis posuere scelerisque. Mauris faucibus a tortor ut finibus. Duis viverra lectus sed felis pulvinar lobortis. Aliquam erat volutpat. Vestibulum ornare eget urna pharetra efficitur. Donec nec hendrerit ante, ac scelerisque nulla. Praesent quis consectetur neque. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae;" +
                "Sed at auctor dolor. Suspendisse cursus, neque nec sollicitudin tempus, tellus risus lobortis velit, sit amet finibus elit ipsum nec est. Fusce consequat quis quam sed tincidunt. Nulla facilisi. Praesent porttitor, enim id mollis eleifend, eros sapien pretium tortor, id sollicitudin enim nulla ut neque. Cras vitae bibendum eros, vel auctor massa. Cras maximus vestibulum mi, eu viverra risus vulputate id. Sed tempor enim ac elementum ornare. Donec porttitor, felis vitae fermentum maximus, nibh est vulputate mi, vitae volutpat dui erat eu nisl. Suspendisse sit amet risus nibh. In hac habitasse platea dictumst. Duis faucibus facilisis bibendum. Aliquam ornare odio ac ligula cursus, nec feugiat velit laoreet. Aenean sit amet leo nunc. Aliquam at hendrerit CODE.\n"
        );

        ProjectResponseDTO projectResponseDTO = new ProjectResponseDTO();
        ProjectPayloadDTO projectPayloadDTO = new ProjectPayloadDTO();
        projectResponseDTO.setErrors(null);
        projectResponseDTO.setMessage("Successfully fetch all projects.");
        projectPayloadDTO.setAdditionalProperty("projects", Arrays.asList());
        projectResponseDTO.setPayload(projectPayloadDTO);

        when(projectService.updateProject(any(ProjectUpdateDTO.class), anyInt())).thenReturn(projectResponseDTO);
//        when(projectService.updateProject(any(ProjectUpdateDTO.class), anyInt())).thenReturn(raven);

        //ACT
        mockMvc.perform(patch("/projects/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(raven)))
                .andExpect(jsonPath("$.projectDesc", CoreMatchers.is(raven.getProjectDesc())))
                .andExpect(jsonPath("$.projectCode", CoreMatchers.is(raven.getProjectCode())))
                .andExpect(status().isBadRequest());


    }
}
