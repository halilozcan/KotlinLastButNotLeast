package org.example

class Logger {
    var log = StringBuilder()
    fun log(str: String) {
        log = log.append(str).append("\n")
    }
}

val logger = Logger()

val getSalaryWithLog: Func<Worker, Salary> = {
    logger.log("Salary calculated for ${it.id}")
    it.salary
}

val formatSalaryWithLog: Func<Salary, String> = {
    logger.log("Bill line created")
    "value: ${it.value} ${it.currency}"
}

fun main() {
    // Condition race ile
    formatSalaryWithLog(getSalaryWithLog(workers[0]))
    println(logger.log)
}