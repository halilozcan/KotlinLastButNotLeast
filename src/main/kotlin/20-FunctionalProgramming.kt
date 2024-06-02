package org.example

typealias Func<A, B> = (A) -> B

val getSalary: Func<Worker, Salary> = { worker -> worker.salary }

val formatSalary: Func<Salary, String> =
    fun(salaryData: Salary) = "value: ${salaryData.value}${salaryData.currency}"

infix fun <A, B, C> Func<B, C>.after(f: Func<A, B>): Func<A, C> = { x: A -> this(f(x)) }

fun main() {
    // Composition olmadan
    val result: String = formatSalary(getSalary(workers[0]))
    println(result)

    // Composite edilmiş sonuç
    val compositeResult: String = (formatSalary after getSalary)(workers[0])
    println(compositeResult)
}