package com.blogproject.project.dto.request;

import lombok.Data;

@Data
public class PostCreateRequest {
    Long id;
    String text;
    String title;
    Long userId;
}
