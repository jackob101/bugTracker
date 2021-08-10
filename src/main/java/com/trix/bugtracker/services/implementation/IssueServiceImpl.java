package com.trix.bugtracker.services.implementation;


import com.trix.bugtracker.model.Issue.Issue;
import com.trix.bugtracker.repository.IssueRepository;
import com.trix.bugtracker.services.interfaces.IssueService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;

    public IssueServiceImpl(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    @Override
    public Issue findById(Long id) {
        if (id != null) {
            return issueRepository.findById(id).orElse(null);
        }
        return null;
    }

    @Override
    public Issue save(Issue entity) {
        if (entity != null) {
            return issueRepository.save(entity);
        }
        return null;
    }

    @Override
    public List<Issue> saveAll(List<Issue> listOfEntities) {
        if (listOfEntities == null)
            throw new IllegalArgumentException("List cannot be null");
        if (listOfEntities.contains(null))
            throw new IllegalArgumentException("List cannot contain null value");
        return issueRepository.saveAll(listOfEntities);
    }

    @Override
    public boolean delete(Issue entity) {
        if(entity!=null){
            issueRepository.delete(entity);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        if(id!=null){
            issueRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Issue> findAll() {
        return issueRepository.findAll();
    }

    @Override
    public List<Issue> findIssuesByProjectId(Long id) {
        return issueRepository.findByProjectId(id);
    }

    @Override
    public Issue update(Issue issue, Long issueId) {
        issue.setId(issueId);
        return save(issue);
    }
}
