package gr.aueb.team14.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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
    }
}