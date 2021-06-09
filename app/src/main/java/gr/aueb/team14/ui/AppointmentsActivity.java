package gr.aueb.team14.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import gr.aueb.team14.LoggedInUser;
import gr.aueb.team14.R;
import gr.aueb.team14.dao.AppointmentDAO;
import gr.aueb.team14.domain.Appointment;
import gr.aueb.team14.domain.Technician;

public class AppointmentsActivity extends AppCompatActivity {
    Technician mTechnician;
    LinearLayout mPendingAppointmentsSection;
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        mTechnician = (Technician) LoggedInUser.getInstance().getUser();
        fm = getSupportFragmentManager();

        // Setup the appointments
        mPendingAppointmentsSection = findViewById(R.id.pendingAppointmentsSection);
        if (mTechnician == null) return;
        for (Appointment appointment : AppointmentDAO.getInstance().getAppointmentsForTechnician(mTechnician.getUsername())) {
            // Pending appointments
            if (!appointment.isConfirmed()) {
                // Create an entry for appointment approval
                Fragment newFragment = new AppointmentApprovalFragment();
                Bundle args = new Bundle();
                args.putLong("APPOINTMENT_ID", appointment.getId());
                newFragment.setArguments(args);
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.pendingAppointmentsSection, newFragment, "appointmentApprovalEntry");
                ft.commit();

            } else if (!appointment.isCompleted()) {
                // Create an entry for an uncompleted appointment
                Fragment newFragment = new AppointmentCompletionFragment();
                Bundle args = new Bundle();
                args.putLong("APPOINTMENT_ID", appointment.getId());
                newFragment.setArguments(args);
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.uncompletedAppointmentsSection, newFragment, "appointmentCompletionEntry");
                ft.commit();
            }
        }
    }
}