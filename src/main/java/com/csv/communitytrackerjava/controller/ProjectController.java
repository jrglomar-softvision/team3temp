package com.csv.communitytrackerjava.controller;
import com.csv.communitytrackerjava.dto.ProjectAddDTO;
import com.csv.communitytrackerjava.dto.ProjectUpdateDTO;
import com.csv.communitytrackerjava.exception.RecordNotFoundException;
import com.csv.communitytrackerjava.dto.ProjectResponseDTO;
import com.csv.communitytrackerjava.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;
    

    @GetMapping
    public ResponseEntity<ProjectResponseDTO> findAll(){
        return new ResponseEntity<>(projectService.findAllProject(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ProjectResponseDTO> addProject(@Valid @RequestBody ProjectAddDTO projectAddDTO) {
        return new ResponseEntity<>(projectService.saveProject(projectAddDTO), HttpStatus.CREATED);
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> update(@Valid @RequestBody ProjectUpdateDTO projectUpdateDTO, @PathVariable Integer id) throws RecordNotFoundException {
        return new ResponseEntity<>(projectService.updateProject(projectUpdateDTO, id), HttpStatus.ACCEPTED);
    }

}
