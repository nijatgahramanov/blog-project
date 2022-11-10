package com.blogproject.project.service;

import com.blogproject.project.dto.request.LikeCreateRequest;
import com.blogproject.project.dto.response.LikeResponse;
import com.blogproject.project.entity.Like;
import com.blogproject.project.entity.Post;
import com.blogproject.project.entity.User;
import com.blogproject.project.repository.LikeRepository;
import com.blogproject.project.repository.PostRepository;
import com.blogproject.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserService userService;
    private final PostService postService;

    public List<LikeResponse> getAllLikeWithParam(Optional<Long> userId, Optional<Long> postId) {
        List<Like> list;
        if (userId.isPresent() && postId.isPresent()) {
            list = likeRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            list = likeRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            list = likeRepository.findByPostId(postId.get());
        } else
            list = likeRepository.findAll();

        return list.stream()
                .map(like -> new LikeResponse(like)).collect(Collectors.toList());

    }

    public Like createOneLike(LikeCreateRequest request) {
        User user = userService.getOneUserById(request.getUserId());
        Post post = postService.getOnePostById(request.getPostId());
        if (user != null && post != null) {
            Like likeToSave = new Like();
            likeToSave.setPost(post);
            likeToSave.setUser(user);
            likeToSave.setId(request.getId());
            return likeRepository.save(likeToSave);
        } else {
            return null;
        }
    }

    public void deleteOneLike(Long likeId) {
        likeRepository.deleteById(likeId);
    }

    public Like getOneLikeById(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }
}
