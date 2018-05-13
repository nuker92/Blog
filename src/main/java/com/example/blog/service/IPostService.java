package com.example.blog.service;

import com.example.blog.entity.Post;
import com.example.blog.restObjects.SimplePost;

import java.util.List;

public interface IPostService {

    void createPost(Post post, String userLogin);

    List<Post> findAllPostsWithComments(int min, int max, List<String> criteria);

    List<SimplePost> findAllSimplifiedPostsWithSimplifiedComments(int page, int size, List<String> criteria);

    Post findPostWithComments(Long id);

    List<Post> findAllUserPostsWithComment(int page, int size, String userNick);

    List<SimplePost> findAllUserSimplifiedPostsWithSimplifiedComments(int page, int size, String userNick);
}
