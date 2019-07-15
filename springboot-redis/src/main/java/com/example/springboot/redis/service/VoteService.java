package com.example.springboot.redis.service;


import com.example.springboot.redis.domain.Article;

import java.util.List;

public interface VoteService {
    /**
     * 初始化数据
     */
    void initData();

    /**
     * 投票功能
     *
     * @param id        文章ID
     * @param columnKey 属性字段名
     * @param voteNum   投票数量
     * @return 返回当前投票数
     */
    long lockVote(long id, String columnKey, long voteNum);


    /**
     * 返回文章列表
     *
     * @return
     */
    List<Article> list();


    /**
     * 根据ID获取文章信息
     *
     * @param id
     * @return
     */
    Article getArticleById(long id);

}
