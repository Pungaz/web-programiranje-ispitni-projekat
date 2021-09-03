package bm.repositories.interfaces;

import bm.models.Post;

import java.util.List;

public interface PostRepository {
    public Post addPost(Post post);

    public Post updatePost(Post post);

    public List<Post> listAllPosts(int offset, int limit);

    public List<Post> listPostsByTags(long tag_id);

    public void deletePost(long post_id);
}
