package bm.repositories.impl;

import bm.model.Category;
import bm.model.Post;
import bm.repositories.CategoryRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                    category.setId(resultSet.getInt(1));
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
        return null;
    }

    @Override
    public List<Category> listAllCategories() {
        List<Category> categories = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from category");

            resultSet = statement.executeQuery("SELECT to_json(sub) AS categories_with_posts\n" +
                    "FROM  (\n" +
                    "   SELECT c.*, json_agg(post) AS \"posts\"\n" +
                    "   FROM   category c\n" +
                    "   LEFT   JOIN post ON  post.category_id = c.id\n" +
                    "   WHERE  c.id IN (SELECT id from category)\n" +
                    "   GROUP  BY c.id\n" +
                    "   ) sub\t");

            while (resultSet.next()) {
                HashMap<String, Object> map = mapper.readValue(resultSet.getString(1), HashMap.class);

                Category category = mapper.convertValue(map, Category.class);

                categories.add(category);
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
    public void deleteCategory(long category_id) {

    }


}
