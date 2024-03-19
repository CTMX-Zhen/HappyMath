package my.edu.utar.p2_connie_tang_ming_xin_2106403;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Ascending_order_page extends AppCompatActivity {

    private TextView ascendingTextView;
    private TextView scoreTextView;
    private TextView timerTextView;
    private List<Integer> numbersList = new ArrayList<>();
    private int currentIndex = 0;
    private int score = 0;
    private int totalQuestions = 0;
    private int highestScore = 0;
    private CountDownTimer timer;
    private final int MAX_TIME_MILLIS = 30000; // 30 seconds
    private final int INTERVAL_MILLIS = 1000; // 1 second
    private List<Integer> selectedNumbers = new ArrayList<>();
    private Button Guideline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ascending_order_page);

        Guideline = findViewById(R.id.guideline);
        Guideline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ascending_order_page. this, Q2_guideline_page.class);
                startActivity(intent);
            }
        });

        ascendingTextView = findViewById(R.id.ascending_answer);
        scoreTextView = findViewById(R.id.d2_score_text);
        timerTextView = findViewById(R.id.d2_timer);

        // Find the CardViews
        CardView cardView1 = findViewById(R.id.d2_01);
        CardView cardView2 = findViewById(R.id.d2_02);
        CardView cardView3 = findViewById(R.id.d2_03);
        CardView cardView4 = findViewById(R.id.d2_04);
        CardView cardView5 = findViewById(R.id.d2_05);

        // Set click listeners for the CardViews
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleCardClick(1);
            }
        });
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleCardClick(2);
            }
        });
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleCardClick(3);
            }
        });
        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleCardClick(4);
            }
        });
        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleCardClick(5);
            }
        });

        startNewQuestion();
        startTimer();
    }

    private void handleCardClick(int cardNumber) {
        int selectedNumber = numbersList.get(cardNumber - 1); // Adjust index to match list
        selectedNumbers.add(selectedNumber);

        if (currentIndex == 0) {
            ascendingTextView.setText(String.valueOf(selectedNumber));
        } else {
            ascendingTextView.append(", " + selectedNumber);
        }

        currentIndex++;

        if (currentIndex == 5) {
            checkAscendingOrder();
        }
    }


    private void checkAscendingOrder() {
        boolean isAscendingOrder = true;

        for (int i = 1; i < selectedNumbers.size(); i++) {
            if (selectedNumbers.get(i) <= selectedNumbers.get(i - 1)) {
                isAscendingOrder = false;
                break;
            }
        }

        if (isAscendingOrder) {
            score++;
            Toast.makeText(this, "Correct! Score +1", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show();
        }

        totalQuestions++;
        updateScoreText();
        startNewQuestion();
        selectedNumbers.clear(); // Clear selected numbers for the next question
    }

    private void startNewQuestion() {
        currentIndex = 0; // Reset currentIndex
        ascendingTextView.setText(""); // Reset ascendingTextView

        numbersList.clear();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int randomNumber;
            do {
                randomNumber = random.nextInt(11); // Random number between 0 and 10
            } while (numbersList.contains(randomNumber)); // Check if the number is already in the list
            numbersList.add(randomNumber);
        }
        Collections.shuffle(numbersList); // Shuffle the numbers

        // Set numbers to the card views
        TextView card1TextView = findViewById(R.id.d2_a_01);
        TextView card2TextView = findViewById(R.id.d2_a_02);
        TextView card3TextView = findViewById(R.id.d2_a_03);
        TextView card4TextView = findViewById(R.id.d2_a_04);
        TextView card5TextView = findViewById(R.id.d2_a_05);

        card1TextView.setText(String.valueOf(numbersList.get(0)));
        card2TextView.setText(String.valueOf(numbersList.get(1)));
        card3TextView.setText(String.valueOf(numbersList.get(2)));
        card4TextView.setText(String.valueOf(numbersList.get(3)));
        card5TextView.setText(String.valueOf(numbersList.get(4)));
    }

    private void startTimer() {
        timer = new CountDownTimer(MAX_TIME_MILLIS, INTERVAL_MILLIS) {
            public void onTick(long millisUntilFinished) {
                timerTextView.setText((millisUntilFinished / 1000) + "s");
            }

            public void onFinish() {
                endTest();
            }
        }.start();
    }

    private void updateScoreText() {
        scoreTextView.setText("Score: " + score + "/" + totalQuestions);
    }

    private void endTest() {
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        int previousHighestAscendingScore = preferences.getInt("highestAscendingScore", 0);

        if (score > previousHighestAscendingScore) {
            // Update highest ascending score if current score is higher
            highestScore = score;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("highestAscendingScore", highestScore);
            editor.apply();
        }

        int correctAnswers = score;
        int incorrectAnswers = totalQuestions - correctAnswers;

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Test Finished");
        alertDialogBuilder.setMessage("Test Name: Ordering of Numbers - Ascending order\n" +
                "Highest Score Before: " + previousHighestAscendingScore + "\n" +
                "Score Just Now: " + score + "\n" +
                "Correct Answers: " + correctAnswers + "\n" +
                "Incorrect Answers: " + incorrectAnswers + "\n" +
                "Total Questions Answered: " + totalQuestions);
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                // Navigate back to the main menu
                Intent intent = new Intent(Ascending_order_page.this, Home_page.class);
                startActivity(intent);
                finish(); // Finish the current activity to prevent it from being returned to
            }
        });
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }
}
