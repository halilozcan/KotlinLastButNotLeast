package org.example

fun main() {
    /**
     * Dışarıdaki döngüyü bitirir.
     */
    outerLoop@ for (i in 1..10) {
        innerLoop@ for (j in 1..10) {
            if (i == 5) {
                break@outerLoop
            }
            //println("i:$i j:$j")
        }
    }

    /**
     * Lambda fonksiyonunun o adımı atlaması için kullanılır.
     */
    intArrayOf(1, 2, 3, 4, 5).forEach lambda@{
        if (it == 3) return@lambda
        println(it)
    }

    /**
     * Direkt fonksiyona dönüş için kullanılır
     */
    run loop@{
        intArrayOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return@loop
            println(it)
        }
    }

    println("Hello I'm working..")
}