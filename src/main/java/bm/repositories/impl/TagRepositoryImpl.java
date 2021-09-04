package bm.repositories.impl;

import bm.exceptions.UnknownException;
import bm.models.Tag;
import bm.repositories.interfaces.TagRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TagRepositoryImpl extends PostgreSqlAbstractRepository implements TagRepository {

    @Override
    public Tag createTagIfNameNotExist(Tag tag) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("select * from tag where value = ?");
            preparedStatement.setString(1, tag.getValue());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next() && resultSet.getString("value").equals(tag.getValue())) {
                tag.setId(resultSet.getLong("id"));
            } else {
                String[] generatedColumns = {"id"};

                preparedStatement = connection.prepareStatement("INSERT INTO tag (value) SELECT ? WHERE NOT EXISTS" +
                        " (SELECT value FROM tag WHERE value = ?)", generatedColumns);
                preparedStatement.setString(1, tag.getValue());
                preparedStatement.setString(2, tag.getValue());

                preparedStatement.executeUpdate();
                resultSet = preparedStatement.getGeneratedKeys();

                if (resultSet.next()) {
                    tag.setId(resultSet.getLong("id"));
                }
            }
        } catch (SQLException e) {
            throw new UnknownException();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return tag;
    }

    @Override
    public Tag updateTag(Tag tag) {
        return null;
    }

    @Override
    public void deleteTag(long tag_id) {

    }
}
