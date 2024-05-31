package org.example

fun main() {
    val shape = shape {
        +Point(0, 0)
        +Point(0, 1)
        +Point(1, 3)
    }
    shape.getPoints()

    println("Hello" - 'e')

    println(-"Hello")
}

data class Point(val x: Int, val y: Int) {
    operator fun invoke() = "X:$x Y:$y"
}

class ShapeOperator {
    private val points = mutableListOf<Point>()
    operator fun Point.unaryPlus() {
        points.add(this)
    }

    fun getPoints() = points
}

fun shape(init: ShapeOperator.() -> Unit): ShapeOperator {
    val shape = ShapeOperator()
    shape.init()
    return shape
}

operator fun String.minus(char: Char): String {
    return dropLastWhile {
        it != char
    }
}

operator fun String.unaryMinus(): String {
    return this.reversed()
}