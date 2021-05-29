package gr.aueb.team14.dao;

import java.util.ArrayList;
import java.util.List;

import gr.aueb.team14.domain.Customer;

public class CustomerDAO {
    // Singleton instance
    private static CustomerDAO instance = null;

    private List<Customer> customers;

    private CustomerDAO() {
        customers = new ArrayList<>();
    }

    public static CustomerDAO getInstance() {
        if (instance == null)
            instance = new CustomerDAO();

        return instance;
    }

    public Customer find(String email) {
        for (Customer customer : customers) {
            if (email.equals(customer.getUsername()))
                return customer;
        }
        return null;
    }

    public void save(Customer customer) {
        if (!customers.contains(customer))
            customers.add(customer);
    }

    public void delete(Customer customer) {
        customers.remove(customer);
    }
}