package com.blogproject.project.controller;

import com.blogproject.project.dto.request.LikeCreateRequest;
import com.blogproject.project.dto.response.LikeResponse;
import com.blogproject.project.entity.Like;
import com.blogproject.project.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikeController {
    private final LikeService likeService;

    @GetMapping
    public List<LikeResponse> getAllLike(@RequestParam Optional<Long> userId,
                                         @RequestParam Optional<Long> postId) {
        return likeService.getAllLikeWithParam(userId, postId);
    }

    @PostMapping
    public Like createOneLike(@RequestBody LikeCreateRequest request) {
        return likeService.createOneLike(request);
    }

    @GetMapping("/{likeId}")
    public Like getOneLike(@PathVariable Long likeId) {
        return likeService.getOneLikeById(likeId);
    }

    @DeleteMapping("/{likeId}")
    public void deleteOneLike(@PathVariable Long likeId) {
        likeService.deleteOneLike(likeId);
    }

}
