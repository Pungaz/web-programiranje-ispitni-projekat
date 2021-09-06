package bm.resources;

import bm.DTO.Comment;
import bm.DTO.Post;
import bm.services.interfaces.CommentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/comments")
public class CommentResource {

    @Inject
    private CommentService commentService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Comment addComment(Comment comment) {
        return this.commentService.addComment(comment);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Comment editComment(Comment comment) {
        return this.commentService.editComment(comment);
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comment> listCommentsByPost(Post post, @DefaultValue("0") @QueryParam("offset") int offset, @DefaultValue("5") @QueryParam("limit") int limit) {
        return this.commentService.listCommentsByPost(post, offset, limit);
    }

    @DELETE
    public void deleteComment(long commentId) {
        this.commentService.deleteComment(commentId);
    }
}
