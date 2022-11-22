package com.csv.communitytrackerjava.controller;

import com.csv.communitytrackerjava.exception.RecordNotFoundException;
import com.csv.communitytrackerjava.model.Project;
import com.csv.communitytrackerjava.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    

}
