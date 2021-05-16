package gr.aueb.team14.domain;

import java.util.List;
import java.util.ArrayList;

public class Customer extends User{
    private String email;
    private String address;
    private int tk;
    private List<Appointment> appointments;

    public Customer(String username, String password, String address, int tk, String email) {
        super(username, password);
        this.address = address;
        this.tk = tk;
        this.email = email;
        this.appointments = new ArrayList<>();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTk() {
        return tk;
    }

    public void setTk(int tk) {
        this.tk = tk;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void addAppointment(Appointment appointment) {
        if(!appointments.contains(appointment)){
            appointments.add(appointment);
            appointment.setCustomer(this);
        }
    }

    public void removeAppointment(Appointment appointment) {
        if (appointments.contains(appointment)) {
            appointments.remove(appointment);
            appointment.setCustomer(null);
        }
    }
}