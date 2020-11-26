package dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persists.Order;
import persists.Product;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Named
@ApplicationScoped
public class ProductDAO implements Dao<Product> {

    Logger logger = LoggerFactory.getLogger(ProductDAO.class);

    @Inject
    private ServletContext context;

    private Connection connection;

    @PostConstruct
    public void init() throws SQLException {
        this.connection = (Connection) context.getAttribute("jdbcConnection");
    }

    @Override
    public Product get(int id) throws SQLException {

        try (PreparedStatement stmt = connection.prepareStatement("select * from products as p where p.id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Product(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getString(4), rs.getBoolean(5));
            }
            return new Product(0, "", 0f, "",false);
        }

    }

    @Override
    public List<Product> getAll() throws SQLException {
        List<Product> products = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from products where enabled = 1")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                products.add(new Product(resultSet.getInt(1), resultSet.getString(2), resultSet.getFloat(3),resultSet.getString(4),resultSet.getBoolean(5)));
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return products;
    }

    @Override
    public void save(Product product) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("insert into products(name, price, description) values (?, ?, ?)")) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setFloat(2, product.getPrice());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.execute();
        }
    }

    @Override
    public void update(Product product) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("update products set name = ?, price = ?, description = ? where id = ?")) {

            preparedStatement.setString(1, product.getName());
            preparedStatement.setFloat(2, product.getPrice());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setInt(4, product.getId());
            preparedStatement.execute();
        }
    }

    @Override
    public void delete(Product product) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("update products set enabled = 0 where id = ?")) {
            preparedStatement.setInt(1, product.getId());
            preparedStatement.execute();
        }
    }
}
