package gr.aueb.team14;

import gr.aueb.team14.dao.CustomerDAO;
import gr.aueb.team14.dao.TechnicianDAO;
import gr.aueb.team14.domain.Customer;
import gr.aueb.team14.domain.Technician;
import gr.aueb.team14.domain.User;

public class LoggedInUser {
    private static LoggedInUser instance = null;
    private User user;

    private LoggedInUser() {

    }

    public static LoggedInUser getInstance() {
        if (instance == null)
            instance = new LoggedInUser();

        return instance;
    }

    public boolean login(String username, String password) {
        String hashedPassword = User.getSHA1Hash(password);

        // Find a user with the same username
        Technician technician = TechnicianDAO.getInstance().find(username);
        if (technician != null) {
            // Check passwords
            if (hashedPassword.equals(technician.getPassword())) {
                this.user = technician;
                return true;
            }
        }

        Customer customer = CustomerDAO.getInstance().find(username);
        if (customer != null) {
            if (hashedPassword.equals(customer.getPassword())) {
                this.user = customer;
                return true;
            }
        }

        return false;
    }

    public User getUser() {
        return user;
    }
}