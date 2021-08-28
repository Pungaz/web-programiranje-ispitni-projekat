package model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Tag {
    @NotNull(message = "At least one tag is required")
    @NotEmpty(message = "At least one tag is required")
    private String name;

    private long id;

    public Tag(String name, long id) {
        this.name = name;
        this.id = id;
    }

    public String getTagName() {
        return name;
    }

    public void setTagName(String tagName) {
        this.name = tagName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}


