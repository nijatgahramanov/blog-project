package com.blogproject.project.service;

import com.blogproject.project.dto.request.PostCreateRequest;
import com.blogproject.project.dto.request.PostUpdateRequest;
import com.blogproject.project.dto.response.LikeResponse;
import com.blogproject.project.dto.response.PostResponse;
import com.blogproject.project.entity.Like;
import com.blogproject.project.entity.Post;
import com.blogproject.project.entity.User;
import com.blogproject.project.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    private  LikeService likeService;

    public void setLikeService(LikeService likeService){
        this.likeService = likeService;
    }

    public List<PostResponse> getAllPost(Optional<Long> userId) {
        List<Post> list;
        if (userId.isPresent())
            list =  postRepository.findByUserId(userId.get());
        list = postRepository.findAll();
        return list.stream().map(p->{
            List<LikeResponse> likes = likeService.getAllLikeWithParam(null,Optional.of(p.getId()));
            return new PostResponse(p,likes);}).collect(Collectors.toList());
    }

    public Post getOnePostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createOnePost(PostCreateRequest newPostCreateRequest) {
        User user = userService.getOneUserById(newPostCreateRequest.getUserId());
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

    public Post updateOnePostById(Long postId, PostUpdateRequest updatePost) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            Post toUpdate = post.get();
            toUpdate.setText(updatePost.getText());
            toUpdate.setTitle(updatePost.getTitle());
            postRepository.save(toUpdate);
            return toUpdate;
        }
        return null;
    }

    public void deleteOnePostById(Long postId) {
        postRepository.deleteById(postId);
    }
}
