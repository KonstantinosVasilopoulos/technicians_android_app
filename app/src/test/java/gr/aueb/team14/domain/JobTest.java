package gr.aueb.team14.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class JobTest {
    private Job job;

    @BeforeEach
    void setUp() {
        job = new Job("Wall painting", 30.0);
    }

    @Test
    void setTechnician() {
        Technician technician = new Technician("testName", "testing321");
        job.setTechnician(technician);
        assertSame(technician, job.getTechnician());
    }

    @Test
    void addAppointment() {
        Appointment appointment = new Appointment(new Date(2021, 6, 10),
                new Date(2021, 6, 13), job.getPrice());
        job.addAppointment(appointment);
        assertSame(appointment, job.getAppointments().get(0));
        assertSame(job, appointment.getJobs().get(0));
    }

    @Test
    void removeAppointment() {
        Appointment appointment = new Appointment(new Date(2021, 6, 10),
                new Date(2021, 6, 13), job.getPrice());
        job.addAppointment(appointment);
        assertEquals(1, job.getAppointments().size());
        job.removeAppointment(appointment);
        assertEquals(0, job.getAppointments().size());
        assertEquals(0, appointment.getJobs().size());
    }
}