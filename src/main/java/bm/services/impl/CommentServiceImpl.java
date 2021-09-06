package bm.services.impl;

import bm.DTO.Comment;
import bm.DTO.Post;
import bm.repositories.interfaces.CommentRepository;
import bm.services.interfaces.CommentService;

import javax.inject.Inject;
import java.util.List;

public class CommentServiceImpl implements CommentService {

    @Inject
    CommentRepository commentRepository;

    @Override
    public Comment addComment(Comment comment) {
        comment.validate();
        return this.commentRepository.addComment(comment);
    }

    @Override
    public void deleteComment(long commentId) {
        this.commentRepository.deleteComment(commentId);
    }

    @Override
    public Comment editComment(Comment comment) {
        comment.validate();
        return this.commentRepository.editComment(comment);
    }

    @Override
    public List<Comment> listCommentsByPost(Post post, int offset, int limit) {
        return this.commentRepository.listCommentsByPost(post, offset, limit);
    }
}
