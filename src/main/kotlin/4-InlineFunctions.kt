package org.example

val publicMember = "Public"

@PublishedApi
internal val internalMember = "I am internal"

fun main() {
    callingFunction()
}

fun callingFunction() {
    println("started")
    /**
     * Kotlin byte code a çevrildiğinde nesne üretilir. Bunu döngüde kullandığımız zaman
     * her bir çağırımda nesne üretilir. inline eklenmesi bunu engeller. ve fonksiyonun
     * direkt olarak içeriğinin kendisini çağıran tarafa kopyalanmasını sağlar.
     */
    higherOrderFunction {
        println("lambda")
        /**
         * inline fonksiyonlar lokal olarak return çağırarak calling site dan return
         * yapabilir.
         */
        // Sadece bu fonksiyondan dönülmesini istiyorsak return@higherOrderFunction
        return
    }

    higherOrderFunctionNoInlined(lambda = {
        return
    }, noInlinedLambda = {
        // no inlined return e izin vermez
        // return
    })

    higherOrderFunctionCrossInline {
        // non-local return e izin vermez.
        //return
    }

    println("finished")
}

inline fun higherOrderFunction(lambda: () -> Unit) {
    /**
     * inline fonksiyonlar kendisini çevreleyen sınıf veya scope daki
     * private değişkenlere ve methodlara etişim sağlayamaz. Erişmek için bunların public
     * veya internal olması, internal olanların @PublishedApi ile işaretlenmesi gerekir
     */
    //privateMemberVariable.length
    publicMember.length
    internalMember.length
    doSomething()
    lambda()
    doAnotherThing()
}

/**
 * Bazen bazı lambda expressionları inlined olmasını istemeyebilirsiniz. Eğer bunun
 * inlined olmasını istemiyorsanız noinline ile işaretleyebilirsiniz. noinline
 * fonksiyonlar local return lere izin vermez
 */
inline fun higherOrderFunctionNoInlined(lambda: () -> Unit, noinline noInlinedLambda: () -> Unit) {
    doSomething()
    noInlinedLambda()
    doAnotherThing()
}

/**
 * Local returne izin verilmesini istemediğimiz durumlar için crossinline ile
 * fonksiyonları işaretleriz.
 */

inline fun higherOrderFunctionCrossInline(crossinline lambda: () -> Unit) {
    /**
     * Inline bir fonksiyon içerisinde inline olmayan bir fonksiyona
     * higher order çağırılırsa return e izin vermemek için lambda() fonksiyonu
     * crossinline olarak işaretlenir.
     */
    normalFunction {
        lambda()
        // return
    }
}

fun doSomething() {
    println("do something()")
}

fun doAnotherThing() {
    println("do another thing()")
}

fun normalFunction(lambda: () -> Unit) {
    println("normal function started")
    lambda()
    println("normal function ended")
    return
}