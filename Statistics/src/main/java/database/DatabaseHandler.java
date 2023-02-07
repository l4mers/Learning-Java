package database;

import components.Customer;
import components.Order;
import components.OrderItem;
import components.Shoe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {
    final private String user;
    final private String password;
    final private String connectionURL;

    public DatabaseHandler(String database, String user, String password) {
        this.user = user;
        this.password = password;

        connectionURL = "jdbc:mysql://localhost:3306/"+database+"?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
    }

    public List<String> getColors() {
        List<String> colors = new ArrayList<>();
        String query = "SELECT color.color FROM color";
        try (Connection c = DriverManager.getConnection(connectionURL, user, this.password);
             PreparedStatement stmt = c.prepareStatement(query)) {
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                colors.add(result.getString("color"));
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return colors;
    }

    public List<OrderItem> getOrderItems() {
        List<OrderItem> orderItems = new ArrayList<>();
        String query = "SELECT order_item.quantity, customer_order.id AS oID, customer_order.date, " +
                "product_info.id AS pID, product_info.name, brand.brand, color.color, size.size, product_info.price, " +
                "customer.id AS cID, customer.first_name, customer.last_name, county.county " +
                "FROM order_item " +
                "INNER JOIN customer_order ON order_item.customer_order_id=customer_order.id " +
                "INNER JOIN customer ON customer_order.customer_id=customer.id " +
                "INNER JOIN county ON customer.county_id=county.id " +
                "INNER JOIN product ON order_item.product_id=product.id " +
                "INNER JOIN size ON product.size_id=size.id " +
                "INNER JOIN color ON product.color_id=color.id " +
                "INNER JOIN product_info ON product.product_info_id=product_info.id " +
                "INNER JOIN brand ON product_info.brand_id=brand.id";
        try (Connection c = DriverManager.getConnection(connectionURL, user, this.password);
             PreparedStatement stmt = c.prepareStatement(query)) {

            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                OrderItem oi = new OrderItem(result.getInt("quantity"),
                        new Order(result.getInt("oID"),
                                new Customer(result.getInt("cID"),
                                        result.getString("first_name"),
                                        result.getString("last_name"),
                                        result.getString("county")),
                                result.getDate("date").toLocalDate()),
                                new Shoe(result.getInt("pID"),
                                        result.getString("name"),
                                        result.getString("brand"),
                                        result.getString("color"),
                                        result.getString("size"),
                                        result.getInt("price")));
                orderItems.add(oi);
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return orderItems;
    }
    public ArrayList<Order> getOrderList(){
        ArrayList<Order> orders = new ArrayList<>();
        String query = "SELECT customer.id AS cid, customer.first_name, customer.last_name, county.county, customer_order.id, customer_order.date " +
                "FROM customer_order " +
                "INNER JOIN customer ON customer_order.customer_id=customer.id " +
                "INNER JOIN county ON customer.county_id=county.id";

        try (Connection c = DriverManager.getConnection(connectionURL, user, this.password);
             PreparedStatement stmt = c.prepareStatement(query)) {
            ResultSet result = stmt.executeQuery();
            while(result.next()) {
                orders.add(new Order(result.getInt("id"),
                        new Customer(result.getInt("cid"),
                                result.getString("first_name"),
                                result.getString("last_name"),
                                result.getString("county")),
                        result.getDate("date").toLocalDate()));
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return orders;
    }
}
