package my.edu.utar.p2_connie_tang_ming_xin_2106403;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Ordering_of_numbers_page extends AppCompatActivity implements View.OnClickListener{

    private CardView cardView1, cardView2;
    private  Class<?> lastVisitedPage = null;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering_of_numbers_page);

        cardView1 = (CardView) findViewById(R.id.d2_ascending);
        cardView2 = (CardView) findViewById(R.id.d2_descending);

        cardView1.setOnClickListener((View.OnClickListener) this);
        cardView2.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        if (v.getId() == R.id.d2_ascending) {
            intent = new Intent(this, Ascending_order_page.class);
            startActivity(intent);
        } else if (v.getId() == R.id.d2_descending) {
            intent = new Intent(this, Descending_order_page.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        // Check if the last visited page is not null and it's the Ordering_of_numbers_page
        if (lastVisitedPage != null && lastVisitedPage.equals(Ordering_of_numbers_page.class)) {
            // If it is the Ordering_of_numbers_page, navigate back to the Home_page
            Intent intent = new Intent(this, Home_page.class);
            startActivity(intent);
        } else {
            // If the last visited page is null or it's not the Ordering_of_numbers_page, perform default back press behavior
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