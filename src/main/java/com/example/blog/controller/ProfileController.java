package com.example.blog.controller;

import com.example.blog.entity.Comment;
import com.example.blog.entity.Post;
import com.example.blog.entity.User;
import com.example.blog.restObjects.LoadMorePostsResponse;
import com.example.blog.restObjects.SimplePost;
import com.example.blog.service.IPostService;
import com.example.blog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class ProfileController {

    private IUserService userService;

    private IPostService postService;

    @ModelAttribute
    public Comment comment(){
        return new Comment();
    }

    @Autowired
    public ProfileController(IUserService userService, IPostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @RequestMapping("/myProfile")
    public String myProfile(Principal principal, Model model){
        User user = userService.findUserByEmail(principal.getName());
        model.addAttribute("user", user);
        return "profile/myProfile";
    }

    @RequestMapping(value = "/myProfile", method = RequestMethod.POST)
    public String updateProfile(Principal principal, User user){
        userService.updateUser(principal.getName(), user);
        return "redirect:/myProfile";
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public String changePassword(Principal principal, User user){
        userService.changeUserPassword(principal.getName(), user.getPassword());
        return "redirect:/myProfile";
    }

    @RequestMapping("/profile/{userNick}")
    public String userProfile(@PathVariable String userNick, Principal principal, Model model){
        User user = userService.findUserByNick(userNick);
        if (user == null) {
            return "error";
        }
        model.addAttribute("user", user);
        String loggedUserNick = null;
        if (principal != null){
            loggedUserNick = userService.findUserByEmail(principal.getName()).getNick();
        }
        model.addAttribute("loggedUserNick", loggedUserNick);
        List<Post> posts = postService.findAllUserPostsWithComment(0, 3, userNick);
        model.addAttribute("posts", posts);
        return "profile/profilePage";
    }

    @RequestMapping("/loadMoreUserPosts")
    @ResponseBody
    public ResponseEntity<?> loadMorePosts(@RequestParam String page, @RequestParam String size, @RequestParam(required = true) String userNick, Principal principal) {
        int pageValue;
        int sizeValue;
        try {
            pageValue = Integer.parseInt(page);
            sizeValue = Integer.parseInt(size);
        }
        catch (NumberFormatException e){
            return ResponseEntity.badRequest().body("Wrong parameters");
        }
        List<SimplePost> simplePosts = postService.findAllUserSimplifiedPostsWithSimplifiedComments(pageValue, sizeValue, userNick);
        String loggedUserNick = null;
        if (principal != null){
            loggedUserNick = userService.findUserByEmail(principal.getName()).getNick();
        }
        return ResponseEntity.ok(new LoadMorePostsResponse(loggedUserNick, simplePosts));
    }

    @RequestMapping(value = "/profile/toggleStatus/{userNick}", method = RequestMethod.GET)
    public String toggleUserStatus(@PathVariable String userNick) {
        userService.toggleUserStatus(userNick);
        return "redirect:/profile/" + userNick;
    }



}
