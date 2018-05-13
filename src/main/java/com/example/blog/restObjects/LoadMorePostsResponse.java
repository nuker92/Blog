package com.example.blog.restObjects;

import java.util.List;

public class LoadMorePostsResponse {

    private String loggedUserNick;
    private List<SimplePost> simplePosts;

    public LoadMorePostsResponse(String loggedUserNick, List<SimplePost> simplePosts) {
        this.loggedUserNick = loggedUserNick;
        this.simplePosts = simplePosts;
    }


    public String getLoggedUserNick() {
        return loggedUserNick;
    }

    public void setLoggedUserNick(String loggedUserNick) {
        this.loggedUserNick = loggedUserNick;
    }

    public List<SimplePost> getSimplePosts() {
        return simplePosts;
    }

    public void setSimplePosts(List<SimplePost> simplePosts) {
        this.simplePosts = simplePosts;
    }
}
