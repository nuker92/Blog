package com.example.blog.repository;

import com.example.blog.entity.Post;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("postRepository")
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findById(Long id);

    @Query("SELECT distinct(p) FROM Post p \n" +
            "left join p.usedTags ut on p.id = ut.post \n" +
            "left join ut.tag t on ut.tag = t.id \n" +
            "where t.tagValue in :tags")
    List<Post> findAllWithCustomQuery(@Param("tags") List<String> tags, Pageable p);

    @Query("SELECT distinct(p) FROM User u\n" +
            "left join u.posts p on p.user = u.id\n" +
            "left join u.comments c on c.user = u.id\n" +
            "where u.nick = :userNick")
    List<Post> findAllUserPost(@Param("userNick") String userNick, Pageable p);

}
