package com.csv.communitytrackerjava.controller;

import com.csv.communitytrackerjava.mapper.ProjectMapper;
import com.csv.communitytrackerjava.model.Project;
import com.csv.communitytrackerjava.response.ProjectResponse;
import com.csv.communitytrackerjava.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;
    
    @Autowired
    ProjectMapper projectMapper;
    
    @GetMapping
    public ResponseEntity<ProjectResponse> findAll(){
        ProjectResponse projectResponse = new ProjectResponse();
        ObjectError objectError = new ObjectError("test", "test");
        projectResponse.setErrors(Arrays.asList(objectError));
        projectResponse.setMessage("Successfully fetch all projects.");
        projectResponse.setAdditionalProperty("projects", projectService.findAllProject());
        return new ResponseEntity<>(projectResponse, HttpStatus.OK);
    }
    
    @GetMapping("/asd")
    public ResponseEntity<Iterable<Project>> findAllProject(){
        return new ResponseEntity<>(projectService.findAllProject(), HttpStatus.OK);
    }

}
