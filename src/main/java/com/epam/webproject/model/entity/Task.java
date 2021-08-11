package com.epam.webproject.model.entity;


import java.util.Date;
import java.util.Objects;

public class Task extends Entity {
    private String title;
    private String text;
    private Date timeCreatedAt;
    private long idOfUser;
    private int complexity;
    private int countForSolve;

    public Task(String title, String text, Date timeCreatedAt, long idOfUser, int complexity, int countForSolve) {
        this.title = title;
        this.text = text;
        this.timeCreatedAt = timeCreatedAt;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return idOfUser == task.idOfUser && complexity == task.complexity && countForSolve == task.countForSolve && Objects.equals(title, task.title) && Objects.equals(text, task.text) && Objects.equals(timeCreatedAt, task.timeCreatedAt);
    }

    @Override
    public int hashCode() {
        int result = 1;
        for (int i = 0; i < title.length(); i++) {
            result = 31 * result + title.charAt(i);
        }
        for (int i = 0; i < text.length(); i++) {
            result = 31 * result + text.charAt(i);
        }
        result = 31 * timeCreatedAt.hashCode();
        result = 31 * result + (int) (this.idOfUser ^ (this.idOfUser >>> 32));
        result = 31 * result + complexity;
        result = 31 * result + countForSolve;
        return result;

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Task{");
        sb.append("title='").append(title).append('\'');
        sb.append(", text='").append(text).append('\'');
        sb.append(", timeCreatedAt=").append(timeCreatedAt);
        sb.append(", idOfUser=").append(idOfUser);
        sb.append(", complexity=").append(complexity);
        sb.append(", countForSolve=").append(countForSolve);
        sb.append('}');
        return sb.toString();
    }
}