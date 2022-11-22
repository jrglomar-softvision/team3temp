package com.csv.communitytrackerjava.Services;

import com.csv.communitytrackerjava.exception.RecordNotFoundException;
import com.csv.communitytrackerjava.model.Project;

public interface ProjectService {

    Project updateProject(Project project,Integer id) throws RecordNotFoundException;
}
