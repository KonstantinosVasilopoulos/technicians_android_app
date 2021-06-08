package gr.aueb.team14.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import gr.aueb.team14.R
import gr.aueb.team14.dao.CustomerDAO
import gr.aueb.team14.dao.TechnicianDAO
import gr.aueb.team14.domain.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create some technicians and customers for debugging
        var technician = Technician("Antonis Kati", "testing321")
        technician.addSpecialty(Specialty.Builder);
        technician.addAvailableDate(AvailableDate(Date(2021, 10, 13), Date(2021, 10, 13)))
        technician.addAddress("Marathonos 12")
        technician.addAddress(("Apollonos 9"))
        technician.addJob(Job("Wall repair", 50.0))
        TechnicianDAO.getInstance().save(technician)
        technician = Technician("Petros Surname", "code4321")
        technician.addSpecialty(Specialty.Locksmith)
        technician.addSpecialty(Specialty.Electrician)
        technician.addAvailableDate(AvailableDate(Date(2021, 1, 10), Date(2021, 10, 20)))
        technician.addAvailableDate(AvailableDate(Date(2021, 12, 1), Date(2021, 12, 17)))
        technician.addAddress("Spyrou Loui 23")
        technician.addJob(Job("Lock repair", 15.5))
        technician.addJob(Job("A/C repair", 24.99))
        technician.addJob(Job("Change lamps", 10.0))
        TechnicianDAO.getInstance().save(technician)
        var customer = Customer("John", "mypassword", "6912345678", "Veikou 5", 12345, "john@example.com")
        CustomerDAO.getInstance().save(customer)
        customer = Customer("Mark", "testing321", "2107654321", "Address St. 11", 12346, "mark@example.gr")
        CustomerDAO.getInstance().save(customer)
    }

    override fun onStart() {
        super.onStart()

        // Add listener to login button
        val loginBtn: Button = findViewById(R.id.loginBtn)
        loginBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

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