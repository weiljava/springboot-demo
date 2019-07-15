package com.example.springboot.redis.domain;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: dinghw
 * Date: 2017/8/12
 * Time: 21:27
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class Article implements Serializable {
    private long id;
    private String title;//文章标题
    private String link;//指向文章网址
    private long poster;//发布文章的用户
    private long time;//文章发布时间
    private long votes;//文章得到的投票数量信息

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public long getPoster() {
        return poster;
    }

    public void setPoster(long poster) {
        this.poster = poster;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getVotes() {
        return votes;
    }

    public void setVotes(long votes) {
        this.votes = votes;
    }
}