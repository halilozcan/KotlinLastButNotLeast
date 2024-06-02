package org.example

data class Salary(val value: Double, val currency: String = "$")

data class Worker(val id: String, val name: String, val salary: Salary, val weight: Double)

val workers = listOf(
    Worker("1", "Halil", Salary(4000.00, "£"), 105.1),
    Worker("2", "Metehan", Salary(3000.00, "£"), 60.8)
)

fun workerWeight(worker: Worker) = worker.weight

fun workerSalary(worker: Worker) = worker.salary.value

val workerWeightFun = fun(worker: Worker) = worker.weight
val workerSalaryFun = fun(worker: Worker) = worker.salary.value

fun main() {
    var workerFun = workerWeightFun
    println("Worker weight: ${workerFun(workers[0])} Kg")

    workerFun = workerSalaryFun
    println("Worker salary: ${workerFun(workers[0])} £")
}