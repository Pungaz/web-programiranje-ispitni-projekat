package bm.repositories.impl;

import bm.models.PostTag;
import bm.repositories.interfaces.PostTagRepository;

public class PostTagRepositoryImpl implements PostTagRepository {

    @Override
    public PostTag addPostTag(long post_id, long tag_id) {
        return null;
    }

    @Override
    public void removePostFromTag(long post_id, long tag_id) {

    }
}
