package dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger  = LoggerFactory.getLogger(CategoryDAO.class);

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
                return new Category(rs.getInt(1), rs.getString(2), rs.getBoolean(3));
            }
            return new Category( 0, "", false);
        }

    }

    @Override
    public List<Category> getAll() throws SQLException {
        List<Category> categories = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement("select * from categories where enabled = 1")) {
            ResultSet resultSet  = preparedStatement.executeQuery();
            while (resultSet.next()) {
                categories.add(new Category(resultSet.getInt(1), resultSet.getString(2), true));
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return categories;
    }

    @Override
    public void save(Category category) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement("insert into categories(nameCategory) values (?)")) {
            preparedStatement.setString(1, category.getNameCat());
            preparedStatement.execute();
        }
    }

    @Override
    public void update(Category category) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement("update categories set nameCategory = ? where id = ?")){
            logger.debug(category.getNameCat());
            logger.debug(category.getId().toString());
            preparedStatement.setString(1,category.getNameCat());
            preparedStatement.setLong(2,category.getId());
            preparedStatement.execute();
        }
    }

    @Override
    public void delete(Category category) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement("update categories set enabled = 0 where id = ?")) {
            preparedStatement.setLong(1,category.getId());
            preparedStatement.execute();
        }
    }


}
