package com.example.rock

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
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

        val rockButton = findViewById<Button>(R.id.rockButton)
        val paperButton = findViewById<Button>(R.id.paperButton)
        val scissorsButton = findViewById<Button>(R.id.scissorsButton)
        val playerChoiceImageView = findViewById<ImageView>(R.id.playerChoiceImageView)
        val computerChoiceImageView = findViewById<ImageView>(R.id.computerChoiceImageView)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)

        rockButton.setOnClickListener {
            playGame("rock", playerChoiceImageView, computerChoiceImageView, resultTextView)
        }

        paperButton.setOnClickListener {
            playGame("paper", playerChoiceImageView, computerChoiceImageView, resultTextView)
        }

        scissorsButton.setOnClickListener {
            playGame("scissors", playerChoiceImageView, computerChoiceImageView, resultTextView)
        }
    }

    private fun playGame(playerChoice: String, playerChoiceImageView: ImageView, computerChoiceImageView: ImageView, resultTextView: TextView) {
        val computerChoice = listOf("rock", "paper", "scissors").random()

        // Set images based on choices
        playerChoiceImageView.setImageResource(getImageResource(playerChoice))
        computerChoiceImageView.setImageResource(getImageResource(computerChoice))

        // Determine the winner
        when {
            playerChoice == computerChoice -> resultTextView.text = "It's a Draw!"
            (playerChoice == "rock" && computerChoice == "scissors") ||
                    (playerChoice == "paper" && computerChoice == "rock") ||
                    (playerChoice == "scissors" && computerChoice == "paper") -> resultTextView.text = "You Win!"
            else -> resultTextView.text = "You Lose!"
        }
    }

    private fun getImageResource(choice: String): Int {
        return when (choice) {
            "rock" -> R.drawable.rock
            "paper" -> R.drawable.paper
            "scissors" -> R.drawable.scissors
            else -> R.drawable.question_mark
        }
    }
}