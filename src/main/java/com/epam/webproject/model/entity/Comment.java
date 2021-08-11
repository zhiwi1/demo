package com.epam.webproject.model.entity;

import java.util.Date;
import java.util.Objects;

public class Comment extends Entity {
    private String text;
    private Date timeCreatedAt;
    private String loginOfUser;
    private long userId;
    private long postId;

    public Comment(String text, Date timeCreatedAt, long userId, long postId) {
        this.text = text;
        this.timeCreatedAt = timeCreatedAt;
        this.userId = userId;
        this.postId = postId;
    }

    public Comment(String text, Date timeCreatedAt, Date timeUpdatedAt, String loginOfUser) {
        this.text = text;
        this.timeCreatedAt = timeCreatedAt;
        this.loginOfUser = loginOfUser;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTimeCreatedAt() {
        return timeCreatedAt;
    }

    public void setTimeCreatedAt(Date timeCreatedAt) {
        this.timeCreatedAt = timeCreatedAt;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getLoginOfUser() {
        return loginOfUser;
    }

    public void setLoginOfUser(String loginOfUser) {
        this.loginOfUser = loginOfUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return userId == comment.userId && postId == comment.postId && Objects.equals(text, comment.text) && Objects.equals(timeCreatedAt, comment.timeCreatedAt);
    }

    @Override
    public int hashCode() {
        int result = 1;
        for (int i = 0; i < text.length(); i++) {
            result = 31 * result + text.charAt(i);
        }
        for (int i = 0; i < loginOfUser.length(); i++) {
            result = 31 * result + loginOfUser.charAt(i);
        }
        result = 31 * result + timeCreatedAt.hashCode();
        result = 31 * result + (int) (this.userId ^ (this.userId >>> 32));
        result = 31 * result + (int) (this.postId ^ (this.postId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Comment{");
        sb.append("text='").append(text).append('\'');
        sb.append(", timeCreatedAt=").append(timeCreatedAt);
        sb.append(", loginOfUser='").append(loginOfUser).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
