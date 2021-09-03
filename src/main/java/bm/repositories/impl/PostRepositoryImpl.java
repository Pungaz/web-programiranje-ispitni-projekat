package bm.repositories.impl;

import bm.exceptions.UnknownException;
import bm.models.Category;
import bm.models.Post;
import bm.repositories.interfaces.PostRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostRepositoryImpl extends PostgreSqlAbstractRepository implements PostRepository {

    @Override
    public Post addPost(Post post) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO post(title, text, author, created_at, number_of_visits, category_id)\n" +
                    "VALUES (?, ?, ?, ?, ?, ?)\n" +
                    "RETURNING *;", generatedColumns);
            preparedStatement.setString(1, post.getTitle());
            preparedStatement.setString(2, post.getText());
            preparedStatement.setString(3, post.getAuthor());
            preparedStatement.setLong(4, System.currentTimeMillis());
            preparedStatement.setLong(5, 0);
            preparedStatement.setLong(6, post.getCategory().getId());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                post.setId(resultSet.getLong("id"));
                post.setCreatedAt(resultSet.getLong("created_at"));
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            throw new UnknownException();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return post;
    }

    @Override
    public Post updatePost(Post post) {
        return null;
    }

    @Override
    public List<Post> listAllPosts(int offset, int limit) {
        List<Post> posts = new ArrayList<>();
        int postId = 1, postTitle = 2, postText = 3, postAuthor = 4, postCreatedAt = 5, postNumberOfVisits = 6,
                categoryId = 8, categoryName = 9, categoryDescription = 10;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT\n" +
                    "    post.*, category.*\n" +
                    "FROM\n" +
                    "    post\n" +
                    "FULL JOIN category \n" +
                    "   ON post.category_id = category.id\n" +
                    "WHERE post.category_id IS NOT NULL\n" +
                    "order by post.created_at desc " +
                    "OFFSET ? LIMIT ?");
            preparedStatement.setLong(1, offset);
            preparedStatement.setLong(2, limit);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Category category = new Category(resultSet.getLong(categoryId), resultSet.getString(categoryName),
                        resultSet.getString(categoryDescription), null);

                posts.add(new Post(resultSet.getLong(postId), resultSet.getString(postTitle), resultSet.getString(postText),
                        resultSet.getString(postAuthor), resultSet.getLong(postCreatedAt),
                        resultSet.getLong(postNumberOfVisits), category, null));
            }

        } catch (Exception e) {
            throw new UnknownException();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return posts;
    }

    @Override
    public List<Post> listPostsByTags(long tag_id) {
        return null;
    }

    @Override
    public void deletePost(long post_id) {

    }
}
