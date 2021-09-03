package bm.services.impl;

import bm.models.Post;
import bm.repositories.interfaces.PostRepository;

import javax.inject.Inject;
import java.util.List;

public class PostServiceImpl {

    @Inject
    private PostRepository postRepository;

    public Post addPost(Post post) {
        post.validate();
        return this.postRepository.addPost(post);
    }

    public List<Post> listAllPosts(int offset, int limit) {
        return this.postRepository.listAllPosts(offset, limit);
    }

}
