package gr.aueb.team14.domain;

import java.util.ArrayList;
import java.util.List;

public class Technician extends User{
    private List<String> addresses;
    private List<Specialty> specialties;
    private CommunicationType communicationType;
    private String communicationValue;
    private List<AvailableDate> availableDates;
    private List<Job> jobs;

    public Technician(String username, String password) {
        super(username, password);
        this.addresses = new ArrayList<>();
        this.specialties = new ArrayList<>();
        this.jobs = new ArrayList<>();
        this.availableDates = new ArrayList<>();
    }

    public List<String> getAddresses() {
        return new ArrayList<>(addresses);
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }

    public void addAddress(String address) {
        if (!addresses.contains(address))
            addresses.add(address);
    }

    public void removeAddress(String address) {
        addresses.remove(address);
    }

    public CommunicationType getCommunicationType() {
        return communicationType;
    }

    public void setCommunicationType(CommunicationType communicationType) {
        this.communicationType = communicationType;
    }

    public String getCommunicationValue() {
        return communicationValue;
    }

    public void setCommunicationValue(String communicationValue) {
        this.communicationValue = communicationValue;
    }

    public void setSpecialties(List<Specialty> specialties) {
        for (Specialty specialty : specialties)
            addSpecialty(specialty);
    }

    public List<Specialty> getSpecialties() {
        return specialties;
    }

    public void addSpecialty(Specialty specialty) {
        if (!specialties.contains(specialty))
            specialties.add(specialty);
    }

    public void removeSpecialty(List<Specialty> special) {
        specialties.remove(special);
    }

    public List<AvailableDate> getAvailableDates() {
        return availableDates;
    }

    public void addAvailableDate(AvailableDate date){
        if(!availableDates.contains(date)){
            availableDates.add(date);
            date.setTechnician(this);
        }
    }
    public void removeAvailableDate(AvailableDate date){
        if(availableDates.contains(date)){
            availableDates.remove(date);
            date.setTechnician(null);
        }
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void addJob(Job job) {
        if(!jobs.contains(job)){
            jobs.add(job);
            job.setTechnician(this);
        }
    }

    public void removeJob(Job job) {
        if(jobs.contains(job)){
            jobs.remove(job);
            job.setTechnician(null);
        }
    }
}
