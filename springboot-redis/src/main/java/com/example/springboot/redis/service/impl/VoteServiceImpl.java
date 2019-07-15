package com.example.springboot.redis.service.impl;

import com.example.springboot.redis.annotation.RedisLock;
import com.example.springboot.redis.annotation.RedisLockKey;
import com.example.springboot.redis.domain.Article;
import com.example.springboot.redis.service.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dinghw
 * Date: 2017/8/12
 * Time: 21:39
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Service
public class VoteServiceImpl implements VoteService {
    private static final Logger log = LoggerFactory.getLogger(VoteServiceImpl.class);
    private static int i = 0;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void initData() {
        Article article = new Article();
        article.setId(1l);
        article.setTitle("一篇有趣的博文");
        article.setLink("http://blog.csdn.net/wlwlwlwl015/article/details/51201392");
        article.setPoster(1l);
        article.setTime(System.currentTimeMillis());
        article.setVotes(0l);


        redisTemplate.opsForHash().put("article:" + article.getId(), "title", article.getTitle());
        redisTemplate.opsForHash().put("article:" + article.getId(), "link", article.getLink());
        redisTemplate.opsForHash().put("article:" + article.getId(), "poster", article.getPoster());
        redisTemplate.opsForHash().put("article:" + article.getId(), "time", article.getTime());
        redisTemplate.opsForHash().put("article:" + article.getId(), "votes", article.getVotes());


    }

    @RedisLock(lockKey = "article:")
    public long lockVote(long id, @RedisLockKey(order = 0) String columnKey, long voteNum) {
        long votes = (Long) redisTemplate.opsForHash().get("article:" + id, "votes");
        voteNum = votes + voteNum;
        redisTemplate.opsForHash().put("article:" + id, "votes", voteNum);
        return voteNum;
    }

    @Override
    public List<Article> list() {
        return null;
    }

    @Override
    public Article getArticleById(long id) {
        String title = (String) redisTemplate.opsForHash().get("article:" + id, "title");
        String link = (String) redisTemplate.opsForHash().get("article:" + id, "link");
        long poster = (Long) redisTemplate.opsForHash().get("article:" + id, "poster");
        long time = (Long) redisTemplate.opsForHash().get("article:" + id, "time");
        long votes = (Long) redisTemplate.opsForHash().get("article:" + id, "votes");

        Article a = new Article();
        a.setTitle(title);
        a.setLink(link);
        a.setPoster(poster);
        a.setTime(time);
        a.setVotes(votes);
        return a;
    }
}