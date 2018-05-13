package com.example.blog.service;

import com.example.blog.entity.*;
import com.example.blog.restObjects.SimpleVote;
import com.example.blog.repository.CommentRepository;
import com.example.blog.repository.PostRepository;
import com.example.blog.repository.UserRepository;
import com.example.blog.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("voteService")
public class VoteService implements IVoteService {

    private VoteRepository voteRepository;

    private PostRepository postRepository;

    private UserRepository userRepository;

    private CommentRepository commentRepository;

    @Autowired
    public VoteService(VoteRepository voteRepository, PostRepository postRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.voteRepository = voteRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Long addVote(SimpleVote simpleVote, String userLogin) {
        User user = userRepository.findByEmail(userLogin);
        Item item;
        Vote vote;
        switch (simpleVote.getItemType()){
            case POST:
                item = postRepository.findById(simpleVote.getId()).get();
                vote = findOne(item, user);
                deleteOrCreateVote(user, item, vote, VoteType.POST);
                return voteRepository.countByPost((Post) item);
            case COMMENT:
                item = commentRepository.findById(simpleVote.getId()).get();
                vote = findOne(item, user);
                deleteOrCreateVote(user, item, vote, VoteType.COMMENT);
                return voteRepository.countByComment((Comment) item);
            default:
                return 0L;
        }
    }

    private void deleteOrCreateVote(User user, Item item, Vote vote, VoteType voteType) {
        if (vote != null){
            voteRepository.delete(vote);
        }
        else {
            vote = new Vote();
            switch (voteType){
                case POST:
                    vote.setPost((Post) item);
                    break;
                case COMMENT:
                    vote.setComment((Comment) item);
                    break;
            }
            vote.setUser(user);
            voteRepository.save(vote);
        }
    }

    private Vote findOne(Item item, User user) {
        if (item instanceof Post) {
            return voteRepository.findByPostAndUser((Post) item, user);
        }
        return voteRepository.findByCommentAndUser((Comment) item, user);
    }

    @Override
    public boolean isUserItem(SimpleVote simpleVote, String userLogin) {
        switch (simpleVote.getItemType()){
            case COMMENT:
                Optional<Comment> comment = commentRepository.findById(simpleVote.getId());
                return comment.get().getUser().getEmail().equals(userLogin);
            case POST:
                Optional<Post> post = postRepository.findById(simpleVote.getId());
                return post.get().getUser().getEmail().equals(userLogin);
        }
        return false;
    }
}
