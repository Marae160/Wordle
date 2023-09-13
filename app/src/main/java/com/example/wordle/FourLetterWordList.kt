package com.example.wordle
object FourLetterWordList {
    private val words = listOf(
        "WORD", "GAME", "CODE", "JAVA", "LEGO","GIRL","FOUR", // Add more words as needed
        // ...
    )

    fun getRandomFourLetterWord(): String {
        return words.random()
    }
}



