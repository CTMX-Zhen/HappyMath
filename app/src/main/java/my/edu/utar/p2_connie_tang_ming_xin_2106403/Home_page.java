package my.edu.utar.p2_connie_tang_ming_xin_2106403;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Home_page extends AppCompatActivity implements View.OnClickListener{

    private CardView cardView1, cardView2, cardView3, cardView4;
    private Button Guideline;
    private Class<?> lastVisitedPage;
    private int backPressCount = 0;
    private static final long DOUBLE_BACK_PRESS_INTERVAL = 2000; // 2 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        cardView1 = (CardView) findViewById(R.id.d1);
        cardView2 = (CardView) findViewById(R.id.d2);
        cardView3 = (CardView) findViewById(R.id.d3);
        cardView4 = (CardView) findViewById(R.id.d4);

        cardView1.setOnClickListener((View.OnClickListener) this);
        cardView2.setOnClickListener((View.OnClickListener) this);
        cardView3.setOnClickListener((View.OnClickListener) this);
        cardView4.setOnClickListener((View.OnClickListener) this);

        Guideline = findViewById(R.id.guideline);
        Guideline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_page. this, Guideline_page.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        if (v.getId() == R.id.d1) {
            intent = new Intent(this, Compare_numbers_page.class);
            startActivity(intent);
        } else if (v.getId() == R.id.d2) {
            intent = new Intent(this, Ordering_of_numbers_page.class);
            startActivity(intent);
        } else if (v.getId() == R.id.d3) {
            intent = new Intent(this, Composing_numbers_page.class);
            startActivity(intent);
        } else if (v.getId() == R.id.d4){
            intent = new Intent(this, Scores_page.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        // Check if the last visited page is not null and it's the Home_page
        if (lastVisitedPage != null && lastVisitedPage.equals(Home_page.class)) {
            // Increment back press count
            backPressCount++;

            if (backPressCount == 1) {
                Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
                // Start a timer to reset back press count if the second back press doesn't occur within DOUBLE_BACK_PRESS_INTERVAL
                new Handler().postDelayed(() -> backPressCount = 0, DOUBLE_BACK_PRESS_INTERVAL);
            } else if (backPressCount >= 2) {
                // If it is the Home_page and back pressed twice, exit the app
                finishAffinity(); // This method will kill the app
            }
        } else {
            // If the last visited page is null or it's not the Home_page, perform default back press behavior
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