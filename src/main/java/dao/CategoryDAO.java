package dao;

import jdk.jfr.Name;
import persists.Category;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class CategoryDAO implements Dao<Category>{

    @Inject
    private ServletContext context;

    private Connection connection;

    @PostConstruct
    public void init() throws SQLException {
        this.connection = (Connection) context.getAttribute("jdbcConnection");
    }

    @Override
    public Category get(int id) throws SQLException {

        try (PreparedStatement stmt = connection.prepareStatement("select * from categories as ct where ct.id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                return new Category(rs.getInt(1), rs.getString(2), rs.getInt(3));
            }
            return new Category(-1, "", 0);
        }

    }

    @Override
    public List<Category> getAll() throws SQLException {
        List<Category> categories = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement("select * fromcategories where enabled = 1")) {
            ResultSet resultSet  = preparedStatement.executeQuery();
            while (resultSet.next()) {
                categories.add(new Category(resultSet.getInt(1), resultSet.getString(2), 1));
            }
        }
        return categories;
    }

    @Override
    public void save(Category category) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement("insert into categories(nameCattegory) values (?)")) {
            preparedStatement.setString(1, category.getNameCat());
            preparedStatement.execute();
        }
    }

    @Override
    public void update(Category category) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement("update categories set nameCattegory = ? where id = ?")){
            preparedStatement.setString(1,category.getNameCat());
            preparedStatement.setInt(2,category.getiD());
        }
    }

    @Override
    public void delete(Category category) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement("update categories set enabled = 0 where id = ?")) {
            preparedStatement.setInt(1,category.getiD());
            preparedStatement.execute();
        }
    }

    private void createTableIfNotExists(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("create table if not exists categories (\n" +
                    "\tid int auto_increment primary key,\n" +
                    "    nameCattegory varchar(25),\n" +
                    "    enabled tinyint default 1,\n" +
                    ");");
        }
    }
}
