package com.example.blog.repository;

import com.example.blog.entity.tags.TagUsage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("usesTagsRepository")
public interface UsedTagsRepository extends CrudRepository<TagUsage, Long> {

}
