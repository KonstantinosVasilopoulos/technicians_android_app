package gr.aueb.team14.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import gr.aueb.team14.LoggedInUser;
import gr.aueb.team14.R;
import gr.aueb.team14.dao.AppointmentDAO;
import gr.aueb.team14.dao.TechnicianDAO;
import gr.aueb.team14.domain.Appointment;
import gr.aueb.team14.domain.AvailableDate;
import gr.aueb.team14.domain.Customer;
import gr.aueb.team14.domain.Job;
import gr.aueb.team14.domain.Specialty;
import gr.aueb.team14.domain.Technician;

public class BookReservationActivity extends AppCompatActivity {
    Technician mTechnician;
    LinearLayout mSpecialtiesSection;
    LinearLayout mJobsSection;
    // The view below is actually the dateRadioGroup
    // But who cares, nobody is going to read this anyways
    LinearLayout mChooseDateSection;
    int mJobTagCount;
    int mDateTagCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_reservation);

        mJobTagCount = 0;
        mDateTagCount = 0;

        // Find the requested technician
        String technicianUsername = getIntent().getStringExtra("TECHNICIAN_USERNAME");
        mTechnician = TechnicianDAO.getInstance().find(technicianUsername);
        ((TextView) findViewById(R.id.technicianUsernameTitle)).setText(technicianUsername);

        // Setup the specialties
        mSpecialtiesSection = findViewById(R.id.specialtiesSection);
        for (Specialty specialty : mTechnician.getSpecialties())
            putNewTextViewToLayout(specialty.toString(), mSpecialtiesSection);

        // Setup the jobs
        mJobsSection = findViewById(R.id.jobsSection);
        for (Job job : mTechnician.getJobs())
            putNewJobToLayout(job);

        // Setup the dates radio group
        mChooseDateSection = findViewById(R.id.dateRadioGroup);
        for (AvailableDate date : mTechnician.getAvailableDates()) {
            if (!date.isBooked())
                putNewDateToLayout(date);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Add a listener to the button for going back
        Button backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TechnicianList.class);
                v.getContext().startActivity(intent);
            }
        });

        // Add listener to the button which handles the reservation creation
        Button createReservationBtn = findViewById(R.id.createReservationBtn);
        createReservationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the job's the user chose
                List<Job> chosenJobs = new ArrayList<>();
                CheckBox jobCheckBox;
                for (int i = 1; i <= mJobTagCount; i++) {
                    jobCheckBox = (CheckBox) mJobsSection.getChildAt(i);
                    if (jobCheckBox.isChecked())
                        chosenJobs.add(mTechnician.getJobs().get(i - 1));
                }

                // Ensure that at least one job was chosen
                if (chosenJobs.isEmpty()) {
                    Snackbar.make(findViewById(R.id.bookReservationView),
                            R.string.no_job_chosen_error,
                            Snackbar.LENGTH_SHORT).show();
                    return;
                }

                // Get the date the user chose
                AvailableDate chosenDate = null;
                RadioButton dateBtn;
                for (int i = 0; i < mDateTagCount; i++) {
                    dateBtn = (RadioButton) mChooseDateSection.getChildAt(i);
                    if (dateBtn.isChecked()) {
                        chosenDate = mTechnician.getAvailableDates().get(i);
                        break;
                    }
                }

                // Exit if the user hasn't chosen a date
                if (chosenDate == null) {
                    Snackbar.make(findViewById(R.id.bookReservationView),
                            R.string.no_chosen_date_error,
                            Snackbar.LENGTH_SHORT).show();
                    return;
                }

                // Create a new appointment
                double price = 0.0;
                for (Job job : chosenJobs)
                    price += job.getPrice();
                Appointment appointment = new Appointment(chosenDate.getFrom(), chosenDate.getTo(), price);
                chosenDate.setBooked(true);
                appointment.setCustomer((Customer) LoggedInUser.getInstance().getUser());

                // Connect the new appointment to the chosen jobs
                for (Job job : chosenJobs)
                    appointment.addJob(job);

                // Save the new appointment
                AppointmentDAO.getInstance().save(appointment);
                Snackbar.make(findViewById(R.id.bookReservationView),
                        R.string.appointment_created,
                        Snackbar.LENGTH_SHORT).show();

                // Go back to the activity displaying all the appointments of the customer
                Intent intent = new Intent(v.getContext(), CustomerAppointmentsActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    private void putNewTextViewToLayout(String specialtyString, LinearLayout layout) {
        TextView specialtyTxt = new TextView(this);
        specialtyTxt.setText(specialtyString);
        specialtyTxt.setTextSize(16);
        specialtyTxt.setPadding(50, 15, 30, 0);
        layout.addView(specialtyTxt);
    }

    private void putNewJobToLayout(Job job) {
        CheckBox jobCheckBox = new CheckBox(this);
        jobCheckBox.setText(job.toString());
        jobCheckBox.setPadding(50, 15, 30, 0);
        jobCheckBox.setTag("jobCheckBox");
        mJobTagCount++;
        mJobsSection.addView(jobCheckBox);
    }

    private void putNewDateToLayout(AvailableDate date) {
        RadioButton dateBtn = new RadioButton(this);
        dateBtn.setText(date.toString());
        dateBtn.setTag("dateBtn");
        mDateTagCount++;
        mChooseDateSection.addView(dateBtn);
    }
}