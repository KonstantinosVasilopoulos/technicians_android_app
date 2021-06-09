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

public class AppointmentCompletionFragment extends Fragment {
    Appointment appointment;

    private static final String ARG_PARAM1 = "APPOINTMENT_ID";

    public AppointmentCompletionFragment() {
        // Required empty public constructor
    }

    public static AppointmentCompletionFragment newInstance(long appointmentId) {
        AppointmentCompletionFragment fragment = new AppointmentCompletionFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_PARAM1, appointmentId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // Find the relevant appointment
            long id = getArguments().getLong(ARG_PARAM1);
            appointment = AppointmentDAO.getInstance().find(id);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_appointment_completion, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Change the text of the label
        ((TextView) view.findViewById(R.id.uncompletedAppointmentLabel)).setText(appointment.toString());

        // Get the button and add a listener for completing appointments
        Button completeAppointmentBtn = view.findViewById(R.id.completeAppointmentBtn);
        completeAppointmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Complete the appointment
                appointment.setCompleted(true);

                // Redirect to the activity containing all reservation
                Intent intent = new Intent(v.getContext(), AppointmentsActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }
}