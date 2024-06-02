package org.example

typealias WorkerMapper<T> = (Worker) -> T

fun main() {
    var mapper: WorkerMapper<Double> = ::workerWeight

    val currency: WorkerMapper<String> = { worker -> worker.salary.currency }

    println("Weight of ${workers[0].name} is ${mapper(workers[0])} Kg")

    mapper = ::workerSalary

    println("Price of ${workers[0].name} is ${mapper(workers[0])}${currency(workers[0])}")
}