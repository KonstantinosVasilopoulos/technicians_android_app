package gr.aueb.team14.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import gr.aueb.team14.R;
import gr.aueb.team14.dao.TechnicianDAO;
import gr.aueb.team14.domain.Technician;

public class TechnicianList extends AppCompatActivity {
    RecyclerView technicianRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technician_list);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Create the recycler view for the technicians
        List<Technician> technicians = TechnicianDAO.getInstance().getSomeTechnicians();
        TechnicianItemAdapter technicianItemAdapter = new TechnicianItemAdapter(technicians);
        technicianRecyclerView = (RecyclerView) findViewById(R.id.technicianRecycleView);
        technicianRecyclerView.setAdapter(technicianItemAdapter);
        technicianRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Add a listener to the button redirecting to the previous view
        Button backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CustomerAppointmentsActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }
}