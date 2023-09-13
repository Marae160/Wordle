package com.example.wordle

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextGuess: EditText
    private lateinit var submitButton: Button
    private lateinit var restartButton: Button
    private lateinit var feedbackTextView: TextView
    private lateinit var targetWordTextView: TextView
    private lateinit var targetWord: String
    private var remainingAttempts = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextGuess = findViewById(R.id.editTextGuess)
        submitButton = findViewById(R.id.submitButton)
        restartButton = findViewById(R.id.restartButton)
        feedbackTextView = findViewById(R.id.feedbackTextView)
        targetWordTextView = findViewById(R.id.targetWordTextView)

        restartGame()

        submitButton.setOnClickListener {
            if (remainingAttempts > 0) {
                val userGuess = editTextGuess.text.toString().toUpperCase()
                val feedback = generateFeedback(userGuess)
                feedbackTextView.text = "Guess ${4 - remainingAttempts}:\n$feedback"

                remainingAttempts--

                if (remainingAttempts == 0) {
                    targetWordTextView.visibility = View.VISIBLE
                    submitButton.isEnabled = false
                    restartButton.visibility = View.VISIBLE
                }
            }
        }

        restartButton.setOnClickListener {
            restartGame()
        }
    }

    private fun restartGame() {
        targetWord = FourLetterWordList.getRandomFourLetterWord()
        editTextGuess.text.clear()
        remainingAttempts = 3
        feedbackTextView.text = ""
        targetWordTextView.visibility = View.GONE
        submitButton.isEnabled = true
        restartButton.visibility = View.GONE
    }

    private fun generateFeedback(userGuess: String): String {
        val feedback = StringBuilder()

        for (i in userGuess.indices) {
            val guessChar = userGuess[i]
            val targetChar = targetWord.getOrElse(i) { ' ' } // Handle out-of-bounds

            if (guessChar == targetChar) {
                feedback.append('O')
            } else if (targetWord.contains(guessChar)) {
                feedback.append('+')
            } else {
                feedback.append('X')
            }
        }

        return feedback.toString()
    }
}



