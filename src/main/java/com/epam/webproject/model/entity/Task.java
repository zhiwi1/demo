package com.epam.webproject.model.entity;


import java.util.Date;

public class Task extends Entity {
    private String title;
    private String text;
    private Date timeCreatedAt;
    private Date timeUpdatedAt;
    private long idOfUser;
    private int complexity;
    private int countForSolve;

    public Task(String title, String text, Date timeCreatedAt, Date timeUpdatedAt, long idOfUser, int complexity, int countForSolve) {
        this.title = title;
        this.text = text;
        this.timeCreatedAt = timeCreatedAt;
        this.timeUpdatedAt = timeUpdatedAt;
        this.idOfUser = idOfUser;
        this.complexity = complexity;
        this.countForSolve = countForSolve;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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


    public long getIdOfUser() {
        return idOfUser;
    }

    public void setIdOfUser(long idOfUser) {
        this.idOfUser = idOfUser;
    }

    public int getComplexity() {
        return complexity;
    }

    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }

    public int getCountForSolve() {
        return countForSolve;
    }

    public void setCountForSolve(int countForSolve) {
        this.countForSolve = countForSolve;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Post{");
        sb.append("title='").append(title).append('\'');
        sb.append(", text='").append(text).append('\'');
        sb.append(", timeCreatedAt=").append(timeCreatedAt);
        sb.append(", timeUpdatedAt=").append(timeUpdatedAt);
        sb.append(", idOfUser=").append(idOfUser);
        sb.append(", complexity=").append(complexity);
        sb.append(", countForSolve=").append(countForSolve);
        sb.append('}');
        return sb.toString();
    }
}