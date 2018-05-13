package com.example.blog.controller;

import com.example.blog.entity.Comment;
import com.example.blog.entity.Post;
import com.example.blog.service.IPostService;
import com.example.blog.service.IUserService;
import com.example.blog.utilities.PostUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class SearchController {

    private IPostService postService;

    private IUserService userService;

    @ModelAttribute
    public Post post() {
        return new Post();
    }

    @ModelAttribute
    public Comment comment() {
        return new Comment();
    }

    @Autowired
    public SearchController(IPostService postService, IUserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @RequestMapping(value = "/search")
    public String search(@RequestParam String tagCriteria, Model model, Principal principal){
        List<String> tags = PostUtilities.getTags(tagCriteria);
        List<Post> posts = null;
        if (tags.size() > 0){
            posts = postService.findAllPostsWithComments(0, 3, tags);
        }
        model.addAttribute("posts", posts);
        String loggedUserNick = null;
        if (principal != null){
            loggedUserNick = userService.findUserByEmail(principal.getName()).getNick();
        }
        model.addAttribute("loggedUserNick", loggedUserNick);
        return "searchPage";

    }
}
