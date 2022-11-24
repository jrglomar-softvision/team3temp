package com.csv.communitytrackerjava.controller;

import com.csv.communitytrackerjava.dto.ProjectPayloadDTO;
import com.csv.communitytrackerjava.exception.RecordNotFoundException;
import com.csv.communitytrackerjava.mapper.ProjectMapper;
import com.csv.communitytrackerjava.model.Project;
import com.csv.communitytrackerjava.dto.ProjectResponseDTO;
import com.csv.communitytrackerjava.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;
    

    @GetMapping
    public ResponseEntity<ProjectResponseDTO> findAll(){
        return new ResponseEntity<>(projectService.findAllProject(), HttpStatus.OK);
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> update(@RequestBody Project project, @PathVariable Integer id) throws RecordNotFoundException {
        return new ResponseEntity<>(projectService.updateProject(project, id), HttpStatus.ACCEPTED);
    }

}
