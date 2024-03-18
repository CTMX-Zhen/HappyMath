package my.edu.utar.p2_connie_tang_ming_xin_2106403;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class Q1_guideline_page extends AppCompatActivity {

    private Button Understood;
    private  Class<?> lastVisitedPage = null;
    private VideoView Q1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1_guideline_page);

        Understood = findViewById(R.id.understood);
        Understood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        // Check if the last visited page is not null and it's the Compare_numbers_page
        if (lastVisitedPage != null && lastVisitedPage.equals(Compare_numbers_page.class)) {
            // If it is the Compare_numbers_page, navigate back to the Compare_numbers_page
            Intent intent = new Intent(this, Compare_numbers_page.class);
            startActivity(intent);
        } else {
            // If the last visited page is null or it's not the Compare_numbers_page, perform default back press behavior
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Update the last visited page whenever this activity is resumed
        lastVisitedPage = getClass();
    }
}