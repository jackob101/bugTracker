package com.trix.bugtracker.frontend.controllers;

import com.trix.bugtracker.frontend.exceptions.UserNotFoundException;
import com.trix.bugtracker.model.User.User;
import com.trix.bugtracker.services.interfaces.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RequestMapping(path = "user", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(path = "{id}")
    public User findById(@PathVariable(value = "id")Long id){

        User userById = userService.findById(id);

        if (userById == null)
            throw new UserNotFoundException(id);

        return userById;
    }


    @GetMapping(path = "all")
    public List<User> findAll(){
        return userService.findAll();
    }


    @PostMapping(path = "new")
    public User createIssue(@RequestBody @Valid User user, HttpServletResponse httpResponse){

        User saved = userService.save(user);

        if(saved!=null)
            httpResponse.setStatus(HttpServletResponse.SC_CREATED);

        return saved;
    }
}
