package org.example.snakeladder

import org.junit.jupiter.api.Test

class SnakeLadderTest{
    @Test
    fun `Snake ladder works`(){
        val snakeLadder = SnakeLadder(names = listOf("Vikash", "Amit", "Sanu"))
        snakeLadder.play()
    }

    @Test
    fun `Multiple Snake ladder game works`(){
        val gameManager = GameManager.getInstance()
        gameManager.addGame(names = listOf("Vikash", "Amit", "Sanu"))
        gameManager.addGame(names = listOf("Tom", "John", "Harry"))
    }
}