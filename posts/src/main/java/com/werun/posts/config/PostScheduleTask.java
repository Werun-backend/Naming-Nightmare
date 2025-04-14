package com.werun.posts.config;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.werun.posts.domain.Posts;
import com.werun.posts.mapper.PostsMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Component // 让 Spring 扫描并管理
public class PostScheduleTask {

    @Resource
    private PostsMapper postsMapper;

    @Scheduled(fixedRate = 60000) // 每分钟执行一次
    public void publishScheduledPosts() {
        List<Posts> postsToPublish = postsMapper.selectList(
                new QueryWrapper<Posts>()
                        .eq("scheduled", true)
                        .le("scheduled_time", LocalDateTime.now())
                        .eq("visible", false)
        );

        for (Posts post : postsToPublish) {
            post.setVisible(true);
            post.setScheduled(false);
            post.setScheduledTime(null);
            postsMapper.updateById(post);
        }
    }
}
