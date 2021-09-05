package bm.services.impl;

import bm.models.Post;
import bm.models.Tag;
import bm.repositories.interfaces.PostRepository;
import bm.repositories.interfaces.PostTagRepository;
import bm.repositories.interfaces.TagRepository;
import bm.services.interfaces.PostService;
import bm.utils.StringUtil;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PostServiceImpl implements PostService {

    @Inject
    private PostRepository postRepository;
    @Inject
    private TagRepository tagRepository;
    @Inject
    private PostTagRepository postTagRepository;

    public Post addPost(Post post) {
        post.validate();
        Post finalPost = this.postRepository.addPost(post);
        List<String> tags = new ArrayList<>();
        Arrays.stream(post.getTagsString().split(",")).forEach((String tagString) -> {
            if (!StringUtil.isEmpty(tagString)) {
                Tag tag = this.tagRepository.createTagIfNameNotExist(new Tag(0, tagString.trim()));
                tags.add(tag.getValue());
                this.postTagRepository.addPostTag(finalPost.getId(), tag.getId());
            }
        });

        finalPost.setTagsString(String.join(", ", tags));
        return finalPost;
    }

    public List<Post> listAllPosts(int offset, int limit) {
        return this.postRepository.listAllPosts(offset, limit);
    }

    public List<Post> listPostsByText(int offset, int limit, String text) {
        return this.postRepository.listPostsByText(offset, limit, text);
    }

    public List<Post> listPostsByTag(int offset, int limit, String tag) {
        return this.postRepository.listPostsByTag(offset, limit, tag);
    }

    public Post editPost(Post post) {
        post.validate();
        return this.postRepository.updatePost(post);
    }

    public void deletePost(long postId) {
        this.postRepository.deletePost(postId);
        this.postTagRepository.removePostFromTag(postId);
    }

}
