package com.example.blog.blogcommunity.dto;

import lombok.Data;

/**
 * @author hp
 * @create 2020/8/20
 * @describe
 */

@Data
public class HotTagDTO implements Comparable {
    private String name;
    private Integer priority;

    @Override
    public int compareTo(Object o) {
        return this.getPriority() - ((HotTagDTO) o).getPriority();
    }
}
