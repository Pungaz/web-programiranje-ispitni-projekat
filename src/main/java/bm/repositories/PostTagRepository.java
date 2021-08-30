package bm.repositories;

import bm.model.PostTag;

public interface PostTagRepository {
    public PostTag addPostToTag(long post_id, long tag_id);

    public void removePostFromTag(long post_id, long tag_id);
}
