package gr.aueb.team14.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentTest {
    private Appointment appointment;

    @BeforeEach
    void setUp() {
        appointment = new Appointment(new Date(2020, 5, 9),
                new Date(2020, 5, 10), 50.6);
    }

    @Test
    void completedInitAsFalse() {
        assertFalse(appointment.isCompleted());
    }

    @Test
    void confirmedInitAsFalse() {
        assertFalse(appointment.isConfirmed());
    }

    @Test
    void setPayment() {
        // The method setPayment is already called inside the constructor
        // of the Appointment class. So there is no need to call it again.
        assertEquals(50.6, appointment.getPayment().getAmount());
    }

    @Test
    void setCustomer() {
        Customer customer = new Customer("testName", "testing321",
                "6912345678", "St. john's 13", 12345, "test@example.com");
        appointment.setCustomer(customer);
        assertSame(customer, appointment.getCustomer());
    }

    @Test
    void addJob() {
        Job job = new Job("Window Repair", 10.0);
        appointment.addJob(job);
        assertTrue(appointment.getJobs().contains(job));
        assertTrue(job.getAppointments().contains(appointment));
    }

    @Test
    void removeJob() {
        Job job = new Job("Window Repair", 10.0);
        appointment.addJob(job);
        assertFalse(appointment.getJobs().isEmpty());
        appointment.removeJob(job);
        assertTrue(appointment.getJobs().isEmpty());
    }
}