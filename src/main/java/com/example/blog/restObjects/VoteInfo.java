package com.example.blog.restObjects;

import com.example.blog.entity.VoteType;

//auxiliary class that returns a response concerning voting
public class VoteInfo {

    private Long id;
    private Long rating;
    private VoteType voteType;

    public VoteInfo(Long id, Long rating, VoteType voteType) {
        this.id = id;
        this.rating = rating;
        this.voteType = voteType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public String getVoteType() {
        return voteType.toString();
    }

    public void setVoteType(VoteType voteType) {
        this.voteType = voteType;
    }
}
