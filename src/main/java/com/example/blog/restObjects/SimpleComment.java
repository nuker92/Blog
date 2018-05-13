package com.example.blog.restObjects;

import java.time.LocalDateTime;

public class SimpleComment {

    private Long postId;
    private Long commentId;
    private String commentDescription;
    private String userNick;
    private LocalDateTime dateOfAddition;
    private int rating;

    public SimpleComment() {
    }

    public SimpleComment(Long postId, Long commentId, String commentDescription, String userNick, LocalDateTime dateOfAddition, int rating) {
        this.postId = postId;
        this.commentId = commentId;
        this.commentDescription = commentDescription;
        this.userNick = userNick;
        this.dateOfAddition = dateOfAddition;
        this.rating = rating;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getCommentDescription() {
        return commentDescription;
    }

    public void setCommentDescription(String commentDescription) {
        this.commentDescription = commentDescription;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public LocalDateTime getDateOfAddition() {
        return dateOfAddition;
    }

    public void setDateOfAddition(LocalDateTime dateOfAddition) {
        this.dateOfAddition = dateOfAddition;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
