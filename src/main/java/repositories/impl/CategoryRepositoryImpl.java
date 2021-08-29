package repositories.impl;

import model.Category;
import repositories.CategoryRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CategoryRepositoryImpl extends PostgreSqlAbstractRepository implements CategoryRepository {

    @Override
    public Category addCategory(Category category) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO category (description, name) VALUES(?, ?, ?)", generatedColumns);
            preparedStatement.setString(1, category.getDescription());
            preparedStatement.setLong(2, category.getId());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                category.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return category;
    }

    @Override
    public Category updateCategory(Category category) {
        return null;
    }

    @Override
    public List<Category> listAllCategories() {
        return null;
    }

    @Override
    public void deleteCategory(long category_id) {

    }
}
