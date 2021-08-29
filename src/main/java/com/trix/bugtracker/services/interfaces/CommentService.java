package com.trix.bugtracker.services.interfaces;

import com.trix.bugtracker.model.Comment.Comment;

import java.util.List;

public interface CommentService extends CRUDService<Comment> {

    List<Comment> findByUser_id(Long userId);
}
