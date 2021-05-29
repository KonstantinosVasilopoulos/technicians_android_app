package gr.aueb.team14.dao;

import java.util.ArrayList;
import java.util.List;

import gr.aueb.team14.domain.Technician;

public class TechnicianDAO {
    private TechnicianDAO instance = null;

    private List<Technician> technicians;

    private TechnicianDAO() {
        technicians = new ArrayList<>();
    }

    public TechnicianDAO getInstance() {
        if (instance == null)
            instance = new TechnicianDAO();

        return instance;
    }

    public Technician find(String username) {
        for (Technician technician : technicians) {
            if (username.equals(technician.getUsername())) {
                return technician;
            }
        }
        return null;
    }

    public void save(Technician technician) {
        if (!technicians.contains(technician))
            technicians.add(technician);
    }

    public void delete(Technician technician) {
        technicians.remove(technician);
    }
}