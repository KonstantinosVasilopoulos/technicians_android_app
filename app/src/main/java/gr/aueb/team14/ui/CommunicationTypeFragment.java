package gr.aueb.team14.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import org.jetbrains.annotations.NotNull;

import gr.aueb.team14.R;
import gr.aueb.team14.domain.CommunicationType;

public class CommunicationTypeFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private Spinner typeField;
    private EditText valueField;

    public CommunicationTypeFragment() {
        // Required empty public constructor
    }

    public static CommunicationTypeFragment newInstance(String param1, String param2) {
        return new CommunicationTypeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_communication_type, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Add options to the spinner
        typeField = (Spinner) getView().findViewById(R.id.TypeSpinner);
        typeField.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.communication_type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeField.setAdapter(adapter);

        valueField = (EditText) getView().findViewById(R.id.ValueField);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getItemAtPosition(position).equals("Email"))
            valueField.setHint(R.string.email_label);
        else
            valueField.setHint(R.string.phone_hint);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        valueField.setHint(R.string.communication_value_hint);
    }

    public CommunicationType getCommunicationType() {
        switch (typeField.getSelectedItem().toString()) {
            case "Email":
                return CommunicationType.Email;

            case "Phone":
                return CommunicationType.Phone;

            case "SMS":
                return CommunicationType.SMS;

            default:
                return null;
        }
    }

    public String getValue() {
        return valueField.getText().toString();
    }
}