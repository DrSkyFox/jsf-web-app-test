package dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persists.Category;
import persists.Order;

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
public class OrderDAO implements Dao<Order> {


    Logger logger  = LoggerFactory.getLogger(OrderDAO.class);

    @Inject
    private ServletContext context;

    private Connection connection;

    @PostConstruct
    public void init() throws SQLException {
        this.connection = (Connection) context.getAttribute("jdbcConnection");
    }

    @Override
    public Order get(int id) throws SQLException {

        try (PreparedStatement stmt = connection.prepareStatement("select * from orders as o where o.id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                return new Order(rs.getInt(1), rs.getString(2), rs.getInt(3),rs.getBoolean(4));
            }
            return new Order(0, "", 0, false);
        }

    }

    @Override
    public List<Order> getAll() throws SQLException {
        List<Order> orders = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement("select * from orders where enabled = 1")) {
            ResultSet resultSet  = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orders.add(new Order(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getBoolean(4)));
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        return orders;
    }

    @Override
    public void save(Order order) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement("insert into orders(description, customer_id) values (?, ?)")) {
            preparedStatement.setString(1, order.getDescription());
            preparedStatement.setInt(2, order.getCustomerID());
            preparedStatement.execute();
        }
    }

    @Override
    public void update(Order order) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement("update orders set description = ?, customer_id = ? where id = ?")){

            preparedStatement.setString(1,order.getDescription());
            preparedStatement.setInt(2, order.getCustomerID());
            preparedStatement.setInt(3, order.getId());
            preparedStatement.execute();
        }
    }

    @Override
    public void delete(Order order) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement("update orders set enabled = 0 where id = ?")) {
            preparedStatement.setInt(1,order.getId());
            preparedStatement.execute();
        }
    }
}
