package com.epam.webproject.model.entity;

import java.util.Objects;

public class Answer extends Entity {
    private long answerId;
    private String content;
    private long like;
    private String userLogin;
    private long taskId;
    private long userId;

    public Answer(String content,long like, long taskId, long userId) {
        this.content = content;
        this.like = like;
        this.taskId = taskId;
        this.userId = userId;
    }

    public Answer(String content, long like, String userLogin) {
        this.content = content;
        this.like = like;
        this.userLogin = userLogin;
    }
    public Answer(long answerId,String content, long like, String userLogin){
        this.answerId=answerId;
        this.content = content;
        this.like = like;
        this.userLogin = userLogin;
    }

    public long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }

    public void setLike(long like) {
        this.like = like;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
//todo equals and hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return like == answer.like && taskId == answer.taskId && userId == answer.userId && Objects.equals(content, answer.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, like, taskId, userId);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Answer{");
        sb.append("answerId=").append(answerId);
        sb.append(", content='").append(content).append('\'');
        sb.append(", like=").append(like);
        sb.append(", userLogin='").append(userLogin).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
