package my.edu.utar.p2_connie_tang_ming_xin_2106403;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Scores_page extends AppCompatActivity {

    private static final String PREF_NAME = "ScoresPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores_page);

        // Display highest scores
        displayHighestScores();

        // Set up click listener for the CLEAR button
        Button clearButton = findViewById(R.id.clear);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call clearAllScores when the CLEAR button is clicked
                clearAllScores();
            }
        });
    }

    // Function to find the highest score from each feature
    private int[] getHighestScores() {
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        // Get the highest score from each feature
        int previousHighestCompareNumberScore = preferences.getInt("highestCompareScore", 0);
        int previousHighestAscendingScore = preferences.getInt("highestAscendingScore", 0);
        int previousHighestDescendingScore = preferences.getInt("highestDescendingScore", 0);
        int previousHighestComposingNumberScore = preferences.getInt("highestComposingScore", 0);

        // Return an array containing the highest scores
        return new int[] { previousHighestCompareNumberScore, previousHighestAscendingScore, previousHighestDescendingScore, previousHighestComposingNumberScore };
    }

    // Function to display the highest scores
    private void displayHighestScores() {
        int[] highestScores = getHighestScores();

        TextView compareNumbersTextView = findViewById(R.id.d4_a_01);
        compareNumbersTextView.setText("Compare Numbers's highest score = " + highestScores[0]);

        TextView ascendingOrderTextView = findViewById(R.id.d4_a_02);
        ascendingOrderTextView.setText("Ordering of Numbers - Ascending order's highest score = " + highestScores[1]);

        TextView descendingOrderTextView = findViewById(R.id.d4_a_03);
        descendingOrderTextView.setText("Ordering of Numbers - Descending order's highest score = " + highestScores[2]);

        TextView composingNumbersTextView = findViewById(R.id.d4_a_04);
        composingNumbersTextView.setText("Composing Numbers's highest score = " + highestScores[3]);
    }


    // Function to clear all score records with confirmation dialog
    public void clearAllScores() {
        new AlertDialog.Builder(this)
                .setTitle("Clear Scores")
                .setMessage("Are you sure you want to clear all scores records?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putInt("highestCompareScore", 0);
                        editor.putInt("highestAscendingScore", 0);
                        editor.putInt("highestDescendingScore", 0);
                        editor.putInt("highestComposingScore", 0);
                        editor.apply();

                        Toast.makeText(Scores_page.this, "All scores cleared", Toast.LENGTH_SHORT).show();
                        // After clearing, update displayed scores
                        displayHighestScores();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
