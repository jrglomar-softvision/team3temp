package com.csv.communitytrackerjava.repository;

import com.csv.communitytrackerjava.model.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Integer> {
}
