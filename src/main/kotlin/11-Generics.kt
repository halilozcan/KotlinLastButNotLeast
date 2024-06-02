package org.example

open class A {
    //some methods & member variable
}

class B : A() {
    // some mehods & member variables
}

/**
 * Invariance; tam olarak belirlenen tipin kullanılmasıdır
 */
class InvarianceGenericClass<T : A> {

}

/**
 * Covariance; super type yerine subtype kullanılması
 * Sadece out position da kullanılabilir yani fonksiyonun return type ı olarak
 * kullanılabilir.
 * val property olarak kullanılabilir, var property olarak kullanılmaz.
 */
class CovarianceGenericClass<out T : A> {

}

/**
 * Contravariance; subtype yerine supertype ın kullanılması
 * Sadece in position da yani parametre olarak kullanılabilir
 * out position olarak kullanılamaz yani val ya da var propery olarak kullanılmaz
 */
class ContravarianceGenericClass<in T : A> {

}

/**
 * Out ve in positionlarda constructorlar bu parametre olarak alma kuralına tabi
 * tutulmazlar.
 * Java nın aksine Kotlin tarafında declaration-site variance şeklinde sınıflar
 * yazılırken type parametreleri tanımlanabilir. Java da WildCards'a (*) ihtiyaç
 * bulunmaktadır. Böylece fazlasıyla boilerplate code yazılmamış olur.
 */
class ReadOnlyBox<out T>(private var item: T) {
    fun getItem(): T = item
}

class WriteOnlyBox<in T>(private var item: T) {
    fun setItem(newItem: T) {
        item = newItem
    }
}

fun main() {
    /**
     * B a nın alt tipidir. Bundan dolay B de anın alt tipi olduğu için üretilebilir
     */
    val a: A = B()

    /**
     * Typelar uyuşmaz. Invariance demek bütün tam olarak verilen tipin kullanılması
     * demektir.
     */
    // val invariance1: InvarianceGenericClass<A> = InvarianceGenericClass<B>()
    // val invariance2: InvarianceGenericClass<B> = InvarianceGenericClass<A>()

    /**
     * Sınıfın alt tipinin kullanılabilmesi için out ile işaretlenmesi gereklidir.
     */
    val covarianceGenericClass: CovarianceGenericClass<A> = CovarianceGenericClass<B>()

    /**
     * Sınıfın üst tipinin kullanulabilmesi için in ile işaretlenmesi gereklidir.
     */
    val contravarianceGenericClass: ContravarianceGenericClass<B> = ContravarianceGenericClass<A>()
}