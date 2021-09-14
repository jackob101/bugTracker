package com.trix.bugtracker.frontend.controllers;

import java.util.List;

import com.trix.bugtracker.DTO.CommentDTO;
import com.trix.bugtracker.model.Comment.Comment;
import com.trix.bugtracker.services.interfaces.CommentService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/comment")
@RestController
public class CommentController{
    
    private final CommentService commentService;
    
    public CommentController(CommentService commentService){
	this.commentService = commentService;
    }

    @PostMapping(path = "new") 
    public Comment saveComment(@RequestBody CommentDTO commentDTO){
	System.out.println(commentDTO);
	return commentService.save(commentDTO);
	
    }

    @GetMapping(value="all")
    public List<Comment> getAllComments() {

	return commentService.findAll();
    }

    @DeleteMapping(value = "/delete")
    public boolean deleteComment(@RequestParam Long id){
	return commentService.delete(id);
	
    }

}
