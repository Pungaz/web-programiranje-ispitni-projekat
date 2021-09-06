package bm.resources;

import bm.DTO.Post;
import bm.services.interfaces.PostService;
import bm.utils.StringUtil;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/posts")
public class PostResource {

    @Inject
    private PostService postService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Post addPost(Post post) {
        return this.postService.addPost(post);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Post> listAllPosts(@DefaultValue("0") @QueryParam("offset") int offset, @DefaultValue("5") @QueryParam("limit") int limit, @QueryParam("text") String text, @QueryParam("tag") String tag) {
        if (!StringUtil.isEmpty(text)) {
            return this.postService.listPostsByText(offset, limit, text);
        } else if (!StringUtil.isEmpty(tag)) {
            return this.postService.listPostsByTag(offset, limit, tag);
        } else {
            return this.postService.listAllPosts(offset, limit);
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Post editPost(Post post) {
        return this.postService.editPost(post);
    }

    @Path("/{postId}")
    @DELETE
    public void deletePost(@PathParam("postId") long postId) {
        this.postService.deletePost(postId);
    }

}
