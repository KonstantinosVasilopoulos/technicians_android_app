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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import gr.aueb.team14.R;
import gr.aueb.team14.domain.AvailableDate;

public class AvailableDateFragment extends Fragment {
    private EditText fromField;
    private EditText toField;

    public AvailableDateFragment() {
        // Required empty public constructor
    }

    public static AvailableDateFragment newInstance() {
        return new AvailableDateFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_available_date, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize the variables holding the user's input
        fromField = (EditText) getView().findViewById(R.id.fromField);
        toField = (EditText) getView().findViewById(R.id.toField);
    }

    public AvailableDate getAvailableDate() {
        if (fromField == null || toField == null)
            return null;

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("GR"));
            Date fromDate = dateFormat.parse(fromField.getText().toString());
            Date toDate = dateFormat.parse(toField.getText().toString());
            return new AvailableDate(fromDate, toDate);

        } catch (ParseException e) {
            return null;
        }
    }
}