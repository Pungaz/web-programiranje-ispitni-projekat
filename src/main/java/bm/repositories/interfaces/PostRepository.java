package bm.repositories.interfaces;

import bm.DTO.Category;
import bm.DTO.Comment;
import bm.DTO.Post;
import bm.DTO.Tag;

import java.util.List;

public interface PostRepository {
    public Post addPost(Post post);

    public Post updatePost(Post post);

    public List<Post> listAllPosts(int offset, int limit);

    public void deletePost(long post_id);

    public List<Post> listPostsByTag(int offset, int limit, String tag);

    public List<Post> listPostsByText(int offset, int limit, String text);

    public Post getPostById(long postId);
}
