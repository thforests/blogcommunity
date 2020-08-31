package com.example.blog.blogcommunity.dto;

import lombok.Data;

/**
 * @author hp
 * @create 2020/8/13
 * @describe
 */

@Data
public class QuestionQueryDTO {
    private String search;
    private Integer size;
    private String tag;
    private Integer page;
}
