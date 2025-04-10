import java.sql.*;

public class CustomerRepository {

    private Connection connection;

    public CustomerRepository(Connection connection) {
        this.connection = connection;
    }

    // Lägg till en kund
    public void addCustomer(String name, String email) throws SQLException {
        String sql = "INSERT INTO customers (name, email) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.executeUpdate();
        }
    }

    // Uppdatera kundens email
    public void updateCustomerEmail(int customerId, String email) throws SQLException {
        String sql = "UPDATE customers SET email = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setInt(2, customerId);
            stmt.executeUpdate();
        }
    }

    // Hämta alla kunder
    public ResultSet getAllCustomers() throws SQLException {
        String sql = "SELECT * FROM customers";
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(sql);
    }
}

