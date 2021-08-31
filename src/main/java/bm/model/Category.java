package bm.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class Category {

    @NotNull(message = "Category name is required")
    @NotEmpty(message = "Category name is required")
    private String name;

    @NotNull(message = "Category description is required")
    @NotEmpty(message = "Category description is required")
    private String description;

    private long id;
    private List<Post> posts;

    public Category() {
    }

    public Category(long id, String name, String description, List<Post> posts) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.posts = posts;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
