package com.trix.bugtracker.frontend.handlers;

import com.trix.bugtracker.frontend.exceptions.IssueNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class IssueControllerHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IssueNotFoundException.class)
    public void issueNotFound(IssueNotFoundException exception){
        log.info(exception.getMessage());
    }
}
