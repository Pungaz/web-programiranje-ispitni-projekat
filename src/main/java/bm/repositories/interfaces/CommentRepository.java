package bm.repositories.interfaces;

import bm.DTO.Comment;
import bm.DTO.Post;

import java.util.List;

public interface CommentRepository {
    public Comment addComment(Comment comment);

    public Comment editComment(Comment comment);

    public List<Comment> listCommentsByPostId(long postId, int offset, int limit);

    public void deleteComment(long commentId);
}
