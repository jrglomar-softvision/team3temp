package com.csv.communitytrackerjava.repository;

import com.csv.communitytrackerjava.model.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleRepository extends PagingAndSortingRepository<People, Integer> {
    
}
