package bm.repositories.interfaces;

import bm.DTO.Tag;

public interface TagRepository {
    public Tag createTagIfNameNotExist(Tag tag);

    public Tag updateTag(Tag tag);

    public void deleteTag(long tag_id);
}
