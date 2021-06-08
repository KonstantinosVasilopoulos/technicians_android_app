package gr.aueb.team14.dao;

import java.util.ArrayList;
import java.util.List;

import gr.aueb.team14.domain.Appointment;

public class AppointmentDAO {
    private static AppointmentDAO instance = null;

    private final List<Appointment> appointments;

    private AppointmentDAO() {
        appointments = new ArrayList<>();
    }

    public static AppointmentDAO getInstance() {
        if (instance == null)
            instance = new AppointmentDAO();

        return instance;
    }

    public Appointment find(long id) {
        for (Appointment appointment : appointments) {
            if (appointment.getId() == id)
                return appointment;
        }

        return null;
    }

    public List<Appointment> getAppointmentsForTechnician(String username) {
        List<Appointment> technicianAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            // Use the first job of the appointment to find it's technician
            if (username.equals(appointment.getJobs().get(0).getTechnician().getUsername()))
                technicianAppointments.add(appointment);
        }

        return technicianAppointments;
    }

    public List<Appointment> getAppointmentsForCustomer(String username) {
        List<Appointment> customerAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (username.equals(appointment.getCustomer().getUsername()))
                customerAppointments.add(appointment);
        }

        return customerAppointments;
    }

    public void save(Appointment appointment) {
        if (!appointments.contains(appointment))
            appointments.add(appointment);
    }

    public void delete(Appointment appointment) {
        appointments.remove(appointment);
    }
}
