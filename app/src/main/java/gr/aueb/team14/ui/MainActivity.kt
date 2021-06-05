package gr.aueb.team14.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import gr.aueb.team14.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        // TODO: Add listener to login button

        // Add listener to customer registration button
        val registerCustomerBtn: Button = findViewById(R.id.registerCutomerBtn)
        registerCustomerBtn.setOnClickListener {
            val intent = Intent(this, CustomerRegistration::class.java)
            startActivity(intent)
        }

        // Add listener to technician registration button
        val technicianRegistrationBtn: Button = findViewById(R.id.registerTechnicianBtn)
        technicianRegistrationBtn.setOnClickListener {
            val intent = Intent(this, TechnicianRegistration::class.java)
            startActivity(intent)
        }
    }
}