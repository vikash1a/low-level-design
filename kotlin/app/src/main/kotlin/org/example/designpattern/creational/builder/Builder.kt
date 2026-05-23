package org.example.designpattern.creational.builder

data class Bike(
    var color: String = "",
    var cc: Int = 0
)

interface BikeBuilder {
    fun setColor(): BikeBuilder
    fun setCC(): BikeBuilder
    fun build(): Bike
}

class CommuterBuilder : BikeBuilder {
    private val bike = Bike()
    override fun setColor(): BikeBuilder = apply { bike.color = "Blue" }
    override fun setCC(): BikeBuilder = apply { bike.cc = 100 }
    override fun build(): Bike = bike
}

class ClassicBuilder : BikeBuilder {
    private val bike = Bike()
    override fun setColor(): BikeBuilder = apply { bike.color = "Black" }
    override fun setCC(): BikeBuilder = apply { bike.cc = 250 }
    override fun build(): Bike = bike
}

class Director {
    fun build(builder: BikeBuilder): Bike = builder.setCC().setColor().build()
}
