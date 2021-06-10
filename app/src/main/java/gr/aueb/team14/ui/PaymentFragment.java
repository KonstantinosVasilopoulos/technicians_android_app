package gr.aueb.team14.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import gr.aueb.team14.R;
import gr.aueb.team14.dao.AppointmentDAO;
import gr.aueb.team14.domain.Appointment;
import gr.aueb.team14.domain.Job;
import gr.aueb.team14.domain.Payment;

public class PaymentFragment extends Fragment {
    private Payment mPayment;

    private static final String ARG_PARAM1 = "APPOINTMENT_ID";

    public PaymentFragment() {
        // Required empty public constructor
    }

    public static PaymentFragment newInstance(long appointmentId) {
        PaymentFragment fragment = new PaymentFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_PARAM1, appointmentId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // Get the appointment
            long id = getArguments().getLong(ARG_PARAM1);
            Appointment appointment = AppointmentDAO.getInstance().find(id);
            mPayment = appointment.getPayment();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Change the labels' text
        ((TextView) view.findViewById(R.id.appointmentLabel)).setText(mPayment.getAppointment().toString());
        ((TextView) view.findViewById(R.id.amountCell)).setText(String.valueOf(mPayment.getAmount()));

        // Display all jobs and their cost individually
        for (Job job : mPayment.getAppointment().getJobs()) {
            // Create a horizontal layout
            LinearLayout jobLayout = new LinearLayout(view.getContext());
            jobLayout.setOrientation(LinearLayout.HORIZONTAL);

            // Add a label with the job's name to the left
            TextView jobNameLabel = new TextView(view.getContext());
            jobNameLabel.setTextSize(20);
            jobNameLabel.setText(job.getName());
            jobNameLabel.setPadding(dpToPixels(30), dpToPixels(20), dpToPixels(30), 0);
            jobLayout.addView(jobNameLabel);

            // Add a label displaying the job's price
            TextView jobPriceLabel = new TextView(view.getContext());
            jobPriceLabel.setTextSize(20);
            jobPriceLabel.setText(job.getPrice() + " €");
            jobPriceLabel.setPadding(dpToPixels(30), dpToPixels(20), dpToPixels(30), 0);
            jobLayout.addView(jobPriceLabel);

            // Add the jobs' layout to the fragment's view
            ((LinearLayout) view.findViewById(R.id.paymentJobsLayout)).addView(jobLayout);

            // Add a listener to the button which completes the payment
            Button paymentBtn = view.findViewById(R.id.paymentBtn);
            paymentBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPayment.setCompleted(true);

                    // Reload the customer's appointment activity
                    Intent intent = new Intent(v.getContext(), CustomerAppointmentsActivity.class);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    private int dpToPixels(int dpSize) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dpSize * scale + 0.5f);
    }
}