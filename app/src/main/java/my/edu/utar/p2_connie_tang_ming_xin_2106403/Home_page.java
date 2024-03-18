package my.edu.utar.p2_connie_tang_ming_xin_2106403;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home_page extends AppCompatActivity implements View.OnClickListener{

    private CardView cardView1, cardView2, cardView3, cardView4;
    private Button Guideline;

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
}