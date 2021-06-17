package gr.aueb.team14.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import gr.aueb.team14.R
import gr.aueb.team14.dao.AppointmentDAO
import gr.aueb.team14.dao.CustomerDAO
import gr.aueb.team14.dao.TechnicianDAO
import gr.aueb.team14.domain.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create some technicians and customers for debugging
        var technician = Technician("Antonis Vassilopoulos", "testing321")
        technician.addSpecialty(Specialty.Builder);
        technician.addAvailableDate(AvailableDate(Date(2021, 10, 13), Date(2021, 10, 13)))
        technician.addAddress("Marathonos 12")
        technician.addAddress(("Apollonos 9"))
        technician.addJob(Job("Wall repair", 50.0))
        TechnicianDAO.getInstance().save(technician)
        technician = Technician("Petros Syntakas", "code4321")
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
        val appointment = Appointment(technician.availableDates[0].from, technician.availableDates[0].to, technician.jobs[0].price + technician.jobs[2].price)
        appointment.addJob(technician.jobs[0])
        appointment.addJob(technician.jobs[2])
        appointment.isConfirmed = true
        technician.availableDates[0].isBooked = true
        customer.addAppointment(appointment)
        AppointmentDAO.getInstance().save(appointment)
        val appointment2 = Appointment(technician.availableDates[1].from, technician.availableDates[0].to, technician.jobs[0].price)
        appointment2.addJob(technician.jobs[0])
        appointment2.isConfirmed = true
        appointment2.isCompleted = true
        appointment2.payment.isCompleted = true
        customer.addAppointment(appointment2)
        AppointmentDAO.getInstance().save(appointment2)
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