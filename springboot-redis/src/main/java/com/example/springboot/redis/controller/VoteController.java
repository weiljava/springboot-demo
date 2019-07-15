package com.example.springboot.redis.controller;

import com.example.springboot.redis.domain.Article;
import com.example.springboot.redis.service.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * User: dinghw
 * Date: 2017/8/12
 * Time: 21:32
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@RestController
public class VoteController {
    private static final Logger log = LoggerFactory.getLogger(VoteController.class);

    @Autowired
    private VoteService voteService;

    @RequestMapping(value = "/api/vote/init")
    public void init() {
        voteService.initData();
    }

    @RequestMapping(value = "/api/vote/{id}", method = RequestMethod.GET)
    public long vote(@PathVariable("id") long id) {
        return voteService.lockVote(id, "votes", 1l);
    }

    @RequestMapping(value = "/api/article/{id}", method = RequestMethod.GET)
    public Article getArticle(@PathVariable("id") long id) {
        return voteService.getArticleById(id);
    }
}