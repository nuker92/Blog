package com.example.blog.controller;

import com.example.blog.entity.Comment;
import com.example.blog.entity.Post;
import com.example.blog.service.IPostService;
import com.example.blog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller()
public class PostController {

    private IPostService postService;

    private IUserService userService;

    @ModelAttribute
    public Comment comment(){
        return new Comment();
    }

    @Autowired
    public PostController(IPostService postService, IUserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @RequestMapping(value = "post/{id}", method = RequestMethod.GET)
    public String postDetail(Model model, @PathVariable long id, Principal principal){
        Post post = postService.findPostWithComments(id);
        if (post == null) {
            return "error";
        }
        model.addAttribute("post", postService.findPostWithComments(id));
        String loggedUserNick = null;
        if (principal != null){
            loggedUserNick = userService.findUserByEmail(principal.getName()).getNick();
        }
        model.addAttribute("loggedUserNick", loggedUserNick);
        return "postDetail";
    }

}
