import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Försök att ansluta till databasen med hjälp av DatabaseConnection.getConnection()
        try (Connection conn = DatabaseConnection.getConnection()) {
            System.out.println("Ansluten till databasen!");

            // Skapa en lista med OrderProduct (produkter som ska ingå i ordern)
            List<OrderProduct> orderProducts = new ArrayList<>();
            orderProducts.add(new OrderProduct(1, "Produkt1", 2, 100.0));  // Exempel på produkt
            orderProducts.add(new OrderProduct(2, "Produkt2", 3, 150.0));  // Exempel på produkt

            // Skapa en instans av repository-klasserna
            OrderRepository orderRepo = new OrderRepository(conn);
            ProductRepository productRepo = new ProductRepository(conn);

            // Skapa en instans av OrderService och lägg till en order
            OrderService orderService = new OrderService(orderRepo, productRepo);

            // Exempel på kund-ID (kan hämtas från användarinmatning eller en annan källa)
            int customerId = 1;  // Detta är ett exempel, justera efter behov

            // Lägg till en order med de angivna produkterna
            orderService.placeOrder(customerId, orderProducts);

            System.out.println("Order lagd framgångsrikt!");

        } catch (SQLException e) {
            System.out.println("Fel vid anslutning till databasen: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ett oväntat fel inträffade: " + e.getMessage());
        }
    }
}
