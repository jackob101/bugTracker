package com.trix.bugtracker.services.implementation;

import com.trix.bugtracker.model.Comment.Comment;
import com.trix.bugtracker.repository.CommentRepository;
import com.trix.bugtracker.services.interfaces.CommentService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
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
}
