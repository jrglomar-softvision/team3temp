package com.csv.communitytrackerjava.controller;

import com.csv.communitytrackerjava.dto.PayloadDTO;
import com.csv.communitytrackerjava.exception.RecordNotFoundException;
import com.csv.communitytrackerjava.mapper.ProjectMapper;
import com.csv.communitytrackerjava.model.Project;
import com.csv.communitytrackerjava.dto.ProjectResponseDTO;
import com.csv.communitytrackerjava.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;
    
    @Autowired
    ProjectMapper projectMapper;
    
    ProjectResponseDTO projectResponseDTO = new ProjectResponseDTO();
    PayloadDTO payloadDTO = new PayloadDTO();

    @GetMapping
    public ResponseEntity<ProjectResponseDTO> findAll(){
        projectResponseDTO.setMessage("Successfully fetch all projects.");
        payloadDTO.setAdditionalProperty("projects", projectService.findAllProject());
        projectResponseDTO.setPayload(payloadDTO);
        return new ResponseEntity<>(projectResponseDTO, HttpStatus.OK);
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> update(@RequestBody Project project, @PathVariable Integer id) throws RecordNotFoundException {
        projectResponseDTO.setMessage("Successfully update project.");
        payloadDTO.setAdditionalProperty("projects", projectService.updateProject(project, id));
        projectResponseDTO.setPayload(payloadDTO);
        return new ResponseEntity<>(projectResponseDTO, HttpStatus.ACCEPTED);
    }

}
