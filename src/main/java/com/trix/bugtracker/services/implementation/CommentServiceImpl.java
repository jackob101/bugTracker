package com.trix.bugtracker.services.implementation;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.trix.bugtracker.DTO.CommentDTO;
import com.trix.bugtracker.model.Comment.Comment;
import com.trix.bugtracker.model.Issue.Issue;
import com.trix.bugtracker.model.User.User;
import com.trix.bugtracker.repository.CommentRepository;
import com.trix.bugtracker.services.interfaces.CommentService;
import com.trix.bugtracker.services.interfaces.IssueService;
import com.trix.bugtracker.services.interfaces.UserService;

import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final IssueService issueService;
    private final UserService userService;

    public CommentServiceImpl(CommentRepository commentRepository,
			      IssueService issueService,
			      UserService userService) {

        this.commentRepository = commentRepository;
	this.issueService = issueService;
	this.userService = userService;
    }


    @Override
    public Comment findById(Long id) {
        Optional<Comment> commentById = commentRepository.findById(id);
        return commentById.orElse(null);
    }

    @Override
    public Comment save(Comment entity) {
        return commentRepository.save(entity);
    }

    @Override
    public Collection<Comment> saveAll(List<Comment> listOfEntities) {
        return commentRepository.saveAll(listOfEntities);
    }

    @Override
    public boolean delete(Comment entity) {
        if (entity != null) {
            commentRepository.delete(entity);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        if (id != null) {
            commentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public List<Comment> findByUser_id(Long userId) {
        return null;
    }


    @Override
    public Comment save(CommentDTO commentDTO) {
	if(commentDTO.getUserId() != null && commentDTO.getIssueId() != null){
	    User userById = userService.findById(commentDTO.getUserId()); 
	    Issue issueById = issueService.findById(commentDTO.getIssueId());

	    Comment comment = new Comment();
	    comment.setCommentOwner(userById);
	    comment.setIssue(issueById);
	    comment.setComment(commentDTO.getComment());
	    comment.setId(commentDTO.getId());
	    comment.setCreationDate(LocalDateTime.now());

	    return commentRepository.save(comment);
	}
	return null;
    }


    @Override
    public Comment update(Long commentId, String comment) {
	Comment commentById = this.findById(commentId);
	commentById.setComment(comment);
	return save(commentById);
    }
}
