package com.trix.bugtracker.frontend.handlers;

import com.trix.bugtracker.frontend.exceptions.ProjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ProjectControllerHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProjectNotFoundException.class)
    public void projectNotFound(ProjectNotFoundException exception){
        log.info(exception.getMessage());
    }
}
