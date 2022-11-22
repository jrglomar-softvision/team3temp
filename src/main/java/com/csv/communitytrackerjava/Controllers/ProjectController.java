package com.csv.communitytrackerjava.Controllers;

import com.csv.communitytrackerjava.Services.ProjectService;
import com.csv.communitytrackerjava.exception.RecordNotFoundException;
import com.csv.communitytrackerjava.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.Authenticator;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PutMapping("/{id}")
    public ResponseEntity<Project> update(@RequestBody Project project, @PathVariable Integer id) throws RecordNotFoundException {
        return new ResponseEntity<>(projectService.updateProject(project, id), HttpStatus.OK);


    }


}
