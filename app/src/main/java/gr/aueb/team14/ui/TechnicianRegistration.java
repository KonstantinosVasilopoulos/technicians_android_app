package gr.aueb.team14.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import gr.aueb.team14.R;
import gr.aueb.team14.dao.TechnicianDAO;
import gr.aueb.team14.domain.AvailableDate;
import gr.aueb.team14.domain.CommunicationType;
import gr.aueb.team14.domain.Job;
import gr.aueb.team14.domain.Specialty;
import gr.aueb.team14.domain.Technician;
import gr.aueb.team14.domain.User;

public class TechnicianRegistration extends AppCompatActivity {
    private int addressFragmentId;
    private int dateFragmentId;
    private int jobFragmentId;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technician_registration);
        fm = getSupportFragmentManager();
    }

    @Override
    protected void onStart() {
        super.onStart();
        addressFragmentId = 1;
        dateFragmentId = 1;
        jobFragmentId = 1;

        // Add listener to the button responsible for handling the technician's addresses
        Button addAddressBtn = (Button) findViewById(R.id.addAddressBtn);
        addAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new fragment and add it to it's layout
                Fragment newFragment = new AddressesFragment();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.addressLayout, newFragment, "addressFragment" + addressFragmentId++);
                ft.commit();
            }
        });

        // Add listener to the button responsible for handling the technician's available dates
        Button addDateBtn = (Button) findViewById(R.id.addDateBtn);
        addDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new fragment and add it to it's layout
                Fragment newFragment = new AvailableDateFragment();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.availableDateLayout, newFragment, "dateFragment" + dateFragmentId++);
                ft.commit();
            }
        });

        // Add listener to the button responsible for handling the technician's jobs
        Button addJobBtn = (Button) findViewById(R.id.addJobBtn);
        addJobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new fragment and add it to it's layout
                Fragment newFragment = new JobsFragment();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.jobsLayout, newFragment, "jobFragment" + jobFragmentId++);
                ft.commit();
            }
        });

        // Add a listener to the button handling the technician's registration
        Button technicianRegistrationBtn = (Button) findViewById(R.id.technicianRegistrationBtn);
        technicianRegistrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the specialties and ensure that at least one specialty was chosen
                List<Specialty> specialties = getChosenSpecialties();
                if (specialties.isEmpty()) {  // Display a pop up informing the user about the error
                    Snackbar.make(findViewById(R.id.technicianregistrationView),
                            R.string.no_specialty_chosen_error,
                            Snackbar.LENGTH_SHORT).show();
                    return;
                }

                // Get the address from the fragments
                AddressesFragment addressesFragment;
                List<String> addresses = new ArrayList<>();
                String address;
                for (int i = 0; i < addressFragmentId; i++) {
                    addressesFragment = (AddressesFragment) fm.findFragmentByTag("addressFragment" + i);
                    address = addressesFragment.getAddress();
                    addresses.add(address);
                }

                // Make sure that at least one address was inputted
                if (addresses.isEmpty()) {
                    Snackbar.make(findViewById(R.id.technicianregistrationView),
                            R.string.no_address_given_error,
                            Snackbar.LENGTH_SHORT).show();
                    return;
                }

                // Get the available dates from their fragments
                AvailableDateFragment dateFragment;
                List<AvailableDate> availableDates = new ArrayList<>();
                AvailableDate date;
                for (int i = 0; i < dateFragmentId; i++) {
                    dateFragment = (AvailableDateFragment) fm.findFragmentByTag("dateFragment" + i);
                    date = dateFragment.getAvailableDate();
                    if (date != null)
                        availableDates.add(date);
                }

                // Make sure that at least one date was given by the user
                if (availableDates.isEmpty()) {
                    // Use a pop up to inform the user
                    Snackbar.make(findViewById(R.id.technicianregistrationView),
                            R.string.no_dates_given_error,
                            Snackbar.LENGTH_SHORT).show();
                    return;
                }

                // Get the jobs
                JobsFragment jobsFragment;
                List<Job> jobs = new ArrayList<>();
                Job job;
                for (int i = 0; i < jobFragmentId; i++) {
                    jobsFragment = (JobsFragment) fm.findFragmentByTag("jobFragment" + i);
                    job = jobsFragment.getJob();
                    if (job != null)
                        jobs.add(job);
                    else
                        Log.d("job", "job null");
                }

                // At least one job must be given
                if (jobs.isEmpty()) {
                    Snackbar.make(findViewById(R.id.technicianregistrationView),
                            R.string.no_job_given_error,
                            Snackbar.LENGTH_SHORT).show();
                    return;
                }

                // Get the rest of the data
                String username = ((EditText) findViewById(R.id.usernameField)).getText().toString();
                String password1 = ((EditText) findViewById(R.id.password1Field)).getText().toString();
                String password2 = ((EditText) findViewById(R.id.password2Field)).getText().toString();
                if (!User.checkPasswords(password1, password2)) {
                    Snackbar.make(findViewById(R.id.technicianregistrationView),
                            R.string.passwords_do_not_match_error,
                            Snackbar.LENGTH_SHORT).show();
                    return;
                } else {
                    // Check the length of the password
                    if (password1.length() < 8) {
                        Snackbar.make(findViewById(R.id.technicianregistrationView),
                                R.string.password_length_error,
                                Snackbar.LENGTH_SHORT).show();
                        return;
                    }
                }

                // Get the new technician's communication method
                CommunicationTypeFragment communicationTypeFragment = (CommunicationTypeFragment) fm.findFragmentById(R.id.communicationTypeFragment);
                CommunicationType type = communicationTypeFragment.getCommunicationType();
                String value = communicationTypeFragment.getValue();

                // Register the new technician
                Technician technician = new Technician(username, password1);
                technician.setSpecialties(specialties);
                technician.setAddresses(addresses);
                technician.setAvailableDates(availableDates);
                technician.setCommunicationType(type);
                technician.setCommunicationValue(value);
                technician.setJobs(jobs);
                TechnicianDAO.getInstance().save(technician);

                // Notify the user and go to the technician's profile view
                Snackbar.make(findViewById(R.id.technicianregistrationView),
                        R.string.successful_technician_creation,
                        Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private boolean checkBoxChecked(int viewId) {
        return ((CheckBox) findViewById(viewId)).isChecked();
    }

    private List<Specialty> getChosenSpecialties() {
        List<Specialty> chosenSpecialties = new ArrayList<>();
        if (checkBoxChecked(R.id.electricianBox))
            chosenSpecialties.add(Specialty.Electrician);

        if (checkBoxChecked(R.id.plumberBox))
            chosenSpecialties.add(Specialty.Plumber);

        if (checkBoxChecked(R.id.gardenerBox))
            chosenSpecialties.add(Specialty.Gardener);

        if (checkBoxChecked(R.id.locksmithBox))
            chosenSpecialties.add(Specialty.Locksmith);

        if (checkBoxChecked(R.id.plakatzisBox))
            chosenSpecialties.add(Specialty.Plakatzis);

        if (checkBoxChecked(R.id.plastererBox))
            chosenSpecialties.add(Specialty.Plasterer);

        if (checkBoxChecked(R.id.engineerBox))
            chosenSpecialties.add(Specialty.Engineer);

        if (checkBoxChecked(R.id.builderBox))
            chosenSpecialties.add(Specialty.Builder);

        if (checkBoxChecked(R.id.carpenterBox))
            chosenSpecialties.add(Specialty.Carpenter);

        return chosenSpecialties;
    }
}