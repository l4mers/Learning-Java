package database;

import components.*;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseHandler {
    final private String user;
    final private String password;
    final private String connectionURL;

    public DatabaseHandler(String database, String user, String password) {
        this.user = user;
        this.password = password;

        connectionURL = "jdbc:mysql://localhost:3306/"+database+"?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
    }
    public void addLogin(int id, String email, String password){
        String query = "INSERT INTO login (login.customer_id, login.email, login.password) VALUES (?, ?, ?)";
        try (Connection c = DriverManager.getConnection(connectionURL, user, this.password);
             PreparedStatement stmt = c.prepareStatement(query)){
            String test = String.valueOf(password.hashCode());
            System.out.println(test);
            stmt.setInt(1, id);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Shoe> getAllProductsByCategory(String category) {
        String query = "SELECT product_info.id, product_info.name, product_info.price, brand.brand" +
                " FROM product_info" +
                " INNER JOIN brand ON product_info.brand_id=brand.id" +
                " INNER JOIN product_category ON product_info.id=product_category.product_info_id" +
                " INNER JOIN category ON product_category.category_id=category.id" +
                " WHERE category.category=?";
        return getShoes(category, query);
    }

    private ArrayList<Shoe> getShoes(String category, String query) {
        try (Connection c = DriverManager.getConnection(connectionURL, user, this.password);
             PreparedStatement stmt = c.prepareStatement(query)){
            stmt.setString(1, category);

            return getShoes(c, stmt);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<Shoe> getShoes(Connection c, PreparedStatement stmt) throws SQLException {
        ResultSet result = stmt.executeQuery();
        ArrayList<Shoe> shoeList = new ArrayList<>();
        while (result.next()){
            shoeList.add(new Shoe(result.getInt("id"),
                    result.getString("name"),
                    result.getString("brand"),
                    result.getInt("price")));
        }
        c.close();
        return shoeList;
    }

    public ArrayList<Shoe> getAllProductsByBrand(String brand) {
        String query = "SELECT product_info.id, product_info.name, product_info.price, brand.brand" +
                " FROM product_info" +
                " INNER JOIN brand ON product_info.brand_id=brand.id" +
                " WHERE brand.brand=?";
        return getShoes(brand, query);
    }
    public ArrayList<Shoe> getAllProducts() {
        String query = "SELECT product_info.id, product_info.name, product_info.price, brand.brand" +
                " FROM product_info" +
                " INNER JOIN brand ON product_info.brand_id=brand.id";
        try (Connection c = DriverManager.getConnection(connectionURL, user, this.password);
             PreparedStatement stmt = c.prepareStatement(query)){

            return getShoes(c, stmt);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean emailDoesNotExist(String email){
        String query = "SELECT login.email FROM login WHERE login.email=?";
        try (Connection c = DriverManager.getConnection(connectionURL, user, this.password);
             PreparedStatement stmt = c.prepareStatement(query)) {
            stmt.setString(1, email);

            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                return false;
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return true;
    }
    public ArrayList<String> getAllCounty(){
        String query = "SELECT county.county FROM county";
        ArrayList<String> counties = new ArrayList<>();
        try (Connection c = DriverManager.getConnection(connectionURL, user, this.password);
             PreparedStatement stmt = c.prepareStatement(query)) {

            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                counties.add(result.getString("county"));
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return counties;
    }
    public int callAddToCard(Item item, Order order){
        try (Connection c = DriverManager.getConnection(connectionURL, user, this.password);
             CallableStatement stmt = c.prepareCall("CALL AddToCart(?,?,?,?,?)")){
            stmt.setInt(1, order.getCustomer().getId());
            stmt.setInt(2, order.getId());
            stmt.setInt(3, item.getProduct().id());
            stmt.setDate(4, Date.valueOf(order.getDate().toString()));
            stmt.registerOutParameter(5, Types.INTEGER);
            stmt.executeQuery();
            return stmt.getInt(5);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int getIdFromStockProduct(int id, int size, String color){
        String query = "SELECT product.id" +
                " FROM product" +
                " INNER JOIN product_info ON product_info.id=product.product_info_id" +
                " INNER JOIN size ON product.size_id=size.id" +
                " INNER JOIN color ON product.color_id=color.id" +
                " WHERE product.product_info_id=?" +
                " AND size.size=?" +
                " AND color.color=?";

        try (Connection c = DriverManager.getConnection(connectionURL, user, this.password);
             PreparedStatement stmt = c.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.setInt(2, size);
            stmt.setString(3, color);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                return result.getInt("id");
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return 0;
    }

    public void createAccount(String email, String pw, String fName, String lName, String county) {
        String query = "INSERT INTO customer (customer.first_name, customer.last_name, customer.county_id)" +
                "VALUES (?,?,(SELECT county.id FROM county WHERE county.county=?))";
        try (Connection c = DriverManager.getConnection(connectionURL, user, this.password);
             PreparedStatement stmt = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, fName);
            stmt.setString(2, lName);
            stmt.setString(3, county);
            int lastID = stmt.executeUpdate();

            ResultSet result = stmt.getGeneratedKeys();
            if(result.next()){
                addLogin(result.getInt(1), email, pw);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Customer getCustomerByLogin(String email, String pw) {
        String query = "SELECT customer.id, customer.first_name, customer.last_name, county.county FROM customer" +
                " INNER JOIN county ON customer.county_id=county.id" +
                " INNER JOIN login ON customer.id=login.customer_id" +
                " WHERE login.email=?" +
                " AND login.password=?";

        try (Connection c = DriverManager.getConnection(connectionURL, user, this.password);
             PreparedStatement stmt = c.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setString(2, pw);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                return new Customer(result.getInt("id"), result.getString("first_name"),
                        result.getString("last_name"), result.getString("county"));
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return null;
    }

    public ArrayList<Integer> getShoeSizeByShoeID(int id){
        ArrayList<Integer> shoeSizeList = new ArrayList<>();
        String query = "SELECT DISTINCT size.size FROM size" +
                " INNER JOIN product ON product.size_id=size.id" +
                " INNER JOIN product_info ON product.product_info_id=product_info.id" +
                " WHERE product.product_info_id=?";
        try (Connection c = DriverManager.getConnection(connectionURL, user, this.password);
             PreparedStatement stmt = c.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                shoeSizeList.add(result.getInt("size"));
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return shoeSizeList;
    }

    public ArrayList<String> getShoeColorByShoeID(int id){
        ArrayList<String> shoeSizeList = new ArrayList<>();
        String query = "SELECT DISTINCT color.color FROM color" +
                " INNER JOIN product ON product.color_id=color.id" +
                " INNER JOIN product_info ON product.product_info_id=product_info.id" +
                " WHERE product.product_info_id=?";
        try (Connection c = DriverManager.getConnection(connectionURL, user, this.password);
             PreparedStatement stmt = c.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                shoeSizeList.add(result.getString("color"));
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return shoeSizeList;
    }

    public ArrayList<String> getAllCategories(){
        ArrayList<String> categories = new ArrayList<>();
        String query = "SELECT category.category FROM category";

        try (Connection c = DriverManager.getConnection(connectionURL, user, this.password);
             PreparedStatement stmt = c.prepareStatement(query)) {
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                categories.add(result.getString("category"));
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return categories;
    }
    public boolean shoeNotInStock(int id, String color, int size){
        String query = "SELECT product.quantity" +
                " FROM product" +
                " INNER JOIN product_info ON product_info.id=product.product_info_id" +
                " INNER JOIN size ON product.size_id=size.id" +
                " INNER JOIN color ON product.color_id=color.id" +
                " WHERE product.product_info_id=?" +
                " AND size.size=?" +
                " AND color.color=?";

        try (Connection c = DriverManager.getConnection(connectionURL, user, this.password);
             PreparedStatement stmt = c.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.setInt(2, size);
            stmt.setString(3, color);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                System.out.println("Quantity" + result.getInt("quantity"));
                return result.getInt("quantity") > 0;
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return false;
    }
    public boolean shoeNotInStock(StockProduct product){
        String query = "SELECT product.quantity" +
                " FROM product" +
                " INNER JOIN size ON product.size_id=size.id" +
                " INNER JOIN color ON product.color_id=color.id" +
                " WHERE product.id=?" +
                " AND size.size=?" +
                " AND color.color=?";

        try (Connection c = DriverManager.getConnection(connectionURL, user, this.password);
             PreparedStatement stmt = c.prepareStatement(query)) {
            stmt.setInt(1, product.id());
            stmt.setInt(2, product.size());
            stmt.setString(3, product.color());
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                System.out.println("Quantity" + result.getInt("quantity"));
                return result.getInt("quantity") > 0;
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return false;
    }
    public ArrayList<String> getAllBrand(){
        ArrayList<String> brand = new ArrayList<>();
        String query = "SELECT brand.brand FROM brand";

        try (Connection c = DriverManager.getConnection(connectionURL, user, this.password);
             PreparedStatement stmt = c.prepareStatement(query)) {
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                brand.add(result.getString("brand"));
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return brand;
    }

    public ArrayList<Order> getCustomerOrders(Customer customer) {
        ArrayList<Order> orders = new ArrayList<>();

        String query = "SELECT * FROM customer_order WHERE customer_order.customer_id=?";

        try (Connection c = DriverManager.getConnection(connectionURL, user, this.password);
             PreparedStatement stmt = c.prepareStatement(query)) {
            stmt.setInt(1, customer.getId());
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                orders.add(new Order(result.getInt("id"), customer, result.getDate("date").toLocalDate()));
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return orders;
    }

    public ArrayList<Item> getItemsByOrder(Order order) {
        ArrayList<Item> items = new ArrayList<>();

        String query = "SELECT order_item.quantity, product.id AS product_id, size.size," +
                " color.color, product_info.id AS shoe_id, product_info.name," +
                " product_info.price, brand.brand" +
                " FROM order_item" +
                " INNER JOIN product ON order_item.product_id=product.id" +
                " INNER JOIN size ON product.size_id=size.id" +
                " INNER JOIN color ON product.color_id=color.id" +
                " INNER JOIN product_info ON product.product_info_id=product_info.id" +
                " INNER JOIN brand ON product_info.brand_id=brand.id" +
                " WHERE order_item.customer_order_id=?";

        try (Connection c = DriverManager.getConnection(connectionURL, user, this.password);
             PreparedStatement stmt = c.prepareStatement(query)) {

            stmt.setInt(1, order.getId());
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                items.add(new Item(new StockProduct(result.getInt("product_id"),
                        new Shoe(result.getInt("shoe_id"),
                                result.getString("name"),
                                result.getString("brand"),
                                result.getInt("price")),
                        result.getInt("size"),
                        result.getString("color")),
                        order,
                        result.getInt("quantity")));
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return items;
    }

    public boolean updatePassword(Customer customer, String oldPW, String newPW) {
        String query = "UPDATE login" +
                " SET login.password=?" +
                " WHERE login.customer_id=?" +
                " AND login.password=?";
        try (Connection c = DriverManager.getConnection(connectionURL, user, this.password);
             PreparedStatement stmt = c.prepareStatement(query)) {

            stmt.setInt(1, customer.getId());
            stmt.setString(2, newPW);
            stmt.setString(3, oldPW);

            ResultSet result = stmt.executeQuery();
            if(result.next()){
                return true;
            }

        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return false;
    }
}
