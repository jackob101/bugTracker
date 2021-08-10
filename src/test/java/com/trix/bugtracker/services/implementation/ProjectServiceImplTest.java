package com.trix.bugtracker.services.implementation;

import com.trix.bugtracker.model.Project.Project;
import com.trix.bugtracker.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectServiceImplTest {

    @Mock
    ProjectRepository projectRepository;

    @InjectMocks
    ProjectServiceImpl projectService;

    Project project;

    @BeforeEach
    void setUp() {
        project = Project.builder()
                .id(1L)
                .name("Project 1")
                .build();
    }

    @Test
    void findById() {
        //given
        //when
        when(projectRepository.findById(anyLong())).thenReturn(Optional.of(project));
        Project byId = projectService.findById(1L);

        //then
        assertNotNull(byId);
        assertEquals(project, byId);
    }

    @Test
    void findById_NotFound() {
        //given
        //when
        when(projectRepository.findById(anyLong())).thenReturn(Optional.empty());
        Project byId = projectService.findById(1L);

        //then
        assertNull(byId);

    }

    @Test
    void findById_IdNull() {
        //given
        //when
        //then
        assertNull(projectService.findById(null));
    }

    @Test
    void save() {
        //given
        //when
        when(projectRepository.save(project)).thenReturn(project);

        //then
        assertNotNull(projectService.save(project));
    }


    @Test
    void save_null() {
        //given
        //when
        //then
        assertNull(projectService.save(null));
    }

    @Test
    void saveAll() {
        //given
        List<Project> projects = Arrays.asList(
                Project.builder()
                        .name("Project 1")
                        .id(1L)
                        .build(),
                Project.builder()
                        .name("Project 2")
                        .id(1L)
                        .build(),
                Project.builder()
                        .name("Project 3")
                        .id(1L)
                        .build()
        );

        //when
        when(projectRepository.saveAll(projects)).thenReturn(projects);
        List<Project> returned = projectService.saveAll(projects);

        //then
        assertNotNull(returned);
        assertEquals(3, returned.size());
    }

    @Test
    void saveAll_null() {
        //given
        List<Project> projects = Arrays.asList(
                Project.builder()
                        .name("Project 1")
                        .id(1L)
                        .build(),
                Project.builder()
                        .name("Project 2")
                        .id(1L)
                        .build(),
                null,
                Project.builder()
                        .name("Project 3")
                        .id(1L)
                        .build()
        );

        //when
        //then
        assertThrows(IllegalArgumentException.class,() -> projectService.saveAll(projects));
        assertThrows(IllegalArgumentException.class,() -> projectService.saveAll(null));
    }

    @Test
    void delete_entity() {
        //given
        //when
        //then
        assertTrue(projectService.delete(project));
    }

    @Test
    void delete_entityNull() {
        //given
        //when
        //then
        assertFalse(projectService.delete((Project)null));
    }

    @Test
    void delete_id() {
        //given
        //when
        //then
        assertTrue(projectService.delete(1L));
    }

    @Test
    void delete_idNull() {
        //given
        //when
        //then
        assertFalse(projectService.delete((Long)null));
    }

}