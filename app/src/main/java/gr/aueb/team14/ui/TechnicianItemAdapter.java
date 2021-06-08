package gr.aueb.team14.ui;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import gr.aueb.team14.R;
import gr.aueb.team14.domain.Technician;

public class TechnicianItemAdapter extends RecyclerView.Adapter<TechnicianItemAdapter.TechnicianItemViewHolder> {
    private final List<Technician> technicians;
    
    public static class TechnicianItemViewHolder extends RecyclerView.ViewHolder {
        public TechnicianItemViewHolder(View view) {
            super(view);
        }
    }

    public TechnicianItemAdapter(List<Technician> technicians) {
        this.technicians = technicians;
    }

    @NonNull
    @NotNull
    @Override
    public TechnicianItemViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.technician_item, parent, false);
        return new TechnicianItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TechnicianItemViewHolder holder, int position) {
        // Set the technician's username as the label
        ((TextView) holder.itemView.findViewById(R.id.technicianItemNameLabel)).setText(technicians.get(position).getUsername());

        // Add listener to the view's button redirecting to the activity handling reservations
        Button technicianItemInfoBtn = (Button) holder.itemView.findViewById(R.id.technicianItemInfoBtn);
        technicianItemInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to the activity which handles new reservations
                Intent intent = new Intent(v.getContext(), BookReservationActivity.class);
                intent.putExtra("TECHNICIAN_USERNAME", technicians.get(position).getUsername());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return technicians.size();
    }
}