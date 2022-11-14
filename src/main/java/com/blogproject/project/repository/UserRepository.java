package com.blogproject.project.repository;

import com.blogproject.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {


    User findByUserName(String username);
}
