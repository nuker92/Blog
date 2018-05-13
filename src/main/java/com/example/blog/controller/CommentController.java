package com.example.blog.controller;

import com.example.blog.entity.Comment;
import com.example.blog.restObjects.SimpleComment;
import com.example.blog.service.ICommentService;
import com.example.blog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
public class CommentController {

    private ICommentService commentService;

    private IUserService userService;

    @Autowired
    public CommentController(ICommentService commentService, IUserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    @PostMapping(value = "/sendComment")
    public ResponseEntity<?> createComment(@Valid @RequestBody SimpleComment simpleComment, Principal principal) {
        if (simpleComment.getCommentDescription().length() < 3){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.class);
        }
        Comment comment = commentService.createComment(simpleComment, principal.getName());
        simpleComment.setUserNick(userService.findUserByEmail(principal.getName()).getNick());
        simpleComment.setCommentId(comment.getId());
        return ResponseEntity.ok(simpleComment);
    }
}
