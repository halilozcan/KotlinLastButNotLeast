package org.example

/**
 * Genericler inheritance kavramı normalden farklıdır. Örneğin X sınıfı Y sınıfının alt
 * sınıfı olarak düşünülürse Array<X>, Array<Y> nin alt tpipi olarak kabul edilmez.
 * Bu durum variance yaparak çözülür.
 */

/**
 * Type nesnelerin paylaştıkları propertyleri tanımlar. Yani derleyiciye yazılımcının
 * veriyi nasıl kullanmayı planladığını söyler.
 *
 * Class ise bu tipin bir implementasyonudur.
 */

/**
 * Burada kitty isminden de anlaşılacağı üzere hem bir classtır hem de bir tipin ismidir.
 */
data class Kitty(val kittyName: String, val isTwoFingered: Boolean) : Mammal(kittyName) {
    /*
    fun eat() {}
    fun sleep() {}
    */
}

data class Lion(val lionName: String) : Mammal(lionName) {
    /*fun eat() {}
    fun sleep() {}
    */
}

open class Mammal(val name: String) {
    fun eat() {}
    fun sleep() {}
}

/**
 * Burada Mammal belirtilip Kitty gönderilebilmesinin sebebi List in out ile işaretlenip
 * covariance sağlamasıdır. Covariance gönderilen tipin alt tipinin de gönderilmesini
 * sağlar. Fonksiyon parametresi ile gönderilen parametre eşleşmediklerinde kabul
 * kriterleri, bunların en azından tanımlanan tipin alt türü olmasıdır.
 *
 * Covariance sadece tek yollu gider. Yani Lion Mammal in alt tipidir diyebiliriz ancak
 * Mammal bir Lion dur diyemeyiz.
 */
fun feed(elements: List<Mammal>) {
    elements.forEach {
        it.eat()
    }
}

fun main() {
    /**
     * var kitty:Kitty -> Kitti sınıfının nesnesini bir değişken tanımlar.
     * var kitty:Kitty? -> Kitty nin aynı zamanda nullable bir tip oluşturabileceğini
     * belirtir.
     */

    /**
     * List type ı otomatik olarak Kitty olur
     */
    val kitties = listOf(
        Kitty("A", false), Kitty("B", false), Kitty("C", false)
    )

    val lions = listOf(Lion("D"), Lion("E"))

    // Covariance
    feed(kitties)
    feed(lions)

    val allElements = listOf(
        Kitty("Jerry", false),
        Kitty("Bae", true),
        Kitty("Alex", false),
        Lion("Tegan"),
        Lion("Peggy")
    )

    // Contravariance
    val compareNames = Comparator { o1: Mammal, o2: Mammal ->
        o1.name.first().code - o2.name.first().code
    }

    println(allElements.sortedWith(compareNames))
}