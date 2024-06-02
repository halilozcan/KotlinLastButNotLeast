package org.example

fun List<Worker>.total(fn: WorkerMapper<Double>): Double =
    fold(0.0) { total, worker -> total + fn(worker) }

fun main() {
    // 1
    val totalPrice = workers.total { it.salary.value }
    val totalWeight = workers.total { it.weight }
    // 2
    println("Total Price: ${totalPrice} £")
    println("Total Weight: ${totalWeight} Kg")
}