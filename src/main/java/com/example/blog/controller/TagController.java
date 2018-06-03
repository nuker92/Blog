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

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller
public class TagController {

    private IPostService postService;

    private IUserService userService;

    @Autowired
    public TagController(IPostService postService, IUserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @ModelAttribute
    public Post post() {
        return new Post();
    }

    @ModelAttribute
    public Comment comment() {
        return new Comment();
    }

    @RequestMapping(value = "/tag/{tagValue}")
    public String search(@PathVariable String tagValue, Model model, Principal principal) {
        List<Post> posts = postService.findAllPostsWithComments(0, 3, Arrays.asList(tagValue));
        if (posts.size() == 0) {
            return "error";
        }
        model.addAttribute("posts", posts);
        String loggedUserNick = null;
        if (principal != null) {
            loggedUserNick = userService.findUserByEmail(principal.getName()).getNick();
        }
        model.addAttribute("loggedUserNick", loggedUserNick);
        return "tagPage";

    }


}
