package com.academy.communitytrackerjava.controller;

import com.academy.communitytrackerjava.model.ProjectDTO;
import com.academy.communitytrackerjava.service.ProjectService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;


    @PostMapping("")
    public ResponseEntity<Map<String, Object>> addProject(@Valid @RequestBody ProjectDTO projectDTO) {
        Map<String, Object> response = returnResponse("Added succesfully", projectService.saveProject(projectDTO));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    private Map<String, Object> returnResponse(String message, ProjectDTO projectDTO) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("errors", ArrayUtils.EMPTY_STRING_ARRAY);
        response.put("message", message);
        response.put("payload", projectDTO);
        return response;
    }

}
