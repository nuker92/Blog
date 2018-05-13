package com.example.blog.restObjects;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SimplePost {

    private Long postId;
    private String postDescription;
    private String userNick;
    private int rating;
    private List<SimpleComment> comments = new ArrayList<>();

    private LocalDateTime dateOfAddition;

    public SimplePost(Long postId, String postDescription, String userNick, int rating, LocalDateTime dateOfAddition) {
        this.postId = postId;
        this.postDescription = postDescription;
        this.userNick = userNick;
        this.rating = rating;
        this.dateOfAddition = dateOfAddition;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<SimpleComment> getComments() {
        return comments;
    }

    public void setComments(List<SimpleComment> comments) {
        this.comments = comments;
    }

    public LocalDateTime getDateOfAddition() {
        return dateOfAddition;
    }

    public void setDateOfAddition(LocalDateTime dateOfAddition) {
        this.dateOfAddition = dateOfAddition;
    }
}
