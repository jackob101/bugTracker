package com.trix.bugtracker.frontend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trix.bugtracker.model.Project;
import com.trix.bugtracker.model.User;
import com.trix.bugtracker.services.interfaces.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.ObjectFactory;
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

@WebMvcTest(UserController.class)
class UserControllerTest {

    @MockBean
    UserService userService;

    @Autowired
    MockMvc mockMvc;

    User user;

    String path = "/user";

    @BeforeEach
    void setUp() {

        user = User.builder()
                .id(1L)
                .name("User 1")
                .lastName("Last name 1")
                .age(25)
                .email("user@gmail.com")
                .build();

    }

    @Test
    void findAll() throws Exception {
        //given

        //when
        when(userService.findAll()).thenReturn(Collections.singletonList(user));

        //then
        mockMvc.perform(get(path + "/all")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].name").value(user.getName()));

    }

    @Test
    void findById() throws Exception {
        //given
        //when
        when(userService.findById(anyLong())).thenReturn(user);

        //then
        mockMvc.perform(get(path + "/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.name").value(user.getName()));
    }

    @Test
    void findById_notFound() throws Exception {
        //given
        //when
        when(userService.findById(anyLong())).thenReturn(null);

        //then
        mockMvc.perform(get(path + "/1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void createAccount() throws Exception {
        //given
        String json = new ObjectMapper().writeValueAsString(user);

        //when
        when(userService.save(Mockito.any(User.class))).thenReturn(user);

        //then
        mockMvc.perform(post(path + "/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(user.getName()));
    }

}