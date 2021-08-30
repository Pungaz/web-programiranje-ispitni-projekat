package bm.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Comment {

    @NotNull(message = "Author name is required")
    @NotEmpty(message = "Author name is required")
    private String author;

    @NotNull(message = "Text is required")
    @NotEmpty(message = "Text is required")
    private String text;

    private long createdAt;
    private Post post;
    private long id;

    public Comment() {
    }

    public Comment(String author, String text, long createdAt, Post post, long id) {
        this.author = author;
        this.text = text;
        this.createdAt = createdAt;
        this.post = post;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

}