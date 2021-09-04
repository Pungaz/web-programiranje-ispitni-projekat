package bm.repositories.impl;

import bm.exceptions.UnknownException;
import bm.repositories.interfaces.PostTagRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostTagRepositoryImpl extends PostgreSqlAbstractRepository implements PostTagRepository {

    @Override
    public void addPostTag(long post_id, long tag_id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO post_tag (post_id, tag_id) " +
                    "VALUES (?, ?)", generatedColumns);
            preparedStatement.setLong(1, post_id);
            preparedStatement.setLong(2, tag_id);

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

        } catch (SQLException e) {
            throw new UnknownException();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
    }

    @Override
    public void removePostFromTag(long post_id, long tag_id) {

    }
}
