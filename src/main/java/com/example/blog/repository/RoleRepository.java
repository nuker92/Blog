package com.example.blog.repository;

import com.example.blog.entity.Role;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("roleRepository")
public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findByRole(String role);
}
