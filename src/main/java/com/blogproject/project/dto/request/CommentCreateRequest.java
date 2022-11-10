package com.blogproject.project.dto.request;

import lombok.Data;

@Data
public class CommentCreateRequest {
    Long id;
    Long userId;
    Long postId;
    String text;
}
