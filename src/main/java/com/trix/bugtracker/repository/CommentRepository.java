package com.trix.bugtracker.repository;

import com.trix.bugtracker.model.Comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByCommentOwner_id(Long userId);

    List<Comment> findByIssue_id(Long issueId);
}
