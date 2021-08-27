package model;

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

    private long createdAt;
    private long numberOfVisits;
    private User author;
    private List<Comment> comments;
    private List<Tag> tags;
    private Category category;

    public Post(String title, String text, User author, List<Comment> comments, List<Tag> tags, Category category) {
        this.title = title;
        this.text = text;
        this.createdAt = System.currentTimeMillis();
        this.numberOfVisits = 0;
        this.author = author;
        this.comments = comments;
        this.tags = tags;
        this.category = category;
    }
}

