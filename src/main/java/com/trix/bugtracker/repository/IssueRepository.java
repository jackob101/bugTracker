package com.trix.bugtracker.repository;

import com.trix.bugtracker.model.Issue.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {

    List<Issue> findByUsersId(Long id);

    @Query("SELECT issue FROM Issue issue JOIN issue.project project WHERE (issue.closedTime is null) and project.id = :id")
    List<Issue> findOpenedByProjectId(Long id);

    List<Issue> findByProjectId(Long id);
}
