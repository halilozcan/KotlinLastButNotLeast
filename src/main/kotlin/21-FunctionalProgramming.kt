package org.example

typealias Writer<T, R> = (T) -> Pair<R, String>

val fpGetSalary: Writer<Worker, Salary> =
    fun(worker: Worker) = getSalary(worker) to "Price calculated for ${worker.id}"

val fpFormatSalary: Writer<Salary, String> =
    fun(salary: Salary) = formatSalary(salary) to "Bill line created for ${formatSalary(salary)}"

infix fun <A, B, C> Writer<A, B>.compose(f: Writer<B, C>): Writer<A, C> = { x: A ->
        val p1 = this(x)
        val p2 = f(p1.first)
        p2.first to p1.second + "\n" + p2.second
    }

fun main() {
    // Condition race olmadan
    val getPriceWithLog = fpGetSalary compose fpFormatSalary
    workers.forEach { worker ->
        println(getPriceWithLog(worker).second)
    }
}