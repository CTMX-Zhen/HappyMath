package my.edu.utar.p2_connie_tang_ming_xin_2106403;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Guideline_page extends AppCompatActivity implements View.OnClickListener{

    private TextView Q1, Q2, Q3, Q4;
    private Class<?> lastVisitedPage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guideline_page);

        Q1 = findViewById(R.id.Q_a_01);
        Q2 = findViewById(R.id.Q_a_02);
        Q3 = findViewById(R.id.Q_a_03);
        Q4 = findViewById(R.id.Q_a_04);

        Q1.setOnClickListener((View.OnClickListener) this);
        Q2.setOnClickListener((View.OnClickListener) this);
        Q3.setOnClickListener((View.OnClickListener) this);
        Q4.setOnClickListener((View.OnClickListener) this);
    }
    @Override
    public void onClick(View v) {
        Intent intent;
        if (v.getId() == R.id.Q_a_01) {
            intent = new Intent(this, Q1_guideline_page.class);
            startActivity(intent);
        } else if (v.getId() == R.id.Q_a_02) {
            intent = new Intent(this, Q2_guideline_page.class);
            startActivity(intent);
        } else if (v.getId() == R.id.Q_a_03) {
            intent = new Intent(this, Q3_guideline_page.class);
            startActivity(intent);
        } else if (v.getId() == R.id.Q_a_04){
            intent = new Intent(this, Q4_guideline_page.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        // Check if the last visited page is not null and it's not the home page
        if (lastVisitedPage != null && !lastVisitedPage.equals(Home_page.class)) {
            // If it is not the home page, navigate back to the home page
            Intent intent = new Intent(this, Home_page.class);
            startActivity(intent);
        } else {
            // If the last visited page is null or it's the home page, perform default back press behavior
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