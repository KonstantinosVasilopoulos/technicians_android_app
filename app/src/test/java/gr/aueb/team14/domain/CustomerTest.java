package gr.aueb.team14.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer("testName", "testing321",
                "St. john's 13", 12345, "test@example.com");
    }

    @Test
    void getTk() {
        assertEquals(12345, customer.getTk());
    }

    @Test
    void addAppointment() {
        Appointment appointment = new Appointment(new Date(2021, 4, 1),
                new Date(2021, 4, 2), 20.0);
        customer.addAppointment(appointment);
        assertSame(appointment, customer.getAppointments().get(0));
        assertEquals("testName", appointment.getCustomer().getUsername());
    }

    @Test
    void removeAppointment() {
        Appointment appointment = new Appointment(new Date(2021, 4, 1),
                new Date(2021, 4, 2), 20.0);
        customer.addAppointment(appointment);
        assertFalse(customer.getAppointments().isEmpty());
        customer.removeAppointment(appointment);
        assertTrue(customer.getAppointments().isEmpty());
        assertNull(appointment.getCustomer());
    }
}