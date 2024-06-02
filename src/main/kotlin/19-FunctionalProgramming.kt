package org.example

typealias WorkerMapper<T> = (Worker) -> T

fun List<Worker>.total(fn: WorkerMapper<Double>): Double =
    fold(0.0) { total, worker -> total + fn(worker) }

fun main() {
    var mapper: WorkerMapper<Double> = ::workerWeight

    val currency: WorkerMapper<String> = { worker -> worker.salary.currency }

    println("Weight of ${workers[0].name} is ${mapper(workers[0])} Kg")

    mapper = ::workerSalary

    println("Price of ${workers[0].name} is ${mapper(workers[0])}${currency(workers[0])}")

    // 1
    val totalPrice = workers.total { it.salary.value }
    val totalWeight = workers.total { it.weight }
    // 2
    println("Total Price: ${totalPrice} £")
    println("Total Weight: ${totalWeight} Kg")
}