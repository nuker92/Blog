package com.example.blog.repository;

import com.example.blog.entity.tags.Tag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Qualifier("tagRepository")
public interface TagRepository extends CrudRepository<Tag, Long> {

    Optional<Tag> findByTagValue(String tagValue);

}
