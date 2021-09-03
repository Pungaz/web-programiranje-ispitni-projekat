package bm.models;

import bm.exceptions.ValidationException;
import bm.utils.CategoryUtil;
import bm.utils.StringUtil;

import java.util.List;

public class Post {

    private String title;
    private String text;
    private String author;
    private Category category;
    private long createdAt;
    private long numberOfVisits;
    private long id;
    private List<Comment> comments;

    public Post() {
    }

    public Post(long id, String title, String text, String author, long createdAt, long numberOfVisits, Category category, List<Comment> comments) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.author = author;
        this.createdAt = createdAt;
        this.numberOfVisits = numberOfVisits;
        this.category = category;
        this.comments = comments;
    }

    public void validate() {
        if (StringUtil.isEmpty(title)) {
            throw new ValidationException("Validation of post unsuccessful, title is invalid");
        } else if (StringUtil.isEmpty(text)) {
            throw new ValidationException("Validation of post unsuccessful, text is invalid");
        } else if (StringUtil.isEmpty(author)) {
            throw new ValidationException("Validation of post unsuccessful, author is invalid");
        } else if (CategoryUtil.isEmpty(category)) {
            throw new ValidationException("Validation of post unsuccessful, category is invalid");
        }
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}

