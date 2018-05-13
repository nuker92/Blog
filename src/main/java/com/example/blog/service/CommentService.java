package com.example.blog.service;

import com.example.blog.entity.Comment;
import com.example.blog.restObjects.SimpleComment;
import com.example.blog.repository.CommentRepository;
import com.example.blog.repository.PostRepository;
import com.example.blog.repository.UserRepository;
import com.example.blog.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service("commentService")
public class CommentService implements ICommentService {

    private CommentRepository commentRepository;

    private PostRepository postRepository;

    private UserRepository userRepository;

    private VoteRepository voteRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository, VoteRepository voteRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.voteRepository = voteRepository;
    }


    @Override
    public Comment createComment(SimpleComment simpleComment, String userLogin) {
        Comment comment = new Comment();
        comment.setDescription(simpleComment.getCommentDescription());
        comment.setPost(postRepository.findById(simpleComment.getPostId()).get());
        comment.setDateOfAddition(LocalDateTime.now());
        comment.setUser(userRepository.findByEmail(userLogin));
        return commentRepository.save(comment);
    }

    @Override
    public Long getRating(Comment comment) {
        return voteRepository.countByComment(comment);
    }


}
