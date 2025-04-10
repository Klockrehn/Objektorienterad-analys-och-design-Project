import java.sql.SQLException;

public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Lägga till en produkt
    public void addProduct(String name, double price, int stockQuantity) {
        try {
            productRepository.addProduct(name, price, stockQuantity);
            System.out.println("Produkt tillagd.");
        } catch (SQLException e) {
            System.err.println("Fel vid läggning av produkt: " + e.getMessage());
        }
    }

    // Uppdatera produktens pris
    public void updateProductPrice(int productId, double newPrice) {
        try {
            productRepository.updateProductPrice(productId, newPrice);
            System.out.println("Produktens pris uppdaterat.");
        } catch (SQLException e) {
            System.err.println("Fel vid uppdatering av produktens pris: " + e.getMessage());
        }
    }

    // Uppdatera lagersaldo
    public void updateProductStock(int productId, int newStockQuantity) {
        try {
            productRepository.updateProductStock(productId, newStockQuantity);
            System.out.println("Lagersaldo uppdaterat.");
        } catch (SQLException e) {
            System.err.println("Fel vid uppdatering av lagersaldo: " + e.getMessage());
        }
    }
}

