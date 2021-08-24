package com.trix.bugtracker.frontend.controllers;

import com.trix.bugtracker.DTO.Auth0Pojo;
import com.trix.bugtracker.frontend.exceptions.UserNotFoundException;
import com.trix.bugtracker.model.User.User;
import com.trix.bugtracker.services.interfaces.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RequestMapping(path = "user", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(path = "{id}")
    public User findById(@PathVariable(value = "id") Long id) {

        User userById = userService.findById(id);

        if (userById == null)
            throw new UserNotFoundException(id);

        return userById;
    }

    @GetMapping(path = "sub")
    public User findBySub(@RequestParam(value = "sub") String sub) {
        return userService.findBySub(sub);
    }


    @GetMapping(path = "all")
    public List<User> findAll() {
        return userService.findAll();
    }


    @PostMapping(path = "new")
    public User createUser(@RequestBody @Valid User user, HttpServletResponse httpResponse) {

        User saved = userService.save(user);

        if (saved != null)
            httpResponse.setStatus(HttpServletResponse.SC_CREATED);

        return user;
    }

    @PostMapping(path = "/check")
    public HashMap<String, Boolean> checkIfUserExist(@RequestBody Auth0Pojo auth0Pojo) {
        HashMap<String, Boolean> response = new HashMap<>();
        response.put("exist", userService.checkIfExist(auth0Pojo));
        return response;
    }

    @GetMapping(path = "/notAssigned")
    public List<User> getNotAssignedUsers(@RequestParam(value = "issueId") Long id) {
        return userService.findNotAssigned(id);
    }

}
