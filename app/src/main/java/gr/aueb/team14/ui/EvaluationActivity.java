package gr.aueb.team14.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

import gr.aueb.team14.R;
import gr.aueb.team14.dao.AppointmentDAO;
import gr.aueb.team14.domain.Appointment;

public class EvaluationActivity extends AppCompatActivity {
    Appointment mAppointment;
    RatingBar mEvaluationBar;
    Button mCompleteEvaluationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation);

        // Find the appointment using it's ID
        long id = getIntent().getLongExtra("APPOINTMENT_ID", 0);
        if (id == 0) {
            // Redirect to the previous view if the ID is 0
            Intent intent = new Intent(this, CustomerAppointmentsActivity.class);
            startActivity(intent);
        }
        mAppointment = AppointmentDAO.getInstance().find(id);

        // Set a default value for the rating bar if the appointment has been reviewed before
        mEvaluationBar = findViewById(R.id.evaluationBar);
        mEvaluationBar.setRating(mAppointment.getReviewScore());
        mCompleteEvaluationBtn = findViewById(R.id.completeEvaluationBtn);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Add a listener to the button responsible for completing the evaluation
        mCompleteEvaluationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the value of the rating bar and set it as the review score for the appointment
                float barValue = mEvaluationBar.getRating();
                mAppointment.setReviewScore((int) barValue);

                // Switch to the view holding all the appointments
                Intent intent = new Intent(v.getContext(), CustomerAppointmentsActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }
}