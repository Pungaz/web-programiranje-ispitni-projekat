package bm.repositories.impl;

import bm.exceptions.UnknownException;
import bm.exceptions.ValidationException;
import bm.models.Category;
import bm.repositories.CategoryRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepositoryImpl extends PostgreSqlAbstractRepository implements CategoryRepository {

    @Override
    public Category addCategory(Category category) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("select exists(select from category where name = ?)");
            preparedStatement.setString(1, category.getName());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next() && !resultSet.getBoolean("exists")) {
                String[] generatedColumns = {"id"};

                preparedStatement = connection.prepareStatement("INSERT INTO category\n" +
                        "    (name, description)\n" +
                        "SELECT ?, ?\n" +
                        "WHERE\n" +
                        "    NOT EXISTS (SELECT name FROM category WHERE name = ?)", generatedColumns);
                preparedStatement.setString(1, category.getName());
                preparedStatement.setString(2, category.getDescription());
                preparedStatement.setString(3, category.getName());

                preparedStatement.executeUpdate();
                resultSet = preparedStatement.getGeneratedKeys();

                if (resultSet.next()) {
                    category.setId(resultSet.getLong("id"));
                }

                resultSet.close();
                preparedStatement.close();
                connection.close();
            } else if (resultSet.getBoolean("exists")) {
                //TODO IF A CATEGORY ALREADY EXISTS, FORWARD USER TO SAID CATEGORY
            }
        } catch (SQLException e) {
            throw new UnknownException();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return category;
    }

    @Override
    public Category updateCategory(Category category) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int idReturnedByQuery = 0;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("WITH rows AS(UPDATE category SET name = ?, description = ? " +
                    "WHERE id = ? AND NOT EXISTS (SELECT name FROM category WHERE name = ?) RETURNING *) SELECT count(*) FROM rows");
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getDescription());
            preparedStatement.setLong(3, category.getId());
            preparedStatement.setString(4, category.getName());

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next() && resultSet.getInt("count") != 0) {
                idReturnedByQuery = resultSet.getInt("count");
            }

        } catch (Exception e) {
            throw new UnknownException();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        if (idReturnedByQuery == 0) {
            //TODO db didn't return a valid object, the targeted object in the db didn't get updated. Return this information and redirect user
        }
        return category;
    }

    @Override
    public List<Category> listAllCategories(int offset, int limit) {
        List<Category> categories = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM category OFFSET ? LIMIT ?");
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, limit);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                categories.add(new Category(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("description"), null));
            }

        } catch (Exception e) {
            throw new UnknownException();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return categories;
    }

    @Override
    public void deleteCategory(long categoryId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM post WHERE category_id = ?");
            preparedStatement.setLong(1, categoryId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next() && resultSet.getLong("count") == 0) {
                preparedStatement = connection.prepareStatement("DELETE FROM category where id = ?");
                preparedStatement.setLong(1, categoryId);
                preparedStatement.executeUpdate();

            } else {
                //TODO There's at leas 1 post in that category, notify the user about this
            }

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new UnknownException();
        } finally {
            this.closeResultSet(resultSet);
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }

}
