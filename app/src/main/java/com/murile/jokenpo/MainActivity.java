package com.murile.jokenpo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void selectRock(View view) {
        checkWinner("rock");
    }

    public void selectPaper(View view) {
        checkWinner("paper");

    }

    public void selectScissors(View view) {
        checkWinner("scissors");
    }

    private String generateAppChoice() {
        int randomN = (int) (Math.random() * 3);
        String[] options = {"rock", "paper", "scissors"};
        String appChoice = options[randomN];
        TextView textChoice = findViewById(R.id.textChoice);
        switch (appChoice) {
            case "rock":
                textChoice.setText(R.string.rock);
                break;
            case "paper":
                textChoice.setText(R.string.paper);
                break;
            case "scissors":
                textChoice.setText(R.string.scissors);
                break;
        }
        return appChoice;
    }

    private String getLocalizedChoice(String choice) {
        switch (choice) {
            case "rock":
                return getString(R.string.rock_text);
            case "paper":
                return getString(R.string.paper_text);
            case "scissors":
                return getString(R.string.scissors_text);
            default:
                return "choice";
        }
    }

    private void checkWinner(String userChoice) {
        String appChoice = generateAppChoice();
        TextView textResult = findViewById(R.id.textResult);
        TextView textOverview = findViewById(R.id.textOverview);

        String localizedUserChoice = getLocalizedChoice(userChoice);
        String localizedAppChoice = getLocalizedChoice(appChoice);

        textOverview.setText(String.format(getString(R.string.game_result), localizedUserChoice, localizedAppChoice));

        if ((appChoice.equals("rock") && userChoice.equals("scissors"))
                || (appChoice.equals("paper") && userChoice.equals("rock"))
                || (appChoice.equals("scissors") && userChoice.equals("paper"))) {
            textResult.setText(R.string.you_lost);
        } else if ((appChoice.equals("rock") && userChoice.equals("paper"))
                || (appChoice.equals("paper") && userChoice.equals("scissors"))
                || (appChoice.equals("scissors") && userChoice.equals("rock"))) {
            textResult.setText(R.string.you_won);
        } else {
            textResult.setText(R.string.draw);
        }

    }
}