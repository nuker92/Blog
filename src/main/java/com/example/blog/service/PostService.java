package com.example.blog.service;

import com.example.blog.entity.Comment;
import com.example.blog.entity.Post;
import com.example.blog.entity.tags.Tag;
import com.example.blog.entity.tags.TagUsage;
import com.example.blog.repository.*;
import com.example.blog.restObjects.SimpleComment;
import com.example.blog.restObjects.SimplePost;
import com.example.blog.utilities.PostUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("postService")
public class PostService implements IPostService {


    private PostRepository postRepository;

    private UserRepository userRepository;

    private CommentRepository commentRepository;

    private UsedTagsRepository usedTagsRepository;

    private TagRepository tagRepository;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository, CommentRepository commentRepository, UsedTagsRepository usedTagsRepository, TagRepository tagRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.usedTagsRepository = usedTagsRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Post> findAllPostsWithComments(int page, int size, List<String> criteria) {
        List<Post> posts;
        if (criteria == null){
            posts = postRepository.findAll(PageRequest.of(page, size, Sort.Direction.DESC, "dateOfAddition")).getContent();
        }
        else {
            posts = postRepository.findAllWithCustomQuery(criteria, PageRequest.of(page, size, Sort.Direction.DESC, "dateOfAddition"));
        }
        posts.stream().forEach(post -> post.setComments(commentRepository.findAllByPost(post)));
        return posts;
    }

    @Override
    public List<Post> findAllUserPostsWithComment(int page, int size, String userNick) {
        List<Post> posts = postRepository.findAllUserPost(userNick, PageRequest.of(page, size, Sort.Direction.DESC, "p.dateOfAddition"));
        posts.stream().forEach(post -> post.setComments(commentRepository.findAllByPost(post)));
        return posts;
    }

    @Override
    public Post findPostWithComments(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setComments(commentRepository.findAllByPost(post));
            return post;
        }
        return null;
    }

    @Override
    public List<SimplePost> findAllSimplifiedPostsWithSimplifiedComments(int page, int size, List<String> criteria) {

        List<Post> posts = findAllPostsWithComments(page, size, criteria);
        return parsePostsToSimplifiedPosts(posts);
    }

    @Override
    public List<SimplePost> findAllUserSimplifiedPostsWithSimplifiedComments(int page, int size, String userNick) {
        List<Post> posts = findAllUserPostsWithComment(page, size, userNick);
        return parsePostsToSimplifiedPosts(posts);
    }

    @Override
    public void createPost(Post post, String userLogin) {
        post.setUser(userRepository.findByEmail(userLogin));
        post.setDateOfAddition(LocalDateTime.now());
        postRepository.save(post);
        List<String> tags = PostUtilities.getTags(post.getDescription());
        saveTags(post, tags);

    }

    private List<SimplePost> parsePostsToSimplifiedPosts(List<Post> posts){
        List<SimplePost> simplePosts = new ArrayList<>();
        for (Post post : posts) {
            int rating = post.getRating().size();
            SimplePost simplePost = new SimplePost(post.getId(), post.getDescription(), post.getUser().getNick(), rating, post.getDateOfAddition());
            List<SimpleComment> simpleComments = new ArrayList<>();
            for (Comment comment : commentRepository.findAllByPost(post)) {
                int commentRating = comment.getRating().size();
                simpleComments.add(new SimpleComment(post.getId(), comment.getId(), comment.getDescription(), comment.getUser().getNick(), comment.getDateOfAddition(), commentRating));
            }
            simplePost.setComments(simpleComments);
            simplePosts.add(simplePost);
        }
        return simplePosts;
    }

    private void saveTags(Post post, List<String> tags){
        List<Tag> tagsUsedInPost = new ArrayList<>();
        for (String tagValue : tags) {
            Optional<Tag> tag = tagRepository.findByTagValue(tagValue);
            if (tag.isPresent()){
                tagsUsedInPost.add(tag.get());
            }
            else {
                Tag newTag = new Tag(tagValue);
                tagRepository.save(newTag);
                tagsUsedInPost.add(newTag);
            }
        }
        for (Tag tag : tagsUsedInPost) {
            TagUsage tagUsage = new TagUsage();
            tagUsage.setPost(post);
            tagUsage.setTag(tag);
            usedTagsRepository.save(tagUsage);
        }
    }



}
