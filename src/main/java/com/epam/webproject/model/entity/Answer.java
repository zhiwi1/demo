package com.epam.webproject.model.entity;

public class Answer extends Entity {
    private String content;
    private Post post;
    private User user;
    private int like;

    public Answer(String content, Post post, User user, int like) {
        this.content = content;
        this.post = post;
        this.user = user;
        this.like = like;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Answer{");
        sb.append("content='").append(content).append('\'');
        sb.append(", post=").append(post);
        sb.append(", user=").append(user);
        sb.append(", like=").append(like);
        sb.append('}');
        return sb.toString();
    }
}
