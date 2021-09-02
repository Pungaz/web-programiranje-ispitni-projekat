package bm.repositories.impl;

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
                    category.setId(resultSet.getLong(1));
                }

                resultSet.close();
                preparedStatement.close();
                connection.close();
            } else if (resultSet.getBoolean("exists")) {
                //TODO IF A CATEGORY ALREADY EXISTS, FORWARD USER TO SAID CATEGORY
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
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        long idReturnedByQuery = 0;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("UPDATE category SET name = ? , description = ? WHERE id = ? RETURNING *");
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getDescription());
            preparedStatement.setLong(3, category.getId());

            preparedStatement.executeQuery();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                idReturnedByQuery = resultSet.getLong(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        if (idReturnedByQuery == 0) {
            //TODO db didn't return a valid object, the targeted object in the db probably didn't get updated either. Return this information and redirect user
            //return GRESKA;
        }
        return category;
    }

    @Override
    public List<Category> listAllCategories() {
        List<Category> categories = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from category");

            while (resultSet.next()) {
                categories.add(new Category(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("description"), null));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
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

            if(resultSet.next() && resultSet.getLong("count") == 0){
                preparedStatement = connection.prepareStatement("DELETE FROM category where id = ?");
                preparedStatement.setLong(1, categoryId);
                preparedStatement.executeUpdate();

            }else {
                //TODO There's at leas 1 post in that category, notify the user about this
            }

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeResultSet(resultSet);
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }

}
