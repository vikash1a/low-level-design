package org.example.designpattern.structural

interface IGraphic {
    fun move(x: Int, y: Int)
    fun draw()
}

open class Dot(open var x: Int, open var y: Int) : IGraphic {
    override fun move(x1: Int, y1: Int) { x += x1; y += y1; println("Dot moved") }
    override fun draw() = println("Dot drawn")
}

class Circle(override var x: Int, override var y: Int, var radius: Int) : Dot(x, y) {
    override fun draw() = println("Circle drawn")
}

class CompoundGraphic(private val graphics: MutableList<IGraphic> = mutableListOf()) : IGraphic {
    fun add(graphic: IGraphic) = graphics.add(graphic)
    fun remove(graphic: IGraphic) = graphics.remove(graphic)
    override fun move(x: Int, y: Int) = graphics.forEach { it.move(x, y) }
    override fun draw() = graphics.forEach { it.draw() }
}
