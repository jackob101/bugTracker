package com.trix.bugtracker.frontend.handlers;

import com.trix.bugtracker.frontend.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.security.auth.login.AccountNotFoundException;

@Slf4j
@ControllerAdvice
public class UserControllerHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public void handleAccountNotFound(UserNotFoundException ex){
        log.info(ex.getMessage());
    }
}
