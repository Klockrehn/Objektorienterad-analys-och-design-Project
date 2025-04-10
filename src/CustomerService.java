import java.sql.SQLException;

public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Lägga till en kund
    public void addCustomer(String name, String email) {
        try {
            customerRepository.addCustomer(name, email);
            System.out.println("Kund tillagd.");
        } catch (SQLException e) {
            System.err.println("Fel vid läggning av kund: " + e.getMessage());
        }
    }

    // Uppdatera kundens email
    public void updateCustomerEmail(int customerId, String email) {
        try {
            customerRepository.updateCustomerEmail(customerId, email);
            System.out.println("Kundens email uppdaterad.");
        } catch (SQLException e) {
            System.err.println("Fel vid uppdatering av kundens email: " + e.getMessage());
        }
    }
}

