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

public class EvaluationEntryFragment extends Fragment {
    Appointment mAppointment;
    TextView mEvaluationEntryLabel;
    Button mEvaluationEntryBtn;

    private static final String ARG_PARAM1 = "APPOINTMENT_ID";

    public EvaluationEntryFragment() {
        // Required empty public constructor
    }

    public static EvaluationEntryFragment newInstance(long appointmentId) {
        EvaluationEntryFragment fragment = new EvaluationEntryFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_PARAM1, appointmentId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // Find the appointment
            long id = getArguments().getLong(ARG_PARAM1);
            mAppointment = AppointmentDAO.getInstance().find(id);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_evaluation_entry, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Change the text of the label
        mEvaluationEntryLabel = view.findViewById(R.id.evaluationEntryLabel);
        mEvaluationEntryLabel.setText(mAppointment.toString());

        // Add a listener to the button redirecting to the activity responsible for
        // completing the evaluation
        mEvaluationEntryBtn = view.findViewById(R.id.evaluationEntryBtn);
        mEvaluationEntryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EvaluationActivity.class);
                intent.putExtra(ARG_PARAM1, mAppointment.getId());
                v.getContext().startActivity(intent);
            }
        });
    }
}