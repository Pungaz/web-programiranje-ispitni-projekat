package bm.repositories.interfaces;

import bm.models.PostTag;

public interface PostTagRepository {
    public void addPostTag(long postId, long tagId);

    public void removePostFromTag(long postId);
}
