package repositories;

import model.Tag;

public interface TagRepository {
    public Tag createTag(Tag tag);

    public Tag updateTag(Tag tag);

    public void deleteTag(long tag_id);
}
