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
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import gr.aueb.team14.R;
import gr.aueb.team14.dao.AppointmentDAO;
import gr.aueb.team14.domain.Appointment;

public class AppointmentApprovalFragment extends Fragment {
    Button confirmBtn;
    Button dismissBtn;
    Appointment appointment;

    private static final String ARG_PARAM = "APPOINTMENT_ID";

    public AppointmentApprovalFragment() {
        // Required empty public constructor
    }

    public static AppointmentApprovalFragment newInstance(long appointmentId) {
        AppointmentApprovalFragment fragment = new AppointmentApprovalFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_PARAM, appointmentId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // Find the appointment
            long id = getArguments().getLong(ARG_PARAM);
            appointment = AppointmentDAO.getInstance().find(id);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_appointment_approval, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Change the text of the label
        ((TextView) view.findViewById(R.id.appointmentApprovalLabel)).setText(appointment.toString());

        // Find the two buttons
        confirmBtn = view.findViewById(R.id.confirmBtn);
        dismissBtn = view.findViewById(R.id.dismissBtn);

        // Add listeners to the buttons
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Confirm the appointment
                appointment.setConfirmed(true);

                // Notify the customer with an email
                appointment.getCustomer().sendConfirmationEmail(appointment);

                // Reload the activity
                Intent intent = new Intent(view.getContext(), AppointmentsActivity.class);
                view.getContext().startActivity(intent);
            }
        });

        dismissBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dismiss the appointment
                appointment.setConfirmed(false);

                // Send an email to the customer
                appointment.getCustomer().sendDismissalEmail(appointment);

                // Reload the activity
                Intent intent = new Intent(view.getContext(), AppointmentsActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }
}