import java.sql.*;

public class OrderRepository {

    private Connection connection;

    public OrderRepository(Connection connection) {
        this.connection = connection;
    }

    // Lägg till en order
    public void addOrder(int customerId, double totalPrice) throws SQLException {
        String sql = "INSERT INTO orders (customer_id, total_price) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, customerId);
            stmt.setDouble(2, totalPrice);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int orderId = rs.getInt(1);
                System.out.println("Order created with ID: " + orderId);
            }
        }
    }

    // Lägg till produkter i en order
    public void addOrderProduct(int orderId, int productId, int quantity) throws SQLException {
        String sql = "INSERT INTO orders_products (order_id, product_id, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            stmt.setInt(2, productId);
            stmt.setInt(3, quantity);
            stmt.executeUpdate();
        }
    }

    // Hämta orderhistorik för en kund
    public ResultSet getOrderHistory(int customerId) throws SQLException {
        String sql = "SELECT * FROM orders WHERE customer_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            return stmt.executeQuery();
        }
    }
}
