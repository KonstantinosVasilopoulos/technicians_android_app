package gr.aueb.team14.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import gr.aueb.team14.LoggedInUser;
import gr.aueb.team14.R;
import gr.aueb.team14.domain.Customer;

public class LoginActivity extends AppCompatActivity {
    EditText usernameField;
    EditText passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void onStart() {
        super.onStart();

        usernameField = (EditText) findViewById(R.id.usernameField);
        passwordField = (EditText) findViewById(R.id.passwordField);

        // Add a listener to the login button
        Button loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the given username and password
                String username = usernameField.getText().toString();
                String password = passwordField.getText().toString();

                boolean success = LoggedInUser.getInstance().login(username, password);
                if (success) {
                    if (LoggedInUser.getInstance().getUser() instanceof Customer) {
                        // Send the user to the activity containing all technicians
                        Intent intent = new Intent(LoginActivity.this, CustomerAppointmentsActivity.class);
                        startActivity(intent);
                    } else {
                        // Redirect the use to his appointments
                        Intent intent = new Intent(v.getContext(), AppointmentsActivity.class);
                        v.getContext().startActivity(intent);
                    }
                } else {
                    // Show error popup
                    Snackbar.make(findViewById(R.id.loginView),
                            R.string.login_error,
                            Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }
}