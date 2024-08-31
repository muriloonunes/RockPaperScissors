package com.murile.jokenpo

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun selectRock(view: View) {
        checkWinner("rock")
    }

    fun selectPaper(view: View) {
        checkWinner("paper")
    }

    fun selectScissors(view: View) {
        checkWinner("scissors")
    }

    private fun generateAppChoice(): String {
        val randomNumber = (Math.random() * 3).toInt()
        val options = arrayOf("rock", "paper", "scissors")
        val appChoice = options[randomNumber]
        val textChoice = findViewById<TextView>(R.id.textChoice)
        val textApp = findViewById<TextView>(R.id.textApp)

        when (appChoice) {
            "rock" -> {
                textApp.setText(R.string.app_choice_rock)
                textChoice.setText(R.string.rock)
            }
            "paper" -> {
                textApp.setText(R.string.app_choice_paper)
                textChoice.setText(R.string.paper)
            }
            "scissors" -> {
                textApp.setText(R.string.app_choice_scissors)
                textChoice.setText(R.string.scissors)
            }
        }
        return appChoice
    }

    private fun getLocalizedChoice(choice: String): String {
        return when (choice) {
            "rock" -> getString(R.string.rock_text)
            "paper" -> getString(R.string.paper_text)
            "scissors" -> getString(R.string.scissors_text)
            else -> choice
        }
    }

    private fun checkWinner(userChoice: String) {
        val appChoice = generateAppChoice()
        val textResult = findViewById<TextView>(R.id.textResult)
        val textOverview = findViewById<TextView>(R.id.textOverview)

        val localizedUserChoice = getLocalizedChoice(userChoice)
        val localizedAppChoice = getLocalizedChoice(appChoice)

        textOverview.text = String.format(getString(R.string.game_result), localizedUserChoice, localizedAppChoice)

        when {
            (appChoice == "rock" && userChoice == "scissors") ||
                    (appChoice == "paper" && userChoice == "rock") ||
                    (appChoice == "scissors" && userChoice == "paper") -> {
                textResult.setText(R.string.you_lost)
            }
            (appChoice == "rock" && userChoice == "paper") ||
                    (appChoice == "paper" && userChoice == "scissors") ||
                    (appChoice == "scissors" && userChoice == "rock") -> {
                textResult.setText(R.string.you_won)
            }
            else -> {
                textResult.setText(R.string.draw)
            }
        }
    }
}