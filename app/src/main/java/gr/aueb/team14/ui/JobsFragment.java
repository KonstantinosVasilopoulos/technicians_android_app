package gr.aueb.team14.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.jetbrains.annotations.NotNull;

import gr.aueb.team14.R;
import gr.aueb.team14.domain.Job;


public class JobsFragment extends Fragment {
    private EditText nameField;
    private EditText priceField;

    public JobsFragment() {
        // Required empty public constructor
    }

    public static JobsFragment newInstance(String param1, String param2) {
        return new JobsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jobs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nameField = (EditText) getView().findViewById(R.id.jobNameField);
        priceField = (EditText) getView().findViewById(R.id.jobPriceField);
    }

    public Job getJob() {
        String name = nameField.getText().toString();
        double price;
        try {
            price = Double.parseDouble(priceField.getText().toString());
        } catch (NumberFormatException e) {
            return null;
        }

        return new Job(name, price);
    }
}