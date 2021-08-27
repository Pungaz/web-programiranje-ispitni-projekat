package model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class Tag {
    @NotNull(message = "At least one tag is required")
    @NotEmpty(message = "At least one tag is required")
    private List<String> tags;

    public Tag(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}


