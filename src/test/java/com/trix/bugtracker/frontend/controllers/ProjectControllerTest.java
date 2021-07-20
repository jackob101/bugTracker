package com.trix.bugtracker.frontend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trix.bugtracker.model.Project;
import com.trix.bugtracker.services.interfaces.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ProjectController.class)
class ProjectControllerTest {

    @MockBean
    ProjectService projectService;

    @Autowired
    MockMvc mockMvc;

    Project project;

    String path = "/project";

    @BeforeEach
    void setUp() {
        project = Project.builder()
                .name("Project 1")
                .id(1L)
                .build();
    }

    @Test
    void findAll() throws Exception {
        //given

        //when
        when(projectService.findAll()).thenReturn(Collections.singletonList(project));

        //then
        mockMvc.perform(get(path + "/all")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].name").value("Project 1"));

    }

    @Test
    void findById() throws Exception {
        //given
        //when
        when(projectService.findById(anyLong())).thenReturn(project);

        //then
        mockMvc.perform(get(path + "/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(project.getId()))
                .andExpect(jsonPath("$.name").value(project.getName()));
    }

    @Test
    void findById_notFound() throws Exception {
        //given
        //when
        when(projectService.findById(anyLong())).thenReturn(null);

        //then
        mockMvc.perform(get(path + "/1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void createAccount() throws Exception {
        //given
        String json = new ObjectMapper().writeValueAsString(project);

        //when
        when(projectService.save(Mockito.any(Project.class))).thenReturn(project);

        //then
        mockMvc.perform(post(path + "/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(project.getName()));
    }
}