package gr.aueb.team14.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import gr.aueb.team14.R;
import gr.aueb.team14.dao.CustomerDAO;
import gr.aueb.team14.domain.Customer;
import gr.aueb.team14.domain.User;

public class CustomerRegistration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Add listener to button responsible for handling customer registration
        Button customerRegistrationButton = (Button) findViewById(R.id.customerRegistrationButton);
        customerRegistrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Make sure that the given passwords match
                String password1 = ((EditText) findViewById(R.id.customerPassword1Field)).getText().toString();
                String password2 = ((EditText) findViewById(R.id.customerPassword2Field)).getText().toString();
                if (User.checkPasswords(password1, password2)) {
                    // Check that the password is at least 8 characters long
                    if (password1.length() >= 8) {
                        // Get the values from the fields
                        String username = ((EditText) findViewById(R.id.customerUsernameField)).getText().toString();
                        String email = ((EditText) findViewById(R.id.customerEmailField)).getText().toString();
                        String phone = ((EditText) findViewById(R.id.customerPhoneField)).getText().toString();
                        String address = ((EditText) findViewById(R.id.customerAddressField)).getText().toString();
                        String tk = ((EditText) findViewById(R.id.customertkField)).getText().toString();

                        // Create a new Customer instance
                        if (Customer.checkRegistrationValues(username, password1, phone, address, tk, email)) {
                            Customer customer = new Customer(username, password1, phone, address, Integer.parseInt(tk), email);
                            CustomerDAO.getInstance().save(customer);

                            // TODO: Redirect to another activity

                        } else {
                            // Display value error popup
                            Snackbar.make(findViewById(R.id.customerRegistrationView),
                                    R.string.wrong_values_given_error,
                                    Snackbar.LENGTH_SHORT).show();
                        }

                    } else {
                        // Display popup with error message
                        Snackbar.make(findViewById(R.id.customerRegistrationView),
                                R.string.password_length_error,
                                Snackbar.LENGTH_SHORT).show();
                    }
                } else {
                    // Display popup informing the user that the passwords do not match
                    Snackbar.make(findViewById(R.id.customerRegistrationView),
                            R.string.passwords_do_not_match_error,
                            Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }
}