package bm.services.interfaces;

import bm.DTO.Comment;
import bm.DTO.Post;

import java.util.List;

public interface CommentService {
    public Comment addComment(Comment comment);

    public void deleteComment(long commentId);

    public Comment editComment(Comment comment);

    public List<Comment> listCommentsByPostId(long postId, int offset, int limit);
}
