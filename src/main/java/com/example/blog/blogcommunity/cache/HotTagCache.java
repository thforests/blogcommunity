package com.example.blog.blogcommunity.cache;

import com.example.blog.blogcommunity.dto.HotTagDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author hp
 * @create 2020/8/19
 * @describe
 */
@Component
@Data
public class HotTagCache {

    private List<String> hots = new ArrayList<>();

    public void updateTags(Map<String, Integer> tags) {
        int max = 10;
        PriorityQueue<HotTagDTO> priorityQueue = new PriorityQueue<>(max);

        tags.forEach((name, priority) -> {
            HotTagDTO hotTagDTO = new HotTagDTO();
            hotTagDTO.setName(name);
            hotTagDTO.setPriority(priority);
            if (priorityQueue.size() < max) {
                priorityQueue.add(hotTagDTO);
            } else {
                HotTagDTO miniHotTag = priorityQueue.peek();
                if (hotTagDTO.compareTo(miniHotTag) > 0) {
                    priorityQueue.poll();
                    priorityQueue.add(hotTagDTO);
                }

            }

        });
        List<String> sortedTags = new ArrayList<>();
        HotTagDTO poll = priorityQueue.poll();
        while (poll != null) {
            sortedTags.add(0, poll.getName());
            poll = priorityQueue.poll();
        }
        hots = sortedTags;
        System.out.println(hots);
    }
}
