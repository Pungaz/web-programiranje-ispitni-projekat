package bm.resources;

import bm.models.Post;
import bm.services.impl.PostServiceImpl;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/post")
public class PostResource {

    @Inject
    private PostServiceImpl postServiceImpl;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Post addPost(Post post){
        return this.postServiceImpl.addPost(post);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Post> listAllPosts(@DefaultValue("0") @QueryParam("offset") int offset, @DefaultValue("5") @QueryParam("limit") int limit) {
        return this.postServiceImpl.listAllPosts(offset, limit);
    }

}
