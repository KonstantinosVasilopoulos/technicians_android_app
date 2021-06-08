package gr.aueb.team14.domain;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.ArrayList;

public class Job{
    private String name;
    private double price;
    private Technician technician;
    private List<Appointment> appointments;

    public Job(String name,double price) {
        this.name = name;
        this.price = price;
        this.appointments = new ArrayList<>();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double job) {
        this.price = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Technician getTechnician() {
        return technician;
    }

    public void setTechnician(Technician technician) {
        this.technician = technician;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void addAppointment(Appointment appointment) {
        if(!appointments.contains(appointment)){
            appointments.add(appointment);
            appointment.addJob(this);
        }
    }

    public void removeAppointment(Appointment appointment) {
        if (appointments.contains(appointment)) {
            appointments.remove(appointment);
            appointment.removeJob(this);
        }
    }

    @NotNull
    public String toString() {
        return name + " " + price + "€";
    }
}