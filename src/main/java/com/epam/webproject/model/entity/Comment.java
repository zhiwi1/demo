package com.epam.webproject.model.entity;

import java.time.LocalDateTime;

public class Comment  extends Entity{
    private String text;
    private LocalDateTime timeCreatedAt;
    private LocalDateTime timeUpdatedAt;
    private User user;
    private Post post;

    public Comment(String text, LocalDateTime timeCreatedAt,
                   LocalDateTime timeUpdatedAt, User user, Post post) {
        this.text = text;
        this.timeCreatedAt = timeCreatedAt;
        this.timeUpdatedAt = timeUpdatedAt;
        this.user = user;
        this.post = post;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Comment{");
        sb.append("text='").append(text).append('\'');
        sb.append(", timeCreatedAt=").append(timeCreatedAt);
        sb.append(", timeUpdatedAt=").append(timeUpdatedAt);
        sb.append(", user=").append(user);
        sb.append(", post=").append(post);
        sb.append('}');
        return sb.toString();
    }
}
