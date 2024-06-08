package org.example

/**
 * Eğer bir değişkeni nullable yaparsanız örneğin Int? aynı sınıf gibi davranır. Aksi takdirde
 * primitive gibi davranır.
 */

fun main() {
    val primitive: Int = 1967

    var nullable: Int? = null

    /**
     * -128 ile 127 arasında memory optimizasyonu yapıldığı için nullable ile normal değerler birbirine denktir.
     * Sınır aşıldığı durumlarda ise denk olmazlar.
     */
    var a = 120

    var b: Int? = a

    var c:Int? = a

    println("eşitlik:${b == c} denklik:${b === c}")

    /**
     * Stringler immutable dır bir kere değer atandı mı o değiştirilemez.
     */
    var str = "abcd"
}