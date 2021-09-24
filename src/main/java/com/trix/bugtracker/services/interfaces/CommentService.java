package com.trix.bugtracker.services.interfaces;

import java.util.List;

import com.trix.bugtracker.DTO.CommentDTO;
import com.trix.bugtracker.model.Comment.Comment;

public interface CommentService extends CRUDService<Comment> {

    List<Comment> findByUser_id(Long userId);

    Comment save(CommentDTO commentDTO);

    Comment update(Long commentId, String comment);
}
