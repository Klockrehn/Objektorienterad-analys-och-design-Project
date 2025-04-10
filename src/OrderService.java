import java.sql.*;
import java.util.List;

public class OrderService {

    private OrderRepository orderRepository;
    private ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    // Lägg en order
    public void placeOrder(int customerId, List<OrderProduct> products) {
        try {
            double totalPrice = 0;
            for (OrderProduct product : products) {
                // Hämta produkt från databasen med hjälp av productId
                ResultSet rs = productRepository.searchProductsByName(product.getProductName());
                if (rs.next()) {
                    int stockQuantity = rs.getInt("stock_quantity");
                    double price = rs.getDouble("price");

                    if (stockQuantity >= product.getQuantity()) {
                        totalPrice += price * product.getQuantity();
                    } else {
                        System.err.println("Otillräckligt lager för produkt: " + product.getProductName());
                        return; // Sluta om lagret är otillräckligt
                    }
                }
            }

            // Skapa en order för kunden
            orderRepository.addOrder(customerId, totalPrice);

            // Lägg till varje produkt i ordern
            for (OrderProduct product : products) {
                ResultSet rs = productRepository.searchProductsByName(product.getProductName());
                if (rs.next()) {
                    int productId = rs.getInt("id");
                    orderRepository.addOrderProduct(customerId, productId, product.getQuantity());
                }
            }

            System.out.println("Order lagd med totala priset: " + totalPrice);

        } catch (SQLException e) {
            System.err.println("Fel vid orderläggning: " + e.getMessage());
        }
    }
}
