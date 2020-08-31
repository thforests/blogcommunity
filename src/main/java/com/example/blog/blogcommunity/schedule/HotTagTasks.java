package com.example.blog.blogcommunity.schedule;

import com.example.blog.blogcommunity.cache.HotTagCache;
import com.example.blog.blogcommunity.mapper.QuestionMapper;
import com.example.blog.blogcommunity.model.Question;
import com.example.blog.blogcommunity.model.QuestionExample;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author hp
 * @create 2020/8/19
 * @describe
 */

@Component
@Slf4j
public class HotTagTasks {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private HotTagCache hotTagCache;

    @Scheduled(fixedRate =  1000 * 60 * 60 * 3)
    public void hotTagSchedule() {
        int offest = 0;
        int limit = 20;
        log.info("hotTagSchedule start {}", new Date());
        List<Question> list = new ArrayList<>();

        Map<String, Integer> priorites = new HashMap<>();
        while (offest == 0 || list.size() == 0) {
            list = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offest, limit));
            for (Question question : list) {
                String[] tags = StringUtils.split(question.getTag(),",");
                for (String tag : tags) {
                    Integer priority = priorites.get(tag);
                    if (priority != null) {
                        priorites.put(tag, priority + 5 + question.getCommentCount());
                    } else {
                        priorites.put(tag, 5 + question.getCommentCount());
                    }
                }
                offest += limit;
            }
            hotTagCache.updateTags(priorites);
            log.info("hotTagSchedule stop {}", new Date());
        }
    }
}