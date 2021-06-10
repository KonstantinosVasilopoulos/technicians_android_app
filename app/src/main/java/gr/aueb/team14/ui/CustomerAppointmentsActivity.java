package gr.aueb.team14.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import gr.aueb.team14.LoggedInUser;
import gr.aueb.team14.R;
import gr.aueb.team14.dao.AppointmentDAO;
import gr.aueb.team14.domain.Appointment;
import gr.aueb.team14.domain.Customer;

public class CustomerAppointmentsActivity extends AppCompatActivity {
    LinearLayout mPendingAppointmentsLayout;
    LinearLayout mConfirmedAppointmentsLayout;
    LinearLayout mEvaluationLayout;
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_appointments);

        mPendingAppointmentsLayout = findViewById(R.id.pendingAppointmentsLayout);
        mConfirmedAppointmentsLayout = findViewById(R.id.confirmedAppointmentsLayout);
        mEvaluationLayout = findViewById(R.id.evaluationLayout);
        fm = getSupportFragmentManager();

        // Add entries for the appointments
        Customer customer = (Customer) LoggedInUser.getInstance().getUser();
        for (Appointment appointment : AppointmentDAO.getInstance().getAppointmentsForCustomer(customer.getUsername())) {
            if (!appointment.isConfirmed()) {
                TextView newAppointment = new TextView(this);
                newAppointment.setTextSize(20);
                newAppointment.setText(appointment.toString());
                newAppointment.setPadding(dpToPixels(25), dpToPixels(20), dpToPixels(25), 0);
                mPendingAppointmentsLayout.addView(newAppointment);
            } else if (!appointment.getPayment().isCompleted()) {
                // Add a new payment fragment for unpaid and confirmed appointments
                FragmentTransaction ft = fm.beginTransaction();
                Fragment newFragment = new PaymentFragment();
                Bundle args = new Bundle();
                args.putLong("APPOINTMENT_ID", appointment.getId());
                newFragment.setArguments(args);
                ft.add(R.id.confirmedAppointmentsLayout, newFragment);
                ft.commit();
            }

            // Show the user appointments that can be evaluated
            if (appointment.isCompleted()) {
                FragmentTransaction ft = fm.beginTransaction();
                Fragment newFragment = new EvaluationEntryFragment();
                Bundle args = new Bundle();
                args.putLong("APPOINTMENT_ID", appointment.getId());
                newFragment.setArguments(args);
                ft.add(R.id.evaluationLayout, newFragment);
                ft.commit();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Add a listener to the button which redirects to the technician's list activity
        Button addAppointmentBtn = findViewById(R.id.addAppointmentBtn);
        addAppointmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TechnicianList.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    private int dpToPixels(int dpSize) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dpSize * scale + 0.5f);
    }
}