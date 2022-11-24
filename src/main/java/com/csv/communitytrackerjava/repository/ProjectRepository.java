package com.csv.communitytrackerjava.repository;

import com.csv.communitytrackerjava.model.Project;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends PagingAndSortingRepository<Project, Integer> {
    Project findByProjectCode(String projectCode);
}
