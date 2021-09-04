package bm.services.impl;

import bm.models.Post;
import bm.models.Tag;
import bm.repositories.interfaces.PostRepository;
import bm.repositories.interfaces.PostTagRepository;
import bm.repositories.interfaces.TagRepository;
import bm.utils.StringUtil;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PostServiceImpl {

    @Inject
    private PostRepository postRepository;
    @Inject
    private TagRepository tagRepository;
    @Inject
    private PostTagRepository postTagRepository;

    public Post addPost(Post post) {
        Post finalPost  = this.postRepository.addPost(post);
        List<String> tags = new ArrayList<>();
        System.out.println(post);
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

}
