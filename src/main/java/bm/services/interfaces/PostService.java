package bm.services.interfaces;

import bm.models.Post;

import java.util.List;

public interface PostService {

    public Post addPost(Post post);

    public List<Post> listAllPosts(int offset, int limit);

    public Post editPost(Post post);

    public void deletePost(long postId);

}
