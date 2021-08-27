package model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Category {

    @NotNull(message = "Category name is required")
    @NotEmpty(message = "Category name is required")
    private String name;

    @NotNull(message = "Category description is required")
    @NotEmpty(message = "Category description is required")
    private String description;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
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
}
