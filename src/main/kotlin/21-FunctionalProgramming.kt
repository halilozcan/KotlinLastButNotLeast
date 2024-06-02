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

typealias Writer<T, R> = (T) -> Pair<R, String>

val fpGetSalary: Writer<Worker, Salary> =
    fun(worker: Worker) = getSalary(worker) to "Price calculated for ${worker.id}"

val fpFormatSalary: Writer<Salary, String> =
    fun(salary: Salary) = formatSalary(salary) to "Bill line created for ${formatSalary(salary)}"

infix fun <A, B, C> Writer<A, B>.compose(f: Writer<B, C>): Writer<A, C> =
    { x: A ->
        val p1 = this(x)
        val p2 = f(p1.first)
        p2.first to p1.second + "\n" + p2.second
    }

fun main() {

    // Condition race ile
    formatSalaryWithLog(getSalaryWithLog(workers[0]))
    println(logger.log)

    // Condition race olmadan
    val getPriceWithLog = fpGetSalary compose fpFormatSalary
    workers.forEach { worker ->
        println(getPriceWithLog(worker).second)
    }
}