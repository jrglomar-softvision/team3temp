package com.academy.communitytrackerjava.controller;

import com.academy.communitytrackerjava.exception.IsActiveNotZeroOrOneException;
import com.academy.communitytrackerjava.model.Project;
import com.academy.communitytrackerjava.model.ProjectDTO;
import com.academy.communitytrackerjava.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;


    @PostMapping("")
    public ResponseEntity<ProjectDTO> addProject(@Valid @RequestBody ProjectDTO projectDTO)
            throws IsActiveNotZeroOrOneException {
        ProjectDTO newProjectDTO = projectService.saveProject(projectDTO);
        return new ResponseEntity<>(newProjectDTO, HttpStatus.OK);
    }

}
