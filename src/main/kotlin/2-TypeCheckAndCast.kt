package org.example

fun main() {
    val x: String = ""

    printLength(x)
}

fun printLength(x: Any) {
    /**
     * && operatörünün sol tarafında böyle bir kontrol yapılırsa sağ tarafı otomatik olarak String gibi davranır.
     */
    if(x is String && x.length > 0){

    }

    when (x) {
        is Int -> print(x + 1)
        is String -> print(x.length + 1)
        is IntArray -> print(x.sum())
    }

    /**
     * X String mi diye bakıldığı için otomatik cast işlemi yapılır. İfadenin içerisinde x otomatik olarak x
     * gibi davranır.
     */
    if (x is String) {
        println(x.length)
    }

    /**
     * X else in içerisinde Int gibi algılanır.
     */
    if (x !is Int) {
        return
    } else {
        println("x + 5 = ${x + 5}")
    }



}