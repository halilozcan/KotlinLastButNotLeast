package org.example

typealias WorkerMapper<T> = (Worker) -> T

fun List<Worker>.total(fn: WorkerMapper<Double>): Double =
    fold(0.0) { total, worker -> total + fn(worker) }

fun main() {
    var mapper: WorkerMapper<Double> = ::workerWeight

    println("Weight of ${workers[0].name} is ${mapper(workers[0])} Kg")

    mapper = ::workerSalary

    val currency: WorkerMapper<String> = { worker -> worker.salary.currency }
    println("Price of ${workers[0].name} is ${mapper(workers[0])}${currency(workers[0])}")


    val totalPrice = workers.total { it.salary.value }
    println("Total Price: $totalPrice £")

    val totalWeight = workers.total { it.weight }
    println("Total Weight: $totalWeight Kg")
}