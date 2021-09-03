package bm.resources;

import bm.models.Post;
import bm.services.PostService;

import javax.inject.Inject;
import javax.validation.Valid;
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
    public Post addPost(Post post){
        post.validate();
        return this.postService.addPost(post);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Post> listAllPosts(@DefaultValue("0") @QueryParam("offset") int offset, @DefaultValue("5") @QueryParam("limit") int limit) {
        return this.postService.listAllPosts(offset, limit);
    }

}
