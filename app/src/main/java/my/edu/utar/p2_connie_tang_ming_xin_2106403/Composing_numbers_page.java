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

public class Composing_numbers_page extends AppCompatActivity {

    private TextView composingTextView;
    private TextView scoreTextView;
    private TextView timerTextView;
    private int num1, num2, num3;
    private int score = 0;
    private int totalQuestions = 0;
    private int highestScore = 0;
    private int clickCount = 0;
    private CountDownTimer timer;
    private final int MAX_TIME_MILLIS = 30000; // 30 seconds
    private final int INTERVAL_MILLIS = 1000; // 1 second
    private Button Guideline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composing_numbers_page);

        Guideline = findViewById(R.id.guideline);
        Guideline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Composing_numbers_page. this, Q4_guideline_page.class);
                startActivity(intent);
            }
        });

        composingTextView = findViewById(R.id.composing_numbers_questions);
        scoreTextView = findViewById(R.id.d3_score_text);
        timerTextView = findViewById(R.id.d3_timer);

        // Find the CardViews
        CardView cardView1 = findViewById(R.id.d3_01);
        CardView cardView2 = findViewById(R.id.d3_02);
        CardView cardView3 = findViewById(R.id.d3_03);
        CardView cardView4 = findViewById(R.id.d3_04);
        CardView cardView5 = findViewById(R.id.d3_05);

        // Set OnClickListener for each CardView
        findViewById(R.id.d3_01).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (clickCount) {
                    case 0:
                        num1 = Integer.parseInt(((TextView) findViewById(R.id.d3_a_01)).getText().toString());
                        update(); // Update the question text immediately after selecting num1
                        clickCount++;
                        break;
                    case 1:
                        num2 = Integer.parseInt(((TextView) findViewById(R.id.d3_a_01)).getText().toString());
                        update(); // Update the question text immediately after selecting num2
                        clickCount = 0; // Reset click count for next question
                        new android.os.Handler().postDelayed(
                                new Runnable() {
                                    public void run() {
                                        checkAnswer(); // Check the answer after a delay
                                    }
                                },
                                500); // Delay of 0.5 seconds
                        break;
                }
            }
        });

        findViewById(R.id.d3_02).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (clickCount) {
                    case 0:
                        num1 = Integer.parseInt(((TextView) findViewById(R.id.d3_a_02)).getText().toString());
                        update(); // Update the question text immediately after selecting num1
                        clickCount++;
                        break;
                    case 1:
                        num2 = Integer.parseInt(((TextView) findViewById(R.id.d3_a_02)).getText().toString());
                        update(); // Update the question text immediately after selecting num2
                        clickCount = 0; // Reset click count for next question
                        new android.os.Handler().postDelayed(
                                new Runnable() {
                                    public void run() {
                                        checkAnswer(); // Check the answer after a delay
                                    }
                                },
                                500); // Delay of 0.5 seconds
                        break;
                }
            }
        });

        findViewById(R.id.d3_03).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (clickCount) {
                    case 0:
                        num1 = Integer.parseInt(((TextView) findViewById(R.id.d3_a_03)).getText().toString());
                        update(); // Update the question text immediately after selecting num1
                        clickCount++;
                        break;
                    case 1:
                        num2 = Integer.parseInt(((TextView) findViewById(R.id.d3_a_03)).getText().toString());
                        update(); // Update the question text immediately after selecting num2
                        clickCount = 0; // Reset click count for next question
                        new android.os.Handler().postDelayed(
                                new Runnable() {
                                    public void run() {
                                        checkAnswer(); // Check the answer after a delay
                                    }
                                },
                                500); // Delay of 0.5 seconds
                        break;
                }
            }
        });

        findViewById(R.id.d3_04).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (clickCount) {
                    case 0:
                        num1 = Integer.parseInt(((TextView) findViewById(R.id.d3_a_04)).getText().toString());
                        update(); // Update the question text immediately after selecting num1
                        clickCount++;
                        break;
                    case 1:
                        num2 = Integer.parseInt(((TextView) findViewById(R.id.d3_a_04)).getText().toString());
                        update(); // Update the question text immediately after selecting num2
                        clickCount = 0; // Reset click count for next question
                        new android.os.Handler().postDelayed(
                                new Runnable() {
                                    public void run() {
                                        checkAnswer(); // Check the answer after a delay
                                    }
                                },
                                500); // Delay of 0.5 seconds
                        break;
                }
            }
        });

        findViewById(R.id.d3_05).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (clickCount) {
                    case 0:
                        num1 = Integer.parseInt(((TextView) findViewById(R.id.d3_a_05)).getText().toString());
                        update(); // Update the question text immediately after selecting num1
                        clickCount++;
                        break;
                    case 1:
                        num2 = Integer.parseInt(((TextView) findViewById(R.id.d3_a_05)).getText().toString());
                        update(); // Update the question text immediately after selecting num2
                        clickCount = 0; // Reset click count for next question
                        new android.os.Handler().postDelayed(
                                new Runnable() {
                                    public void run() {
                                        checkAnswer(); // Check the answer after a delay
                                    }
                                },
                                500); // Delay of 0.5 seconds
                        break;
                }
            }
        });

        startNewQuestion();
        startTimer();
    }

    private void startNewQuestion() {
        // Reset num1 and num2
        num1 = -1;
        num2 = -1;

        List<Integer> numbersList = new ArrayList<>(); // List to store generated numbers
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            int randomNumber;
            do {
                randomNumber = random.nextInt(11); // Random number between 0 and 10
            } while (numbersList.contains(randomNumber)); // Check if the number is already in the list
            numbersList.add(randomNumber);
        }
        Collections.shuffle(numbersList); // Shuffle the numbers

        // Generate random num3
        do {
            num3 = random.nextInt(11); // Random number between 0 and 10
        } while (!hasValidPair(numbersList, num3)); // Check if there is at least one pair that can be added to equal num3

        // Set numbers to the card views
        TextView card1TextView = findViewById(R.id.d3_a_01);
        TextView card2TextView = findViewById(R.id.d3_a_02);
        TextView card3TextView = findViewById(R.id.d3_a_03);
        TextView card4TextView = findViewById(R.id.d3_a_04);
        TextView card5TextView = findViewById(R.id.d3_a_05);

        card1TextView.setText(String.valueOf(numbersList.get(0)));
        card2TextView.setText(String.valueOf(numbersList.get(1)));
        card3TextView.setText(String.valueOf(numbersList.get(2)));
        card4TextView.setText(String.valueOf(numbersList.get(3)));
        card5TextView.setText(String.valueOf(numbersList.get(4)));

        composingTextView.setText("(? + ? = " + num3 + ")"); // Reset question text
    }

    // Method to check if there is at least one pair in the numbersList that can be added to equal targetSum
    private boolean hasValidPair(List<Integer> numbersList, int targetSum) {
        for (int i = 0; i < numbersList.size() - 1; i++) {
            for (int j = i + 1; j < numbersList.size(); j++) {
                if (numbersList.get(i) + numbersList.get(j) == targetSum) {
                    return true;
                }
            }
        }
        return false;
    }

    // Update the question text based on selected num1, num2, and generated num3
    private void update() {
        if (num1 != -1 && num2 != -1) { // Check if both num1 and num2 are selected
            composingTextView.setText(num1 + " + " + num2 + " = " + num3); // Update question text
        } else if (num1 != -1) {
            String question = composingTextView.getText().toString();
            question = question.replaceFirst("\\?", String.valueOf(num1)); // Replace first "?" with num1
            composingTextView.setText(question);
        } else if (num2 != -1) {
            String question = composingTextView.getText().toString();
            question = question.replaceFirst("\\?", String.valueOf(num2)); // Replace second "?" with num2
            composingTextView.setText(question);
        }
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

    public void checkAnswer() {
        if (num1 + num2 == num3) {
            score++;
            Toast.makeText(this, "Correct! Score +1", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show();
            startNewQuestion();
        }

        totalQuestions++;
        updateScoreText();
        startNewQuestion();
    }

    private void updateScoreText() {
        scoreTextView.setText("Score: " + score + "/" + totalQuestions);
    }

    private void endTest() {
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        int previousHighestComposingNumberScore = preferences.getInt("highestComposingScore", 0);

        if (score > previousHighestComposingNumberScore) {
            // Update highest ascending score if current score is higher
            highestScore = score;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("highestComposingScore", highestScore);
            editor.apply();
        }

        int correctAnswers = score;
        int incorrectAnswers = totalQuestions - correctAnswers;

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Test Finished");
        alertDialogBuilder.setMessage("Test Name: Ordering of Numbers - Ascending order\n" +
                "Highest Score Before: " + previousHighestComposingNumberScore + "\n" +
                "Score Just Now: " + score + "\n" +
                "Correct Answers: " + correctAnswers + "\n" +
                "Incorrect Answers: " + incorrectAnswers + "\n" +
                "Total Questions Answered: " + totalQuestions);
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                // Navigate back to the main menu
                Intent intent = new Intent(Composing_numbers_page.this, Home_page.class);
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
