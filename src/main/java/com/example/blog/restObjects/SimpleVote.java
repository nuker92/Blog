package com.example.blog.restObjects;

import com.example.blog.entity.VoteType;

public class SimpleVote {

    private Long Id;
    private VoteType itemType;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public VoteType getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        if ("POST".equals(itemType)){
            this.itemType = VoteType.POST;
            return;
        }
        if ("COMMENT".equals(itemType)){
            this.itemType = VoteType.COMMENT;
            return;
        }
    }

    @Override
    public String toString() {
        return "SimpleVote{" +
                "Id=" + Id +
                ", itemType=" + itemType +
                '}';
    }
}
