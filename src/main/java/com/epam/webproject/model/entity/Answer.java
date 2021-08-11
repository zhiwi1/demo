package com.epam.webproject.model.entity;

import java.util.Objects;

public class Answer extends Entity {
    private long answerId;
    private String content;
    private String userLogin;
    private long taskId;
    private long userId;
    private CorrectnessOfAnswer correctness;

    public Answer(String content, String userLogin) {
        this.content = content;
        this.userLogin = userLogin;
    }

    public Answer(String content, long taskId, long userId) {
        this.content = content;
        this.taskId = taskId;
        this.userId = userId;
    }

    public Answer(long answerId, String content, String userLogin, CorrectnessOfAnswer correctness, long taskId) {
        this.answerId = answerId;
        this.content = content;
        this.userLogin = userLogin;
        this.correctness = correctness;
        this.taskId = taskId;
    }


    public long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
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

    public CorrectnessOfAnswer getCorrectness() {
        return correctness;
    }

    public void setCorrectness(CorrectnessOfAnswer correctness) {
        this.correctness = correctness;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return answerId == answer.answerId && taskId == answer.taskId && userId == answer.userId && Objects.equals(content, answer.content) && Objects.equals(userLogin, answer.userLogin) && correctness == answer.correctness;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (int) (this.answerId ^ (this.answerId >>> 32));
        for (int i = 0; i < content.length(); i++) {
            result = 31 * result + content.charAt(i);
        }
        for (int i = 0; i < userLogin.length(); i++) {
            result = 31 * result + userLogin.charAt(i);
        }
        result = 31 * result + (int) (this.taskId ^ (this.taskId >>> 32));
        result = 31 * result + (int) (this.userId ^ (this.userId >>> 32));
        result = 31 * result + correctness.hashCode();
        return result;

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Answer{");
        sb.append("answerId=").append(answerId);
        sb.append(", content='").append(content).append('\'');
        sb.append(", userLogin='").append(userLogin).append('\'');
        sb.append(", taskId=").append(taskId);
        sb.append(", userId=").append(userId);
        sb.append(", correctness=").append(correctness);
        sb.append('}');
        return sb.toString();
    }
}
