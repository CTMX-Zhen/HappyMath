package my.edu.utar.p2_connie_tang_ming_xin_2106403;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Q4_guideline_page extends AppCompatActivity {

    private Button Understood;
    private  Class<?> lastVisitedPage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q4_guideline_page);

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
        // Check if the last visited page is not null and it's the Composing_numbers_page
        if (lastVisitedPage != null && lastVisitedPage.equals(Composing_numbers_page.class)) {
            // If it is the Composing_numbers_page, navigate back to the Composing_numbers_page
            Intent intent = new Intent(this, Composing_numbers_page.class);
            startActivity(intent);
        } else {
            // If the last visited page is null or it's not the Composing_numbers_page perform default back press behavior
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