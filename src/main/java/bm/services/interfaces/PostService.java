package bm.services.interfaces;

import bm.DTO.Post;

import java.util.List;

public interface PostService {

    public Post addPost(Post post);

    public List<Post> listAllPosts(int offset, int limit);

    List<Post> listPostsByText(int offset, int limit, String text);

    List<Post> listPostsByTag(int offset, int limit, String tag);

    public Post editPost(Post post);

    public void deletePost(long postId);
}
