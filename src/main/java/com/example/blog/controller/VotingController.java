package com.example.blog.controller;

import com.example.blog.restObjects.SimpleVote;
import com.example.blog.restObjects.VoteInfo;
import com.example.blog.service.IPostService;
import com.example.blog.service.IVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
public class VotingController {

    private IPostService postService;

    private IVoteService voteService;

    @Autowired
    public VotingController(IPostService postService, IVoteService voteService) {
        this.postService = postService;
        this.voteService = voteService;
    }

    @PostMapping(value = "/vote")
    public ResponseEntity<?> vote(@Valid @RequestBody SimpleVote simpleVote, Principal principal){
        boolean isUserItem = voteService.isUserItem(simpleVote, principal.getName());
        if (isUserItem){
            return ResponseEntity.badRequest().body("You can't vote on your own content.");
        }
        Long rating = voteService.addVote(simpleVote, principal.getName());
        VoteInfo voteInfo = new VoteInfo(simpleVote.getId(), rating, simpleVote.getItemType());
        return ResponseEntity.ok(voteInfo);
    }





}
