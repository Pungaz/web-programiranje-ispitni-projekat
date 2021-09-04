package bm.repositories.interfaces;

import bm.models.Tag;

public interface TagRepository {
    public Tag createTagIfNameNotExist(Tag tag);

    public Tag updateTag(Tag tag);

    public void deleteTag(long tag_id);
}
