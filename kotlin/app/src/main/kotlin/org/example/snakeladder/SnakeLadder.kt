package org.example.snakeladder

import kotlin.coroutines.CoroutineContext


/*
Feature,
play(noOfPlayers)
rollDice()

Dice -> rollDice()
Board -> snakes, ladders, move(x,y,z)
Player -> x,y
Game -> Game(noOfPlayers), players, boards, play()
 */

class GameManager{
    private val snakeLadders : MutableList<SnakeLadder> = mutableListOf()

    fun addGame(names: List<String>){
        val snakeLadder = SnakeLadder(names)
        snakeLadders.add(snakeLadder)
        Thread{
            snakeLadder.play()
        }.start()
    }

    companion object{
        private val gameManager: GameManager = GameManager()
        fun getInstance(): GameManager{
            return gameManager
        }
    }
}

class SnakeLadder(names: List<String>) {
    private val players: MutableList<Player> = mutableListOf()
    private val board = Board()

    init {
        names.forEach{
            players.add(Player(name = it))
        }
    }

    fun play(){
        var gameCount = MAX_GAME_COUNT;
        while(gameCount-- >= 0){
            players.forEach {
                val rollResult = Dice.roll()
                it.position = board.move(it.position, rollResult)
                if(it.position == Board.MAX_POSITION){
                    println("Current Thread: ${Thread.currentThread().name}, ${it.name} wins, game finished")
                    return
                }
                println("Current Thread: ${Thread.currentThread().name}, ${it.name} is at position: ${it.position}")
            }
        }
    }

    companion object{
        const val MAX_GAME_COUNT = 100
    }
}

class Board {
    private val snakes: MutableList<Snake> = mutableListOf()
    private val ladders: MutableList<Ladder> = mutableListOf()

    init {
        snakes.add(Snake(43,35))
        snakes.add(Snake(95,34))
        snakes.add(Snake(23,10))
        ladders.add(Ladder(2,10))
        ladders.add(Ladder(23,33))
        ladders.add(Ladder(45,65))
    }

    fun move(position: Int, rollResult: Int): Int{
        var newPosition = position+rollResult;
        if(newPosition> MAX_POSITION)return position

        val snake = snakes.find { it.startIndex == newPosition }
        if(snake!=null){
            newPosition = snake.endIndex
        }
        val ladder = ladders.find { it.startIndex == newPosition }
        if(ladder!=null){
            newPosition = ladder.endIndex
        }
        return newPosition
    }

    companion object{
        const val MAX_POSITION = 100;
    }

}

data class Player(
    var position: Int = 0,
    val name: String
)

class Dice{
    companion object{
        fun roll(): Int{
            return (MIN_VALUE+(MAX_VALUE- MIN_VALUE+1)*Math.random()).toInt()
        }
        private const val MIN_VALUE = 1
        private const val MAX_VALUE = 6
    }
}

data class Snake(
    val startIndex: Int,
    val endIndex: Int
)

data class Ladder(
    val startIndex: Int,
    val endIndex: Int
)