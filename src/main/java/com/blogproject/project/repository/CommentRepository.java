package com.blogproject.project.repository;

import com.blogproject.project.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {



}
