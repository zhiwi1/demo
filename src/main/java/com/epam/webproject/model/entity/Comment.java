package com.epam.webproject.model.entity;

import java.util.Date;
import java.util.Objects;

public class Comment  extends Entity{
    private String text;
    private Date timeCreatedAt;
    private Date timeUpdatedAt;
    private String loginOfUser;
    private long userId;
    private long postId;

    public Comment(String text, Date timeCreatedAt, Date timeUpdatedAt, long userId, long postId) {
        this.text = text;
        this.timeCreatedAt = timeCreatedAt;
        this.timeUpdatedAt = timeUpdatedAt;
        this.userId = userId;
        this.postId = postId;
    }

    public Comment(String text, Date timeCreatedAt, Date timeUpdatedAt, String loginOfUser) {
        this.text = text;
        this.timeCreatedAt = timeCreatedAt;
        this.timeUpdatedAt = timeUpdatedAt;
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

    public Date getTimeUpdatedAt() {
        return timeUpdatedAt;
    }

    public void setTimeUpdatedAt(Date timeUpdatedAt) {
        this.timeUpdatedAt = timeUpdatedAt;
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
        return userId == comment.userId && postId == comment.postId && Objects.equals(text, comment.text) && Objects.equals(timeCreatedAt, comment.timeCreatedAt) && Objects.equals(timeUpdatedAt, comment.timeUpdatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, timeCreatedAt, timeUpdatedAt, userId, postId);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Comment{");
        sb.append("text='").append(text).append('\'');
        sb.append(", timeCreatedAt=").append(timeCreatedAt);
        sb.append(", timeUpdatedAt=").append(timeUpdatedAt);
        sb.append(", loginOfUser='").append(loginOfUser).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
