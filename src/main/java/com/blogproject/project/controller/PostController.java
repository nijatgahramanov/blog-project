package com.blogproject.project.controller;

import com.blogproject.project.dto.request.PostCreateRequest;
import com.blogproject.project.entity.Post;
import com.blogproject.project.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    public List<Post> getAllPost(@RequestParam Optional<Long> userId){
        return postService.getAllPost(userId);
    }

    @GetMapping("/{postId}")
    public Post getOnePost(@PathVariable Long postId){
        return postService.getOnePostById(postId);
    }

    @PostMapping
    public Post createOnePost(@RequestBody PostCreateRequest newPostCreateRequest){
        return postService.createOnePost(newPostCreateRequest);
    }

   


}
