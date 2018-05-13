package com.example.blog.controller;

import com.example.blog.entity.Comment;
import com.example.blog.entity.Post;
import com.example.blog.restObjects.LoadMorePostsResponse;
import com.example.blog.restObjects.SimplePost;
import com.example.blog.service.IPostService;
import com.example.blog.service.IUserService;
import com.example.blog.utilities.PostUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    private IPostService postService;

    private IUserService userService;

    @Autowired
    public HomeController(IPostService postService, IUserService userService) {
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

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String home(Model model, Principal principal) {
        List<Post> posts = postService.findAllPostsWithComments(0, 3, null);
        model.addAttribute("posts", posts);
        String loggedUserNick = null;
        if (principal != null){
            loggedUserNick = userService.findUserByEmail(principal.getName()).getNick();
        }
        model.addAttribute("loggedUserNick", loggedUserNick);
        return "homePage";
    }

    @RequestMapping(value = "/sendPost", method = RequestMethod.POST)
    public String createPost(@Valid Post post, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "homePage";
        }
        postService.createPost(post, principal.getName());
        return "redirect:/";
    }

    @RequestMapping("/loadMorePosts")
    @ResponseBody
    public ResponseEntity<?> loadMorePosts(@RequestParam String page, @RequestParam String size, @RequestParam(required = false) String tagCriteria, Principal principal) {
        int pageValue;
        int sizeValue;
        try {
            pageValue = Integer.parseInt(page);
            sizeValue = Integer.parseInt(size);
        }
        catch (NumberFormatException e){
            return ResponseEntity.badRequest().body("Wrong parameters");
        }
        List<String> tags = null;
        if (tagCriteria != null){
            tags = PostUtilities.getTags(tagCriteria);
        }
        List<SimplePost> simplePosts = postService.findAllSimplifiedPostsWithSimplifiedComments(pageValue, sizeValue, tags);
        String loggedUserNick = null;
        if (principal != null){
            loggedUserNick = userService.findUserByEmail(principal.getName()).getNick();
        }
        return ResponseEntity.ok(new LoadMorePostsResponse(loggedUserNick, simplePosts));
    }

}
