package com.csv.communitytrackerjava.controller;

import com.csv.communitytrackerjava.dto.ProjectAddDTO;
import com.csv.communitytrackerjava.dto.ProjectResponseDTO;
import com.csv.communitytrackerjava.dto.ProjectUpdateDTO;
import com.csv.communitytrackerjava.exception.ProjectCodeExistException;
import com.csv.communitytrackerjava.model.Project;
import com.csv.communitytrackerjava.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;


    @GetMapping
    public ResponseEntity<ProjectResponseDTO> findAll() {
        return new ResponseEntity<>(projectService.findAllProject(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ProjectResponseDTO> addProject(@Valid @RequestBody ProjectAddDTO projectAddDTO) throws ProjectCodeExistException {
        return new ResponseEntity<>(projectService.saveProject(projectAddDTO), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> update(@Valid @RequestBody ProjectUpdateDTO projectUpdateDTO, @PathVariable Integer id) throws Exception {
        return new ResponseEntity<>(projectService.updateProject(projectUpdateDTO, id), HttpStatus.ACCEPTED);
    }
    
    @GetMapping("/people/{projectId}")
    public ResponseEntity<Page<List<Project>>> findByProjectId(Pageable pageable, @PathVariable Integer projectId) throws Exception{
        return new ResponseEntity<>(projectService.findPeopleByProjectId(pageable, projectId), HttpStatus.ACCEPTED);
    }

    @GetMapping("/projectDesc/{projectDesc}")
    public ResponseEntity<Page<List<Project>>> findByProjectId(Pageable pageable, @PathVariable String projectDesc) throws Exception{
        return new ResponseEntity<>(projectService.findByProjectDesc(pageable, projectDesc), HttpStatus.ACCEPTED);
    }

}
