package com.example.blog.repository;

import com.example.blog.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("userRepository")
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);

    User findByNick(String nick);

}
