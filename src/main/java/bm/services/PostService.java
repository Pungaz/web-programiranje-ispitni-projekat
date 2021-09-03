package bm.services;

import bm.models.Category;
import bm.models.Post;
import bm.repositories.PostRepository;

import javax.inject.Inject;
import java.util.List;

public class PostService {

    @Inject
    private PostRepository postRepository;

    public Post addPost(Post post) {
        return this.postRepository.addPost(post);
    }


    public List<Post> listAllPosts(int offset, int limit) {
        return this.postRepository.listAllPosts(offset, limit);
    }

}
