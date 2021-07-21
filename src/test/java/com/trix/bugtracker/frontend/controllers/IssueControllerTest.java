package com.trix.bugtracker.frontend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trix.bugtracker.model.Issue;
import com.trix.bugtracker.model.enums.Priority;
import com.trix.bugtracker.services.interfaces.IssueService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(IssueController.class)
class IssueControllerTest {

    @MockBean
    IssueService issueService;

    @Autowired
    MockMvc mockMvc;

    Issue issue;

    String path = "/issue";


    @BeforeEach
    void setUp() {
        issue = Issue.builder()
                .id(1L)
                .priority(Priority.IMPORTANT)
                .description("Test description")
                .build();
    }

    @Test
    void findAll() throws Exception {
        //given

        //when
        when(issueService.findAll()).thenReturn(Collections.singletonList(issue));

        //then
        mockMvc.perform(get(path + "/all")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].description").value("Test description"));

    }

    @Test
    void findById() throws Exception {
        //given
        //when
        when(issueService.findById(anyLong())).thenReturn(issue);

        //then
        mockMvc.perform(get(path + "/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.description").value(issue.getDescription()))
                .andExpect(jsonPath("$.priority").value(issue.getPriority().name()))
                .andExpect(jsonPath("$.id").value(issue.getId()));
    }

    @Test
    void findById_notFound() throws Exception {
        //given
        //when
        when(issueService.findById(anyLong())).thenReturn(null);

        //then
        mockMvc.perform(get(path + "/1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void createAccount() throws Exception {
        //given
        String json = new ObjectMapper().writeValueAsString(issue);

        //when
        when(issueService.save(Mockito.any(Issue.class))).thenReturn(issue);

        //then
        mockMvc.perform(post(path + "/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.description").value(issue.getDescription()))
                .andExpect(jsonPath("$.id").value(issue.getId()))
                .andExpect(jsonPath("$.priority").value(issue.getPriority().name()));
    }
}