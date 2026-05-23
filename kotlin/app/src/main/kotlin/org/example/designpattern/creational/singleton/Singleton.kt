package org.example.designpattern.creational.singleton

import java.util.UUID

class Singleton {
    val randomUuid: UUID = UUID.randomUUID()
    companion object {
        private val singleton: Singleton = Singleton()
        fun getInstance(): Singleton = singleton
    }
}

class SingletonWithDependency private constructor(private val config: Int) {
    val randomUuid: UUID = UUID.randomUUID()
    companion object {
        @Volatile private var instance: SingletonWithDependency? = null
        fun getInstance(config: Int): SingletonWithDependency =
            instance ?: synchronized(this) {
                instance ?: SingletonWithDependency(config).also { instance = it }
            }
    }
}
