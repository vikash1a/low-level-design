package org.example.designpattern.behavioral

interface ISortStrategy {
    fun sort(data: MutableList<Int>): List<Int>
}

class BubbleSort : ISortStrategy {
    override fun sort(data: MutableList<Int>): List<Int> {
        println("Sorting with BubbleSort")
        return data.sorted()
    }
}

class QuickSort : ISortStrategy {
    override fun sort(data: MutableList<Int>): List<Int> {
        println("Sorting with QuickSort")
        return data.sorted()
    }
}

class Sorter(private var strategy: ISortStrategy) {
    fun setStrategy(strategy: ISortStrategy) { this.strategy = strategy }
    fun sort(data: MutableList<Int>): List<Int> = strategy.sort(data)
}
