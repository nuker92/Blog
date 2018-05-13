package com.example.blog.service;


import com.example.blog.entity.Comment;
import com.example.blog.restObjects.SimpleComment;

public interface ICommentService {

    Comment createComment(SimpleComment simpleComment, String userLogin);

    Long getRating(Comment comment);

}
