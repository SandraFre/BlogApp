package com.example.blogapp.service;

import com.example.blogapp.data.Comment;
import com.example.blogapp.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }

    public Comment getComment(UUID id){
        return commentRepository.findById(id).orElseThrow(()->new RuntimeException(String.valueOf(id)));
    }

    public void saveComment(Comment comment){
        commentRepository.save(comment);
    }

    public void deleteComment(UUID id){
        commentRepository.deleteById(id);
    }
}
