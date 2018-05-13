package com.example.blog.service;

import com.example.blog.restObjects.SimpleVote;

public interface IVoteService {

    Long addVote(SimpleVote simpleVote, String userLogin);

    boolean isUserItem(SimpleVote simpleVote, String userLogin);
}
