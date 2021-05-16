package gr.aueb.team14.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TechnicianTest {
    private Technician technician;

    @BeforeEach
    void setUp() {
        technician = new Technician("testName", "testing321");
    }

    @Test
    void setSpecialties() {
        List<Specialty> newSpecialties = new ArrayList<>();
        newSpecialties.add(Specialty.Builder);
        newSpecialties.add(Specialty.Electrician);
        newSpecialties.add(Specialty.Locksmith);
        technician.setSpecialties(newSpecialties);
        assertEquals(3, technician.getSpecialties().size());
    }

    @Test
    void addSpecialty() {
        technician.addSpecialty(Specialty.Builder);
        assertSame(Specialty.Builder, technician.getSpecialties().get(0));
        technician.addSpecialty(Specialty.Builder);
        assertEquals(1, technician.getSpecialties().size());
    }

    @Test
    void addAvailableDate() {
        AvailableDate date = new AvailableDate(new Date(2021, 4, 1),
                new Date(2021, 4, 2));
        technician.addAvailableDate(date);
        assertSame(date, technician.getAvailableDates().get(0));
        date = new AvailableDate(new Date(2021, 5, 2),
                new Date(2021, 5, 3));
        technician.addAvailableDate(date);
        assertEquals(2, technician.getAvailableDates().size());
    }

    @Test
    void removeAvailableDate() {
        AvailableDate date = new AvailableDate(new Date(2021, 4, 1),
                new Date(2021, 4, 2));
        technician.addAvailableDate(date);
        technician.removeAvailableDate(date);
        assertTrue(technician.getAvailableDates().isEmpty());
    }

    @Test
    void addJob() {
        Job job = new Job("Plumbing", 15.5);
        technician.addJob(job);
        assertSame(job, technician.getJobs().get(0));
        technician.addJob(job);
        assertEquals(1, technician.getJobs().size());
    }
}