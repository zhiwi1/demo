package com.epam.webproject.model.entity;

import java.time.LocalDateTime;

public class Post extends Entity{
    private String title;
    private String text;
    private LocalDateTime timeCreatedAt;
    private LocalDateTime timeUpdatedAt;
    private User user;
    private int complexity;
    private int countForSolve;

    public Post(String title, String text, LocalDateTime timeCreatedAt,
                LocalDateTime timeUpdatedAt, User user, int complexity, int countForSolve) {
        this.title = title;
        this.text = text;
        this.timeCreatedAt = timeCreatedAt;
        this.timeUpdatedAt = timeUpdatedAt;
        this.user = user;
        this.complexity = complexity;
        this.countForSolve = countForSolve;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Post{");
        sb.append("title='").append(title).append('\'');
        sb.append(", text='").append(text).append('\'');
        sb.append(", timeCreatedAt=").append(timeCreatedAt);
        sb.append(", timeUpdatedAt=").append(timeUpdatedAt);
        sb.append(", user=").append(user);
        sb.append(", complexity=").append(complexity);
        sb.append(", countForSolve=").append(countForSolve);
        sb.append('}');
        return sb.toString();
    }
}