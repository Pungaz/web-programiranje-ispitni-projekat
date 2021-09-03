package bm.repositories.interfaces;

import bm.models.PostTag;

public interface PostTagRepository {
    public PostTag addPostToTag(long post_id, long tag_id);

    public void removePostFromTag(long post_id, long tag_id);
}
