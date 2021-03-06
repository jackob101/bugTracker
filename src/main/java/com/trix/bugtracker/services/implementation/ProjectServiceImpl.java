package com.trix.bugtracker.services.implementation;

import com.trix.bugtracker.model.Project.Project;
import com.trix.bugtracker.repository.ProjectRepository;
import com.trix.bugtracker.services.interfaces.ProjectService;
import com.trix.bugtracker.services.interfaces.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    private final UserService userService;

    public ProjectServiceImpl(ProjectRepository projectRepository, UserService userService) {
        this.projectRepository = projectRepository;
        this.userService = userService;
    }

    @Override
    public Project findById(Long id) {
        if (id != null) {
            Optional<Project> byId = projectRepository.findById(id);
            return byId.orElse(null);
        }
        return null;
    }

    @Override
    public Project save(Project entity) {
        if (entity != null){
            return projectRepository.save(entity);
        }
        return null;
    }

    @Override
    public List<Project> saveAll(List<Project> listOfEntities) {
        if (listOfEntities == null)
            throw new IllegalArgumentException("List cannot be null");
        if (listOfEntities.contains(null))
            throw new IllegalArgumentException("List cannot contain null value");
        return projectRepository.saveAll(listOfEntities);
    }

    @Override
    public boolean delete(Project entity) {
        if(entity!=null){
            projectRepository.delete(entity);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        if(id!=null){
            projectRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }


}
