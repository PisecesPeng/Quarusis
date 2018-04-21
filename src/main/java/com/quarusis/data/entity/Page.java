package com.quarusis.data.entity;

import org.springframework.stereotype.Component;

@Component
public class Page {
    private int id;
    private String uin;
    private String topic;
    private String title;
    private String text;
    private String url;
    private int whetherComment;
    private int commentSum;
    private int whetherRead;

    public void setWhetherComment(int whetherComment) {
        this.whetherComment = whetherComment;
    }

    public void setUin(String uin) {
        this.uin = uin;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public String getUin() {
        return uin;
    }

    public String getTopic() {
        return topic;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getUrl() {
        return url;
    }

    public int getWhetherComment() {
        return whetherComment;
    }

    public int getCommentSum() {
        return commentSum;
    }

    public int getWhetherRead() {
        return whetherRead;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", uin='" + uin + '\'' +
                ", topic='" + topic + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", url='" + url + '\'' +
                ", whetherComment=" + whetherComment +
                ", commentSum=" + commentSum +
                ", whetherRead=" + whetherRead +
                '}';
    }
}
