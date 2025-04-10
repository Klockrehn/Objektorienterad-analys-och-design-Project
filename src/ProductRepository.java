import java.sql.*;

public class ProductRepository {

    private Connection connection;

    public ProductRepository(Connection connection) {
        this.connection = connection;
    }

    // Lägg till en produkt
    public void addProduct(String name, double price, int stockQuantity) throws SQLException {
        String sql = "INSERT INTO products (name, price, stock_quantity) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setDouble(2, price);
            stmt.setInt(3, stockQuantity);
            stmt.executeUpdate();
        }
    }

    // Uppdatera produktens pris
    public void updateProductPrice(int productId, double newPrice) throws SQLException {
        String sql = "UPDATE products SET price = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, newPrice);
            stmt.setInt(2, productId);
            stmt.executeUpdate();
        }
    }

    // Uppdatera lagersaldo
    public void updateProductStock(int productId, int newStockQuantity) throws SQLException {
        String sql = "UPDATE products SET stock_quantity = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, newStockQuantity);
            stmt.setInt(2, productId);
            stmt.executeUpdate();
        }
    }

    // Sök produkter efter namn
    public ResultSet searchProductsByName(String name) throws SQLException {
        String sql = "SELECT * FROM products WHERE name LIKE ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + name + "%");
            return stmt.executeQuery();
        }
    }
}
