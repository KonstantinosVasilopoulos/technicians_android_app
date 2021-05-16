package gr.aueb.team14.domain;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Appointment{
    private Date from;
    private Date to;
    private boolean confirmed;
    private boolean completed;
    private int reviewScore;
    private Payment payment;
    private Customer customer;
    private List<Job> jobs;

    public Appointment(Date from, Date to,double amount) {
        this.from = from;
        this.to = to;
        this.completed = false;
        this.confirmed = false;
        setPayment(new Payment(amount));
        this.jobs = new ArrayList<>();
    }

    public Appointment(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Date getFrom() { return from;}

    public void setFrom(Date from) {
        this.from = from;
    }

    public int getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(int reviewScore) {
        this.reviewScore = reviewScore;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
        this.payment.setAppointment(this);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void addJob(Job job) {
        if(!jobs.contains(job)){
            jobs.add(job);
            job.addAppointment(this);
        }
    }

    public void removeJob(Job job) {
        if(jobs.contains(job)){
            jobs.remove(job);
            job.removeAppointment(this);
        }
    }
}