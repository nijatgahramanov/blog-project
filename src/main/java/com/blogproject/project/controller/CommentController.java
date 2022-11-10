package com.blogproject.project.controller;


import com.blogproject.project.dto.request.CommentCreateRequest;
import com.blogproject.project.dto.request.CommentUpdateRequest;
import com.blogproject.project.entity.Comment;
import com.blogproject.project.repository.CommentRepository;
import com.blogproject.project.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public List<Comment> getAllComment(@RequestParam Optional<Long> userId,
                                       @RequestParam Optional<Long> postId) {
        return commentService.getAllCommentWithParam(userId, postId);
    }

    @GetMapping("/{commentId}")
    public Comment getOneComment(@PathVariable Long commentId) {
        return commentService.getOneCommentById(commentId);
    }

    @PostMapping
    public Comment createOneComment(@RequestBody CommentCreateRequest request) {
        return commentService.createOneComment(request);
    }

    @PutMapping("/{commentId}")
    public Comment updateOneComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequest request) {
        return commentService.updateOneCommentById(commentId, request);
    }

    @DeleteMapping("/{commentId}")
    public void deleteOneComment(@PathVariable Long commentId) {
         commentService.deleteOneComment(commentId);
    }


}
