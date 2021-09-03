package bm.repositories.interfaces;

import bm.models.Comment;

import java.util.List;

public interface CommentRepository {
    public Comment addComment(Comment comment);

    public Comment updateComment(Comment comment);

    public List<Comment> listCommentsByPost(long post_id);

    public void deleteComment(long comment_id);
}
