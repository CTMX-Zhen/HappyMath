package my.edu.utar.p2_connie_tang_ming_xin_2106403;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Q2_guideline_page extends AppCompatActivity {

    private Button Understood;
    private Class<?> lastVisitedPage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q2_guideline_page);

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
        // Check if the last visited page is not null and it's the Ascending_order_page
        if (lastVisitedPage != null && lastVisitedPage.equals(Ascending_order_page.class)) {
            // If it is the Ascending_order_page, navigate back to the Ascending_order_page
            Intent intent = new Intent(this, Ascending_order_page.class);
            startActivity(intent);
        } else {
            // If the last visited page is null or it's not the Ascending_order_page, perform default back press behavior
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