package org.example.designpattern.structural

interface IDevice {
    fun start()
    fun stop()
    fun volumeUp()
    fun volumeDown()
}

class Tv : IDevice {
    override fun start() = println("Tv Started")
    override fun stop() = println("Tv Stopped")
    override fun volumeUp() = println("Tv Volume increased")
    override fun volumeDown() = println("Tv Volume decreased")
}

class Radio : IDevice {
    override fun start() = println("Radio Started")
    override fun stop() = println("Radio Stopped")
    override fun volumeUp() = println("Radio Volume increased")
    override fun volumeDown() = println("Radio Volume decreased")
}

class Remote(private val device: IDevice) {
    fun start() = device.start()
    fun stop() = device.stop()
    fun volumeUp() = device.volumeUp()
    fun volumeDown() = device.volumeDown()
}
