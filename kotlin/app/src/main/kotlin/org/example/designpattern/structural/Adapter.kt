package org.example.designpattern.structural

open class RoundPeg(private val radius: Int) {
    open fun getRadius(): Int = radius
}

class RoundHole(private val radius: Int) {
    fun getRadius(): Int = radius
    fun fits(roundPeg: RoundPeg): Boolean = radius >= roundPeg.getRadius()
}

class SquarePeg(private val width: Int) {
    fun getWidth(): Int = width
}

class SquarePegAdapter(private val squarePeg: SquarePeg) : RoundPeg(squarePeg.getWidth() / 2)
