package com.example.blog.repository;

import com.example.blog.entity.Comment;
import com.example.blog.entity.Post;
import com.example.blog.entity.User;
import com.example.blog.entity.Vote;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("voteRepository")
public interface VoteRepository extends CrudRepository<Vote, Long> {

    Long countByPost(Post post);

    Long countByComment(Comment comment);

    Vote findByPostAndUser(Post post, User user);

    Vote findByCommentAndUser(Comment comment, User user);

}
