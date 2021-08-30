package bm.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class Post {

    @NotNull(message = "Title is required")
    @NotEmpty(message = "Title is required")
    private String title;

    @NotNull(message = "Text is required")
    @NotEmpty(message = "Text is required")
    private String text;

    @NotNull(message = "Author name is required")
    @NotEmpty(message = "Author name is required")
    private User author;

    @NotNull(message = "Category is required")
    @NotEmpty(message = "Category is required")
    private Category category;

    private long createdAt;
    private long numberOfVisits;
    private long id;
    private List<Comment> comments;

    public Post() {
    }

    public Post(String title, String text, User author, Category category, long id, List<Comment> comments) {
        this.title = title;
        this.text = text;
        this.createdAt = System.currentTimeMillis();
        this.numberOfVisits = 0;
        this.author = author;
        this.category = category;
        this.id = id;
        this.comments = comments;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getNumberOfVisits() {
        return numberOfVisits;
    }

    public void setNumberOfVisits(long numberOfVisits) {
        this.numberOfVisits = numberOfVisits;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}

