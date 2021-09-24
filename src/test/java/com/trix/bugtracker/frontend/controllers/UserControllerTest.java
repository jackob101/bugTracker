package com.trix.bugtracker.frontend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trix.bugtracker.model.User.User;
import com.trix.bugtracker.services.interfaces.UserService;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
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

@AutoConfigureMockMvc(addFilters = false)
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
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sub","auth|test");
        jsonObject.put("name","name1");
        jsonObject.put("nickname","nickname1");
        jsonObject.put("lastName","lastName1");
        jsonObject.put("email","email@gmail.com");
        jsonObject.put("emailVerified",true);
        jsonObject.put("age",24);
        System.out.println(jsonObject.toString());



        //when
        when(userService.save(Mockito.any(User.class))).then(AdditionalAnswers.returnsFirstArg());

        //then
        mockMvc.perform(post(path + "/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonObject.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("name1"));
    }

}