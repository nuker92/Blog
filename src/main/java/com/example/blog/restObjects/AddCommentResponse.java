package com.example.blog.restObjects;

public class AddCommentResponse {

    private String loggedUserNick;
    private SimpleComment simpleComments;

    public AddCommentResponse(String loggedUserNick, SimpleComment simpleComments) {
        this.loggedUserNick = loggedUserNick;
        this.simpleComments = simpleComments;
    }

    public String getLoggedUserNick() {
        return loggedUserNick;
    }

    public void setLoggedUserNick(String loggedUserNick) {
        this.loggedUserNick = loggedUserNick;
    }

    public SimpleComment getSimpleComments() {
        return simpleComments;
    }

    public void setSimpleComments(SimpleComment simpleComments) {
        this.simpleComments = simpleComments;
    }
}
