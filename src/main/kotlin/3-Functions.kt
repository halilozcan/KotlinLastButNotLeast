package org.example

/**
 * Bazen sınıf içerisindeki bazı methodlar birbiriyle alakasız olabilirler. Bundan dolayı birbiriyle alakalı işlemleri
 * bir arada tanımlayabilmek için fonksiyon içerisinde fonksiyon tanımlanması yapılabilir. Local fonksiyonlar
 * kendileri çağırılmadan önce tanımlanmalıdır. Ayrıca local fonksiyonlar bir üst methodun değişkenlerine erişebilir.
 * Bu değişkenler referans tipli değişkenlere dönüşür ve alt method içerisinden erişilebilir.
 */
fun login(userName: String, password: String): Boolean {
    fun validateInput(input: String) {
        if (input.isEmpty()) {
            throw Exception("Input can not be empty")
        }
    }

    validateInput(userName)
    validateInput(password)
    return true
}

/**
 * infix fonksiyonlar her zaman receiver a ve parametrenin belirtilmesine ihtiyacı vardır. güncel receiver içerisinde
 * çağırılırken this kullanılmalı veya parantezli fonksiyon çağırımı yapılmalıdır.
 */
class Operation {
    infix fun add(a: Int) {}

    fun buildAddOperation() {
        this add 5
        add(5)
        // add 5 bu yanlıştır
    }
}

/**
 *
 * (A,B)-> C
 * A -> Parametre Tipi
 * B -> Parametre Tipi
 * C -> Return Tipi
 *
 * () -> A - parametresi yok A tipinde dönüşü var
 *
 * A.(B) -> C -> A tipi üzerinden B parametreli fonksiyon çağrımı var ve return tipi
 * C demektir.
 *
 * nullable fonksiyon tanımlamak için ((Int,Int)->Int)?
 */

typealias SumFunction = (Int, Int) -> Int

fun typeAliasFunction(action: SumFunction): Int {
    return action.invoke(1, 2)
}

/**
 * Lambda expressionlar değer olarak döndürebilir.
 */
fun returnLambda(): () -> String {
    val lambda = { "Hi" }
    return lambda
}

fun main() {
    login("Hello", "1234")

    /**
     * Lambda expressionlar fonksiyonun return type ını belirtemezler. return type açık
     * olarak belirtmek için anonymous fonksiyonlar kullanılır. anonymous fonksiyonlar
     * isimsiz olarak yazılan fonksiyonlardır. bir body olarak veya single expression
     * olarak yazılabilir. return type aynı zamanda infer edilebilir. yani belirtmeye de
     * gerek yoktur
     */

    val names = arrayOf("Halil", "Metehan", "İbrahim", "Hilal")

    val filterFunction = fun(a: String): Boolean = a.length > 5

    names.filter(filterFunction)

}