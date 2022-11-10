package com.blogproject.project.service;

import com.blogproject.project.dto.request.PostCreateRequest;
import com.blogproject.project.entity.Post;
import com.blogproject.project.entity.User;
import com.blogproject.project.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;

    public List<Post> getAllPost(Optional<Long> userId) {
        if (userId.isPresent())
            return postRepository.findByUserId(userId.get());
        return postRepository.findAll();
    }

    public Post getOnePostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createOnePost(PostCreateRequest newPostCreateRequest) {
        User user = userService.getOneUser(newPostCreateRequest.getUserId());
        if (user == null) {
            return null;
        }

        Post toSave = new Post();
        toSave.setId(newPostCreateRequest.getId());
        toSave.setText(newPostCreateRequest.getText());
        toSave.setTitle(newPostCreateRequest.getTitle());
        toSave.setUser(user);
        return postRepository.save(toSave);
    }
}
