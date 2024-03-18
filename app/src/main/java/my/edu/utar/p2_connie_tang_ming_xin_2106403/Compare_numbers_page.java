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

import java.util.Random;

public class Compare_numbers_page extends AppCompatActivity {

    private TextView questionTextView;
    private TextView scoreTextView;
    private TextView timerTextView;
    private int score = 0;
    private int totalQuestions = 0;
    private int highestScore = 0;
    private CountDownTimer timer;
    private final int MAX_TIME_MILLIS = 30000; // 30 seconds
    private final int INTERVAL_MILLIS = 1000; // 1 second
    private Button Guideline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_numbers_page);

        Guideline = findViewById(R.id.guideline);
        Guideline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Compare_numbers_page. this, Q1_guideline_page.class);
                startActivity(intent);
            }
        });

        questionTextView = findViewById(R.id.compare_numbers_questions);
        scoreTextView = findViewById(R.id.d1_score_text);
        timerTextView = findViewById(R.id.d1_timer);

        // Find the CardViews for greater than and less than
        CardView greaterThanCardView = findViewById(R.id.d1_greaterthan);
        CardView lessThanCardView = findViewById(R.id.d1_lessthan);

        // Set click listeners for the CardViews
        greaterThanCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleAnswer(true); // True indicates user clicked on greater than
            }
        });

        lessThanCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleAnswer(false); // False indicates user clicked on less than
            }
        });

        startNewQuestion();
        startTimer();
    }

    private void handleAnswer(boolean isGreaterThan) {
        // Increment total question after user has chosen an answer
        totalQuestions++;
        // Split the question text to retrieve num1 and num2
        String[] parts = questionTextView.getText().toString().split("\\s+");
        int num1 = Integer.parseInt(parts[0]);
        int num2 = Integer.parseInt(parts[2]);

        // Check if num1 is greater than num2
        if ((isGreaterThan && num1 > num2) || (!isGreaterThan && num1 < num2)) {
            // If the user's selection is correct
            score++;
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show();
        }

        startNewQuestion();
    }

    private void startNewQuestion() {
        Random random = new Random();
        int num1 = random.nextInt(11); // Random number between 0 and 10
        int num2 = random.nextInt(11);

        // Repeat until num1 is not equal to num2
        while (num1 == num2) {
            num2 = random.nextInt(11);
        }

        questionTextView.setText(num1 + " (___) " + num2);
        updateScoreText();
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
        int previousHighestCompareNumberScore = preferences.getInt("highestCompareScore", 0);

        if (score > previousHighestCompareNumberScore) {
            // Update highest score if current score is higher
            highestScore = score;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("highestCompareScore", highestScore);
            editor.apply();
        }

        int correctAnswers = score;
        int incorrectAnswers = totalQuestions - correctAnswers;

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Test Finished");
        alertDialogBuilder.setMessage("Test Name: Compare Numbers\n" +
                "Highest Score Before: " + previousHighestCompareNumberScore + "\n" +
                "Score Just Now: " + score + "\n" +
                "Correct Answers: " + correctAnswers + "\n" +
                "Incorrect Answers: " + incorrectAnswers + "\n" +
                "Total Questions Answered: " + totalQuestions);
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                // Navigate back to the main menu
                Intent intent = new Intent(Compare_numbers_page.this, Home_page.class);
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

