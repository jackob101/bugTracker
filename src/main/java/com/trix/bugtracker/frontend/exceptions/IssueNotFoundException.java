package com.trix.bugtracker.frontend.exceptions;

public class IssueNotFoundException extends RuntimeException{

    public IssueNotFoundException(Long id) {
        System.out.println("Issue with id: " + id + " was not found");
    }
}
