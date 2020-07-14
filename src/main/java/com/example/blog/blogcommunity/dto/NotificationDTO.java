package com.example.blog.blogcommunity.dto;

import lombok.Data;

@Data
public class NotificationDTO {
    private Long id;
    private Long notifier;
    private String notifierName;
    private String outerTitle;
    private Long outerid;
    private Integer type;
    private Long gmtCreate;
    private Integer status;
    private String typeName;
}

