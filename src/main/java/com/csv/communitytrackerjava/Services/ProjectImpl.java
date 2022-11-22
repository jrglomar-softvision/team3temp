package com.csv.communitytrackerjava.Services;

import com.csv.communitytrackerjava.Repository.ProjectRepository;
import com.csv.communitytrackerjava.exception.RecordNotFoundException;
import com.csv.communitytrackerjava.model.Project;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ProjectImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override

    public Project updateProject(Project project, Integer id) throws RecordNotFoundException {
        Project projectFound = projectRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("project to update not found."));
        modelMapper.getConfiguration().setSkipNullEnabled(false);
        modelMapper.map(project, projectFound);
        projectFound.setProjectId(id);
        return projectRepository.save(projectFound);
    }
}


