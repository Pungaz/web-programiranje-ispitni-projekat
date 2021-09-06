package bm.repositories.interfaces;

import bm.DTO.Tag;

import java.util.List;

public interface TagRepository {
    public Tag createTagIfNameNotExist(Tag tag);

    public List<Tag> findTagsByPostId(long postId);
}
