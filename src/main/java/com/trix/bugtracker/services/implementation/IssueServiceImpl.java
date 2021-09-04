package com.trix.bugtracker.services.implementation;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.trix.bugtracker.DTO.IssueDTO;
import com.trix.bugtracker.model.Issue.Issue;
import com.trix.bugtracker.model.User.User;
import com.trix.bugtracker.repository.IssueRepository;
import com.trix.bugtracker.services.interfaces.IssueService;
import com.trix.bugtracker.services.interfaces.ProjectService;
import com.trix.bugtracker.services.interfaces.UserService;

import org.springframework.stereotype.Service;

@Service
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;
    private final UserService userService;
    private final ProjectService projectService;

    public IssueServiceImpl(IssueRepository issueRepository, UserService userService, ProjectService projectService) {
        this.issueRepository = issueRepository;
        this.userService = userService;
        this.projectService = projectService;
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
        if (entity != null) {
            issueRepository.delete(entity);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        if (id != null) {
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
    public Issue update(IssueDTO issue) {

        Issue byId = findById(issue.getId());

        if (issue.getTitle() != null) {
            byId.setTitle(issue.getTitle());
        }
        if (issue.getDescription() != null) {
            byId.setDescription(issue.getDescription());
        }
        if (issue.isClosed()) {
            byId.setClosedTime(LocalDateTime.now());
        }
        if (issue.getAssignedUsersId() != null) {
            List<User> allUsersById = userService.findAllByIds(issue.getAssignedUsersId());
            byId.setUsers(allUsersById);
        }
        if (issue.getPriority() != null) {
            byId.setPriority(issue.getPriority());
        }
        return save(byId);
    }

    @Override
    public Issue switchIssueClosedStatus(Long id) {
        Issue issue = findById(id);
        if (issue.getClosedTime() != null) {
            issue.setClosedTime(null);
        } else {
            issue.setClosedTime(LocalDateTime.now());
        }
        return save(issue);
    }

    @Override
    public Issue assignUser(Long issueId, Long userId) {
        User userById = userService.findById(userId);
        Issue issueById = findById(issueId);
        if (!issueById.getUsers().contains(userById)) {
            issueById.getUsers().add(userById);
            return save(issueById);
        }
        return issueById;
    }

    @Override
    public Issue unAssignUser(Long issueId, Long userId) {
        Issue byId = findById(issueId);
        List<User> filteredUsers = byId.getUsers()
                .stream()
                .filter(user -> !user.getId().equals(userId))
                .collect(Collectors.toList());
        byId.setUsers(filteredUsers);
        save(byId);
        return byId;
    }
}
