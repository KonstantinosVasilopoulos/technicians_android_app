package gr.aueb.team14.domain;

import android.annotation.SuppressLint;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AvailableDate{
    private Date from;
    private Date to;
    private boolean booked;
    private Technician technician;

    public AvailableDate(Date from, Date to) {
        this.booked = false;
        this.from = from;
        this.to = to;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getFrom() {
        return from;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public Date getTo() {
        return to;
    }

    public Technician getTechnician() {
        return technician;
    }

    public void setTechnician(Technician technician) {
        this.technician = technician;
    }

    @NotNull
    public String toString() {
        @SuppressLint("SimpleDateFormat")
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yy");
        return dateFormat.format(from) + " μέχρι " + dateFormat.format(to);
    }
}